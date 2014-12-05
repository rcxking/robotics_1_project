%% Robotics I Project
% Sean Bayman, Matt Corsaro, Bryant Pong
% Inverse Kinematics Implementation
% This function is used as a Simulink function block.
% This function depends on rot.m, hat.m, subproblem0.m, subproblem1.m, subproblem2.m, and subproblem3.m

function theta = inverse(P0T)
%% Constants
x=[1;0;0];y=[0;1;0];z=[0;0;1];
% l0, l1, and l2 have length of 132 millimeters
l0 = 0.132;
l1 = 0.132;
l2 = 0.132;
% Zero configuration
p01 = [0; 0; l0];
p12 = [0; 0; 0];
p23 = [-l1; 0; 0];
p3T = [0; 0; -l2];
P1T = P0T - p01;
%Axes of rotation
k1 = z; k2 = y; k3 = y;

%% Inverse Kinematics
% use inverse kinematic functions to determine joint angles for given P0T
% and r0T
theta3 = subproblem3(k3,-p3T,p23,norm(P1T));
if isempty(theta3)
    disp('No solutions to Inverse Kinematics');
else
    p2T = [0 0; 0 0; 0 0];
    % Two possible p2Ts
    p2T(:,1) = p23 + rot(k3,theta3(1))*p3T;
    p2T(:,2) = p23 + rot(k3,theta3(2))*p3T;
    
    % use subproblem 2 to solve for two possible q1's and q2's
    [theta11 theta21] = subproblem2(k1,k2,p2T(:,1),P1T);
    [theta12 theta22] = subproblem2(k1,k2,p2T(:,2),P1T);
    % All possible solutions
    theta = [theta11' theta12'; theta21' theta22'; theta3(1) theta3(1) theta3(2) theta3(2)];
    % Make sure the solutions lie in the range [-pi, pi]
    theta(theta > pi) = -2*pi + theta(theta > pi);
    theta(theta < -pi) = 2*pi + theta(theta < -pi);
end

%% Choose the angles
% Choose the first set of angles
theta = theta(1:3).';

% Add additional angle offsets
theta(2) = theta(2) + 0.247;

end