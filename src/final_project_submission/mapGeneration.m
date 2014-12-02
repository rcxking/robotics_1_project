function [obsMap, startPos, stopPos, bigMap] = ...
    mapGeneration( cameraImage, mapDim, workingRes, N, objTh )
% MAPGENERATION Only camerageImage is required, the rest are optional.

erodeScale = 250;

if nargin < 5
    objTh = 51;
    if nargin < 4
        N = 1 ;  % filter size for low-pass filtering
        if nargin < 3
            workingRes = 500; % pixel side length for high-resolution transform results
            if nargin < 2
                mapDim = [10 10]; % small obstacle map output pixel dimensions
            end
        end
    end
end

ortho = userAssisted(cameraImage,workingRes) ;


% Using sparse recovery to separate foreground objects from background and
% lighting effects:
[fg, ~, off] = rpca_sparseRecovery(ortho, 2/workingRes);
% Low pass filter the image
fg = imgLPF(fg, N);

% Threshold the (almost) sparse foreground image to remove noise
fg = double(fg) + double(off);
fg = fg .* ( abs(fg) > objTh );
% figure(); imshow(uint8(fg-off));


% Process the obstacle map
binMap = fg(:,:,1) & ~fg(:,:,2) & ~fg(:,:,3) ;
bigMap = processImage(binMap,ceil(workingRes/erodeScale));
%bigMap = processImage(leveled,obsTh,obsDir);
%{
figure(2);
imshowpair(ortho,bigMap,'montage')
title('Original image and full-resolution obstacle map');
%}
clear binMap;


% Scale down the obstacle map
%obsMap = imresize(bigMap,mapDim);
obsMap = dsToLogical(bigMap,mapDim);
%{
figure(3);
imshow(obsMap);
title('Low-resolution obstacle map');
%}
%clear bigMap;


% Compute start and stop maps just like for the obstacle map, but also
% compute the center-of-mass point from the maps.
binStart = fg(:,:,1) & fg(:,:,2) & fg(:,:,3) ;
bigStart = processImage(binStart, ceil(workingRes/erodeScale));
%start = imresize(bigStart,mapDim);
start = dsToLogical(bigStart,mapDim);
[startx,starty] = computePosition(start);
%{
figure(4);
imshow(start);
title(['Start position: ',num2str(startx),', ',num2str(starty)]);
%}
clear binStart bigStart start;

binStop = ~fg(:,:,1) & fg(:,:,2) & fg(:,:,3) ;
bigStop = processImage(binStop, ceil(workingRes/erodeScale));
%stop = imresize(bigStop,mapDim);
stop = dsToLogical(bigStop,mapDim);
[stopx,stopy] = computePosition(stop);
%{
figure(5);
imshow(stop);
title(['Stop Position: ',num2str(stopx),', ',num2str(stopy)]);
%}
clear binStop bigStop stop;



startPos= [startx,starty];
stopPos= [stopx,stopy];



end



%% Auxilary functions

