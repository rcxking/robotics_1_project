%{
robotics_1_main.m - The main driver code for our Path Planning Robotics 1
Final Project.

Bryant Pong
Matt Corsaro
Sean Bayman

CSCI-4480
11/21/14

Last Updated: 11/21/14 - 1:54 PM
%}

% Clear the screen:
close all;
clear all;
clc;

% Add the Java Path for the A* Classes:
javaaddpath('C:\Users\pongb\Documents\robotics_1_project\src\AStar\src');

% Create the Java String Variables needed for the map:
javaE = java.lang.String('E'); % Denotes the ending point
javaS = java.lang.String('S'); % Denotes the starting point
javaX = java.lang.String('X'); % Denotes an obstacle
java0 = java.lang.String('0'); % Denotes a free space that can be moved to

% Process the image and determine where the obstacles are as well as the
% starting and ending points of the robot:
[obs, start, stop] = mapGeneration(imread('2014-10-18 21.10.18.jpg'),[100, 100]);

imshow(obs)

smallSize = [15 15];

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


map = convertImageToArray(obsSmall, startSmall(2), startSmall(1), stopSmall(2), stopSmall(1));
atv = ArrayToVector(map);
realmap = atv.convertArray();

disp('realmap: ');
disp(realmap);

pause;

astar = AStar(realmap);
astar.AStarAlgorithm()