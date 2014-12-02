% Create a socket to connect to the PhantomOmniSimulinkHost Program
% This program MUST BE RUNNING before running any Robot Raconteur scripts.
omni = RobotRaconteur.Connect('tcp://127.0.0.1:5150/PhantomOmniSimulinkHost/PhantomOmni');

% Joint position data to send.  This vector must be exactly length 16.
eIn = [0;0.5;0.7;zeros(13,1)];

% Have 500 iterations
steps = 500;

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
plot(eOutData(11,:));

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