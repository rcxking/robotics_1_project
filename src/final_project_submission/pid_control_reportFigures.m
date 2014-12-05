%{
    pid_control.m - Driving code to facilitate in choosing PID parameters.
%}

close all; clear all;

% Joint position data to send in radians.
initialAngles = [ 0.0, 0.2, 0.7];
targetAngles  = [ 0.3, 0.5, 0.4];




% Create a socket to connect to the PhantomOmniSimulinkHost Program
% This program MUST BE RUNNING before running any Robot Raconteur scripts.
omni = RobotRaconteur.Connect('tcp://127.0.0.1:5150/PhantomOmniSimulinkHost/PhantomOmni');

% This vector holds the 9 PID gains (3 for each joint)
% Joint 1 is the rotational base
pGains = [500, 2000, 1500];
iGains = [0, 3000, 3000];
dGains = [20, 0.05, 0.2];

% Start with known initial state every time
eIn = [initialAngles, pGains, dGains, iGains, zeros(1,4)]';
omni.externalInput = eIn;
pause;



% Have 200 iterations
steps = 200;

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
figure(1);
color = {'r','g','b'};
for i = 1:3
    subplot(3,1,i);
    hold on;
    line([1 steps],[targetAngles(i) targetAngles(i)],'color','black');
    plot(eOutData(9+i,:),color{i});
    title(['Joint ',num2str(i)]);
    hold off;
end

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