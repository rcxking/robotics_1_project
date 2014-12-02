%{

Sean Bayman
Robotics I project, Fall 2014

Example for how to use mapGeneration.m


Basic usage:

    [obstacleMapForAstar, startPos, stopPos] = mapGeneration(cameraImage);



Required files:
    mapGeneration.m
    sortPolyFromClockwiseStartingFromTopLeft.m
    All files in inexact_alm_rpca and its subfolders

As of 2014-12-01, there may still be room for some optimization in this
function with regard to memory usage. If it is a problem on the lab 
computers, I will look into it.

%}

close all; clear all; clc;

image = imread('2014-12-02 16.07.23.jpg');
%image = imread('2014-11-09 18.21.53.jpg');

% Can optionally specify the output dimensions, working resolution, filter
% order, and noise suppression threshold.
[obs, start, stop, big] = mapGeneration(image);

% 0.5 subtracted since resolution is so small for obs. This puts the line
% near the middle of the original pixel location.
bigStart = (double(start)-0.5) .* size(big) ./ size(obs);
bigStop  = (double(stop)-0.5)  .* size(big) ./ size(obs);

figure(1)
imshow(big)
title({'High resolution obstacle map';
       ['Start Position: ', num2str(bigStart(1)), ', ', num2str(bigStart(2)),...
       '.  Stop Position: ', num2str(bigStop(1)), ', ', num2str(bigStop(2)),'.']});
line([bigStart(1) bigStart(1)], [1 size(big,1)], 'Color', [0 1 0]);
line([1 size(big,2)], [bigStart(2) bigStart(2)], 'Color', [0 1 0]);
line([bigStop(1) bigStop(1)], [1 size(big,1)], 'Color', [1 0 0]);
line([1 size(big,2)], [bigStop(2) bigStop(2)], 'Color', [1 0 0]);


figure(2)
imshow(obs)
title({'Obstacle map for Astar';
       ['Start Position: ', num2str(start(1)), ', ', num2str(start(2)),...
       '.  Stop Position: ', num2str(stop(1)), ', ', num2str(stop(2)),'.']});
line([start(1) start(1)], [1 size(obs,1)], 'Color', [0 1 0]);
line([1 size(obs,2)], [start(2) start(2)], 'Color', [0 1 0]);
line([stop(1) stop(1)], [1 size(obs,1)], 'Color', [1 0 0]);
line([1 size(obs,2)], [stop(2) stop(2)], 'Color', [1 0 0]);

