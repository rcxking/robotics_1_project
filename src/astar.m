%{
    astar.m - Implementation of the A* path planning algorithm.

    Bryant Pong
    CSCI-4480
    11/8/14

    Last Updated: 11/8/14 - 3:40 PM
%}

%{
    The A* algorithm requires 3 parameters: 
    1) The starting node (in (X, Y) coordinates).
    2) The ending node (in (X, Y) coordinates).
    3) A 2-dimensional M x N matrix representing all the nodes.
%}

function [ finalPath ] = astar(startNode, endNode, nodes)

    % DEBUG ONLY - Print out the initial arguments:
    % VALIDATED
    disp('startNode: ');
    disp(startNode);
    disp('endNode: ');
    disp(endNode);
    disp('nodes: ');
    disp(nodes);
    
    % These variables hold the coordinates of the starting and ending
    % points:
    startX = startNode(1);
    startY = startNode(2);
    
    endX = endNode(1);
    endY = endNode(2);
    
    % DEBUG ONLY - Print out the coordinates: VALIDATED
    disp('Starting Coordinates: ');
    disp(startX);
    disp(startY);
    disp('Ending Coordinates: ');
    disp(endX);
    disp(endY);
    
    
    
    finalPath = 1;


end

