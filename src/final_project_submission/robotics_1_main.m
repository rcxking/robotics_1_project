%{
robotics_1_main.m - The main driver code for our Path Planning Robotics 1
Final Project.

Bryant Pong
Matt Corsaro
Sean Bayman

CSCI-4480
11/21/14

Last Updated: 12/5/14 - 1:44 PM
%}

% Clear the screen:
close all;
clear all;
clc;

% Step 1: Draw Corner Dots on Paper:

% END STEP 1

% Add the Java Path for the A* Classes:
javaaddpath('D:\final_project_submission\astar');

% Create the Java String Variables needed for the map:
javaE = java.lang.String('E'); % Denotes the ending point
javaS = java.lang.String('S'); % Denotes the starting point
javaX = java.lang.String('X'); % Denotes an obstacle
java0 = java.lang.String('0'); % Denotes a free space that can be moved to

% Process the image and determine where the obstacles are as well as the
% starting and ending points of the robot:
[obs, start, stop] = mapGeneration(imread('Photo Dec 09, 3 59 49 PM.jpg'));

%imshow(obs)

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

%pause;
map = convertImageToArray(obsSmall, startSmall(1), startSmall(2), stopSmall(1), stopSmall(2));
atv = ArrayToVector(map);
realmap = atv.convertArray();

disp('realmap: ');
disp(realmap);

%pause;

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

% Point Locations for Reference
% Coordinate bounds (X, Y, Z in world frame)
C = -0.132; x = 0.03; y = 0.055;
p0 = [C+x;  y; 0];
p1 = [C+x; -y; 0];
p2 = [C-x; -y; 0];
p3 = [C-x;  y; 0];



% Move to each boundry point
%{
moveud(p0(1),p0(2),1);
moveud(p1(1),p1(2),1);
moveud(p2(1),p2(2),1);
moveud(p3(1),p3(2),1);
%}

% Now iterate through the path and determine the actual workspace
% coordinates the path coordinates correspond to.  The Omni will now draw
% the path from the starting point to ending point.  Otherwise, display a
% failure message.
if size(path, 1) == 0
    disp('No Solution Found');
else
    % Iterate through the path, and scale all points to robot workspace
    % coordinates.
    
    scaledPoints = linearMap(path, C, x, y);
    
    pause;

    % Initialize the Phantom Omni
omni = RobotRaconteur.Connect('tcp://127.0.0.1:5150/PhantomOmniSimulinkHost/PhantomOmni');

    
    disp('NOW DRAWING OUT FINAL PATH');
    
    % Now draw the path:
    moveud(scaledPoints(1, 1), scaledPoints(1, 2));
    pause;
    for i = 1:size(scaledPoints, 1)
        disp(['Now calling moveud to point #',num2str(i),': ',...
              num2str(scaledPoints(i, 1)), ...
              ', ', num2str(scaledPoints(i, 2))]);
        moveud(scaledPoints(i, 1), scaledPoints(i, 2));
    end
    
    disp('DONE DRAWING PATH! :D');
end