% Function to correct for persepective distortion
function [ orthoImage ] = userAssisted( rawImage, resolution )

    fixed = [  0 1 ;  1 1 ;  1 0 ;  0 0 ] * resolution;
    x=fixed(:,1);
    y=fixed(:,2);


    % User input for selectiong workspace corners from the image
    h = figure();
    imshow(rawImage);

    X = zeros(4,1); Y = zeros(4,1);
    for i = 4:-1:1
        title(['Corner selection must be clockwise or anti-clockwise. Corners remaining: ',num2str(i)]);
          [a, b] = ginput(1);
        X(i) = a; Y(i) = b;
    end
    close(h);
    [X, Y] = sortPolyFromClockwiseStartingFromTopLeft( X, Y );


    % Compute the transformation matrix that brings the user-selected 
    % points to the known workspace corners. This is a 2d affine 
    % transformation represented as a 3d projective-space transformation.
    
    B = zeros(8,8);
    B(1:2:end,1) = X;
    B(1:2:end,2) = Y;
    B(1:2:end,3) = 1;
    B(2:2:end,4) = X;
    B(2:2:end,5) = Y;
    B(2:2:end,6) = 1;
    B(1:2:end,7:8) = -1*[X.*x, Y.*x];
    B(2:2:end,7:8) = -1*[X.*y, Y.*y];

    w = [x, y];
    w = reshape([x,y,]',8,1);
    u = B\w;

    U = reshape( [u;1], 3, 3)' ;


    % Use the transformation U matrix to create a transformation object
    T = maketform('projective',U');

    orthoImage = imtransform( rawImage, T, ...
                              'XData', [1 resolution], ...
                              'YData', [1 resolution]);
    orthoImage = flipud( orthoImage );

end


% Sparse recovery function for image
function [ objectImage, bgImage, offset ] = rpca_sparseRecovery( image, lambda )%, svsToTrun )

    if nargin < 2
        lambda = 0.05;
    end

    % Convert color image to adjacent single channels
    T = colorToAll( image );

    % Add the required subfolders to the path, and save the previous state
    oldPath = addpath(genpath('inexact_alm_rpca'));
    
    % This is where the heavy-lifting happens.
    % High precision isn't needed, so we choose a fast algorithm.
    [A,E] = inexact_alm_rpca(T,lambda);
    
    % Convert the result back to unsigned integers, and compute an offset
    % to use for thresholding later.
    Au = uint8(A);
    offset = floor(min(min(E)));
    Eu = uint8(E-offset);

    % Back to color from adjacent channels
    bgImage = allToColor( Au );
    objectImage = allToColor( Eu );
    
    % Restore the path to the previous state.
    path(oldPath);

end


function [ outIm ] = processImage( binIm, erodeSize, dilateSize, closeSize )
% PROCESSIMAGE Optional parameters to define erode, dilate, and closure sizes

    if( 1 == nargin )
        erodeSize = 4;
        dilateSize = 5;
        closeSize = 6;
    elseif( 2 == nargin )
        dilateSize = erodeSize + 1;
        closeSize = dilateSize + 1;
    elseif( 3 == nargin )
        closeSize = erodeSize + 1;
    end

    % Create some structural elements to do the erosion, dilation, and closure
    sE = strel('disk',erodeSize);
    sD = strel('disk',dilateSize);
    sC = strel('disk',closeSize);

    % Erode to get rid of small spots (noise)
    eroded = imerode(binIm, sE);
    % Dilate to restore the dimensions of the big objects
    dilated = imdilate(eroded, sD);
    % Close large objects
    closed = imclose(dilated, sC);

    % Fill closed objects and return the result
    outIm = imfill(closed,'holes');

end


function [ x, y ] = computePosition( logicalImage )
%COMPUTEPOSITION Computes an estimaed center-of-mass position

    I = size(logicalImage,1); % size in y direction
    J = size(logicalImage,2); % size in x direction

    x = 0; y = 0; count = 0;

    % Add up the positions of true pixels and divide by the count of trues
    for i = 1:I
        for j = 1:J

            if(logicalImage(i,j))
                y = y + i;
                x = x + j;
                count = count + 1;
            end

        end
    end

    % Round up, since the image starts at (1, 1), not (0, 0)
    y = ceil( y / count );
    x = ceil( x / count );

end


function [ allChannels ] = colorToAll( color )
    allChannels = double([color(:,:,1); color(:,:,3); color(:,:,3)]);
end


function [ color ] = allToColor( all )
    res = size(all);
    res = min(res(1:2));
    
    color(:,:,1) = all(1:res,:);
    color(:,:,2) = all(res+1:2*res,:);
    color(:,:,3) = all(2*res+1:3*res,:);
end


function [ smallImg ] = dsToLogical( img, newDim, thresh )
% dsToLogical Default threshold is 0

    if( nargin < 3 )
        thresh = 0;
    end

    smallImg = ( imresize(uint8(img*255),newDim) > thresh );

end


function [ filtered ] = imgLPF( img, N )
% IMLPF Gaussian low-pass filter on img, order N

    Hl = fspecial('gaussian',N);
    filtered = imfilter(img,Hl,'replicate');

end


