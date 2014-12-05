function dummy = moveud(x,y,lift)
%% moveud.m Moves over to a point in the x and y, then down and back up
%  Robotics I Project
%  Sean Bayman, Matt Corsaro, Bryant Pong
%% Constant heights, can be editted
zup = 0.05;
zdown = -0.025;

%% Optional "lift" parameter: set for lift, clear for no lift
%      Default: no lift

if( 3 > nargin )
    lift = 0;
end

%% Points in the X, Y, Z
pointup = [x; y; zup];
pointdown = [x; y; zdown];
%% Use Inverse Kinematics to Find Joint Angles q
qdown = inverse(pointdown);
qup = inverse(pointup);
%% Move over above, then down, then back up
if(lift)
    pid_control_fn(qup);
end
pid_control_fn(qdown);
if(lift)
    pid_control_fn(qup);
end

%% Output doesn't matter
dummy =x;
end

