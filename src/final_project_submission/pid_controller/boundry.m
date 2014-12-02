%% Robotics I Project
%  Sean Bayman, Matt Corsaro, Bryant Pong
%  Draw dots on the boundry of the workspace using pid_control_fn.m

%% Coordinate bounds (Joint angles)
p0 = [0.5658; 0.0175; 0.7167];
p1 = [0.7558; 0.2240; 0.1052];
p2 = [-0.5658; 0.0175; 0.7167];
p3 = [-0.7558; 0.2240; 0.1052];

%% Move to each point
pid_control_fn(p0);
pid_control_fn(p1);
pid_control_fn(p2);
pid_control_fn(p3);