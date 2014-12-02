%% boundry_draw.m

%% Robotics I Project
%  Sean Bayman, Matt Corsaro, Bryant Pong
%  Move robot to the four boundry locations

%% Four Joint Angles
p0 = [0.5658, 0.0175, 0.7167];
p1 = [0.7558, 0.2240, 0.1052];
p2 = [-0.5658, 0.0175, 0.7167];
p3 = [-0.7558, 0.2240, 0.1052];

%% Initialzations
% Create a socket to connect to the PhantomOmniSimulinkHost Program
% This program MUST BE RUNNING before running any Robot Raconteur scripts.
omni = RobotRaconteur.Connect('tcp://127.0.0.1:5150/PhantomOmniSimulinkHost/PhantomOmni');

% This vector holds the 9 PID gains (3 for each joint)
% Joint 1 is the rotational base
pGains = [500, 2000, 1500];
iGains = [0, 3000, 3000];
dGains = [20, 0.05, 0.2];

% Start with known initial state every time
eIn = [0, 0.25, 0, pGains, dGains, iGains, zeros(1,4)]';
omni.externalInput = eIn;
pause;

%% Position 0
% Joint position data to send in radians.
targetAngles = p0;

% Have 500 iterations
steps = 500;

% Construct the actual joint buffer to send.  length(buffer) = 16;
eIn = [targetAngles, pGains, dGains, iGains, zeros(1,4)]';

% Good policy to zero the buffer for reading output data.  This buffer
% must be a length of exactly 32.
eOutData = zeros(32,steps);

% Send the desired joint position data to the Omni.
omni.externalInput = eIn;


% Read the output buffer 500 times.
for k=1:steps
    eOutData(:,k) = omni.extraOutput;
end


%{
The output buffer has the following format:
Elements 1 - 3: Desired joint angles
Elements 4 - 6: Commanded joint torques
Elements 7 - 9: Control Loop Error
Elements 10 - 12: Current Joint Angles
Elements 13 - 32: Spare elements/not currently used
%}
plot(eOutData(10,:));

%{
hold off;
for i = 1:2
    figure(i); plot(eOutData(2,:))
    for k=1:steps
        eOutData(:,k) = omni.extraOutput;
    end
    
    eIn = [0;0.5;0.7;zeros(13,1)];
    omni.externalInput = eIn;
end
%}

%% Position 1
% Joint position data to send in radians.
targetAngles = p1;

% Have 500 iterations
steps = 500;

% Construct the actual joint buffer to send.  length(buffer) = 16;
eIn = [targetAngles, pGains, dGains, iGains, zeros(1,4)]';

% Good policy to zero the buffer for reading output data.  This buffer
% must be a length of exactly 32.
eOutData = zeros(32,steps);

% Send the desired joint position data to the Omni.
omni.externalInput = eIn;


% Read the output buffer 500 times.
for k=1:steps
    eOutData(:,k) = omni.extraOutput;
end


%{
The output buffer has the following format:
Elements 1 - 3: Desired joint angles
Elements 4 - 6: Commanded joint torques
Elements 7 - 9: Control Loop Error
Elements 10 - 12: Current Joint Angles
Elements 13 - 32: Spare elements/not currently used
%}
plot(eOutData(10,:));

%{
hold off;
for i = 1:2
    figure(i); plot(eOutData(2,:))
    for k=1:steps
        eOutData(:,k) = omni.extraOutput;
    end
    
    eIn = [0;0.5;0.7;zeros(13,1)];
    omni.externalInput = eIn;
end
%}

%% Position 2
% Joint position data to send in radians.
targetAngles = p2;

% Have 500 iterations
steps = 500;

% Construct the actual joint buffer to send.  length(buffer) = 16;
eIn = [targetAngles, pGains, dGains, iGains, zeros(1,4)]';

% Good policy to zero the buffer for reading output data.  This buffer
% must be a length of exactly 32.
eOutData = zeros(32,steps);

% Send the desired joint position data to the Omni.
omni.externalInput = eIn;


% Read the output buffer 500 times.
for k=1:steps
    eOutData(:,k) = omni.extraOutput;
end


%{
The output buffer has the following format:
Elements 1 - 3: Desired joint angles
Elements 4 - 6: Commanded joint torques
Elements 7 - 9: Control Loop Error
Elements 10 - 12: Current Joint Angles
Elements 13 - 32: Spare elements/not currently used
%}
plot(eOutData(10,:));

%{
hold off;
for i = 1:2
    figure(i); plot(eOutData(2,:))
    for k=1:steps
        eOutData(:,k) = omni.extraOutput;
    end
    
    eIn = [0;0.5;0.7;zeros(13,1)];
    omni.externalInput = eIn;
end
%}

%% Position 3
% Joint position data to send in radians.
targetAngles = p3;

% Have 500 iterations
steps = 500;

% Construct the actual joint buffer to send.  length(buffer) = 16;
eIn = [targetAngles, pGains, dGains, iGains, zeros(1,4)]';

% Good policy to zero the buffer for reading output data.  This buffer
% must be a length of exactly 32.
eOutData = zeros(32,steps);

% Send the desired joint position data to the Omni.
omni.externalInput = eIn;


% Read the output buffer 500 times.
for k=1:steps
    eOutData(:,k) = omni.extraOutput;
end


%{
The output buffer has the following format:
Elements 1 - 3: Desired joint angles
Elements 4 - 6: Commanded joint torques
Elements 7 - 9: Control Loop Error
Elements 10 - 12: Current Joint Angles
Elements 13 - 32: Spare elements/not currently used
%}
plot(eOutData(10,:));

%{
hold off;
for i = 1:2
    figure(i); plot(eOutData(2,:))
    for k=1:steps
        eOutData(:,k) = omni.extraOutput;
    end
    
    eIn = [0;0.5;0.7;zeros(13,1)];
    omni.externalInput = eIn;
end
%}