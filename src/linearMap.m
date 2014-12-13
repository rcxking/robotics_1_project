function [ workspacePoint ] = linearMap( pathPoint, C, x, y )
%MAP Summary of this function goes here
%   Detailed explanation goes here

    workspacePoint = double(zeros(size(pathPoint)));
    
    workspacePoint(:,1) = map(pathPoint(:,1), 1, 10, C+x, C-x);
    workspacePoint(:,2) = map(pathPoint(:,2), 1, 10, -y, y);

end


function [ mapped ] = map( z, in_min, in_max, out_min, out_max )

    mapped = (double(z) - in_min) .* (out_max - out_min) ./ (in_max - in_min) + out_min;

end