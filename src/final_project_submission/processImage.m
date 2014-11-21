function [ outIm ] = processImage( rawIm, thresholds, directions )
% directions should be 1 for >=, 0 for <


% Before doing the thresholding, we should add some filtering to the
% images. In particular, band-pass for the obstacles would be good. Start
% location and stop location may need to be more delicate. Alternatively,
% they could be pre- or post-processed separately.


% Perform some thresholding.
for n = 1:3
    o{n} = rawIm(:,:,n) < thresholds(n);
    if( directions(n) )
        o{n} = ~o{n};
    end
end

big = o{1} & o{2} & o{3};

% These sizes are fairly arbitrary. They work for now, but it could be
% better to pass them as arguments to the function.
erodeSize = 4;
dilateSize = 5;
closeSize = 6;

% Create some structural elements to do the erosion, dilation, and closure
sE = strel('disk',erodeSize);
sD = strel('disk',dilateSize);
sC = strel('disk',closeSize);

% Erode to get rid of small spots (noise)
eroded = imerode(big, sE);
% Dilate to restore the dimensions of the big objects
dilated = imdilate(eroded, sD);
% Close large objects
closed = imclose(dilated, sC);

% Fill closed objects and return the result
outIm = imfill(closed,'holes');

end

