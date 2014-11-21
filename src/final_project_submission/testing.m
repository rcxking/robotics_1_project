close all; clear all; clc;

[obs, start, stop] = mapGeneration(imread('2014-10-18 21.10.18.jpg'),[100, 100]);

imshow(obs)


smallSize = [20 20];

obsSmall = imresize(obs, smallSize);
startSmall = round(start.*[smallSize(1)/size(obs,1) smallSize(2)/size(obs,2)]);
stopSmall = round(stop.*[smallSize(1)/size(obs,1) smallSize(2)/size(obs,2)]);
