function [obsMap, startPos, stopPos] = mapGeneration( cameraImage, mapDim );

%clear all; close all; clc;

workingRes = 2000;
%cameraImage = imread('2014-10-18 21.10.18.jpg');
ortho = userAssisted(cameraImage,workingRes) ;


% FIX THIS!!!
% Some constants to use for level adjustments.
lowIn  = [125 100 70] / 255;
hiIn   = [170 160 150] / 255;
lowOut = [0 0 0] / 255;
hiOut  = [255 255 255] / 255;


% Adjusting image levels might be unnecessary. Band-pass filtering might be
% sufficient.
leveled = imadjust(ortho, [lowIn; hiIn], [lowOut; hiOut]);


% Display the original and adjusted images
%{
figure(1);
imshowpair(ortho,leveled,'montage');
title('Raw and level-adjusted images');
%}


% Thresholds and directions: 0 for <, 1 for >=
obsTh = [50 100 80];
obsDir = [0 0 1];

startTh = [50 40 50];
startDir = [0 1 0];

stopTh = [100 50 50];
stopDir = [1 0 0];


% Process the obstacle map
bigMap = processImage(leveled,obsTh,obsDir);
%{
figure(2);
imshowpair(ortho,bigMap,'montage')
title('Original image and full-resolution obstacle map');
%}


% Scale down the obstacle map
obsMap = imresize(bigMap,mapDim);
%{
figure(3);
imshow(obsMap);
title('Low-resolution obstacle map');
%}
clear bigMap;


% Compute start and stop maps just like for the obstacle map, but also
% compute the center-of-mass point from the maps.
bigStart = processImage(ortho,startTh,startDir);
start = imresize(bigStart,mapDim);
[startx,starty] = computePosition(start);
%{
figure(4);
imshow(start);
title(['Start position: ',num2str(startx),', ',num2str(starty)]);
%}
clear bigStart start;

bigStop = processImage(ortho,stopTh,stopDir);
stop = imresize(bigStop,mapDim);
[stopx,stopy] = computePosition(stop);
%{
figure(5);
imshow(stop);
title(['Stop Position: ',num2str(stopx),', ',num2str(stopy)]);
%}
clear bigStop stop;



startPos= [starty,startx];
stopPos= [stopy,stopx];



end
%{
h = figure();
while(1)
    imshow(raw);
    [x,y] = ginput(1);

    raw(round(y),round(x),:)
end
close(h)
%}


%{
[r, cr] = imhist(ortho(:,:,1));
[g, cg] = imhist(ortho(:,:,2));
[b, cb] = imhist(ortho(:,:,3));

figure(4);
subplot(3,1,1); imhist(ortho(:,:,1));%stem(r,cr,'r');
subplot(3,1,2); imhist(ortho(:,:,2));%stem(g,cg,'g');
subplot(3,1,3); imhist(ortho(:,:,3));%stem(b,cb,'b');
%}
