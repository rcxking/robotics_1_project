%{
    convertImageToArray.m - Function to convert a processed image into an
    occupancy map.

    Bryant Pong
    CSCI-4480
    11/18/14

    Last Updated: 11/18/14 - 4:53 PM
%}

%{
This function converts an original image of type (logical) to a
2-Dimension Java String Array.
%}
function [ imageArray ] = convertImageToArray(originalImage, startX, startY, endX, endY)
    
    % Initialize imageArray as a 2D Java Array:
    imageArray = javaArray('java.lang.String', size(originalImage, 1), size(originalImage, 2));

    %{
    These are Java String representations of the different kinds of items
    in our map.  These are:
    
    'S' - Starting Location of the Robot Arm
    'X' - Obstacle Location
    'E' - Target Destination of the Robot Arm
    '0' - Valid Space to Move to
    %}
    javaS = java.lang.String('S');
    javaX = java.lang.String('X');
    javaE = java.lang.String('E');
    java0 = java.lang.String('0');
    
    for i = 1:size(originalImage, 1)
        for j = 1:size(originalImage, 2)          
            % Obstacle detected!
            if originalImage(i, j) == 1
                imageArray(i, j) = javaX;
            end
            
            % Free space detected!
            if originalImage(i, j) == 0
                imageArray(i, j) = java0;
            end
        end
    end
    
    % Set the locations of the starting and ending points:
    imageArray(startY, startX) = javaS;
    imageArray(endY, endX) = javaE;
  
end

