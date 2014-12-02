%{
robotics_1_main.m - The main driver code for our Path Planning Robotics 1
Final Project.

Bryant Pong
Matt Corsaro
Sean Bayman

CSCI-4480
11/21/14

Last Updated: 12/2/14 - 4:31 PM
%}

% Clear the screen:
close all;
clear all;
clc;

% Step 1: Draw Corner Dots on Paper:

% END STEP 1

% Add the Java Path for the A* Classes:
javaaddpath('C:\Users\pongb\Documents\robotics_1_project\src\AStar\src');

% Create the Java String Variables needed for the map:
javaE = java.lang.String('E'); % Denotes the ending point
javaS = java.lang.String('S'); % Denotes the starting point
javaX = java.lang.String('X'); % Denotes an obstacle
java0 = java.lang.String('0'); % Denotes a free space that can be moved to

% Process the image and determine where the obstacles are as well as the
% starting and ending points of the robot:
[obs, start, stop] = mapGeneration(imread('2014-12-02 16.07.23.jpg'));

imshow(obs)

smallSize = [10 10];

obsSmall = imresize(obs, smallSize);
startSmall = ceil(start.*[smallSize(1)/size(obs,1) smallSize(2)/size(obs,2)]);
stopSmall = ceil(stop.*[smallSize(1)/size(obs,1) smallSize(2)/size(obs,2)]);

disp('obsSmall: ');
disp(obsSmall);
disp('startSmall: ');
disp(startSmall);
disp('stopSmall: ');
disp(stopSmall);

pause;
map = convertImageToArray(obsSmall, startSmall(1), startSmall(2), stopSmall(1), stopSmall(2));
atv = ArrayToVector(map);
realmap = atv.convertArray();

disp('realmap: ');
disp(realmap);

pause;

astar = AStar(realmap);
path = astar.AStarAlgorithm();

%{
The path is stored in a vector of 2-Dimensional coordinate points
[row][col].  However, the path is 0-indexed; we need to add 1 to each point
to make the path 1-indexed:
%}
for i = 1:size(path, 1)
    path(i, 1) = path(i, 1) + 1;
    path(i, 2) = path(i, 2) + 1;
end

% DEBUG ONLY - Print out the 1-indexed path:
disp('1-indexed path: ');
disp(path);

% Now iterate through the path and determine the actual workspace
% coordinates the path coordinates correspond to.  The Omni will now draw
% the path from the starting point to ending point.  Otherwise, display a
% failure message.
if size(path, 1) == 0
    disp('No Solution Found');
else
    % Iterate through the path, and scale all points to robot workspace
    % coordinates.
    
    scaledPoints = linearMap(path, -0.132, 0.060, 0.060);
    
    % Now draw the path:
    for i = 1:size(scaledPoints, 1)
        
    end
end