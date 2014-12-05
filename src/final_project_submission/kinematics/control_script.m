%% Robotics I Project
%  Sean Bayman, Matt Corsaro, Bryant Pong
%  Script to control the OMNI robot

%% Constants

%% Coordinate bounds (X, Y, Z in world frame)
p0 = [-0.072;  0.06; 0];
p1 = [-0.072; -0.06; 0];
p2 = [-0.192; -0.06; 0];
p3 = [-0.192;  0.06; 0];
%% Move to each boundry point
moveud(p0);
moveud(p1);
moveud(p2);
moveud(p3);

%% Take A* inputs, then move to points with moveud