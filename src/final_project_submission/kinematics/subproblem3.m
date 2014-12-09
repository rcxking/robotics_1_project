%% Robotics I Project
% Sean Bayman, Matt Corsaro, Bryant Pong
% Subproblem 3 for inverse kinematics
function theta=subproblem3(k,p,q,d)
%% Make sure k is a unit vector
k = k/norm(k);

%% Solve for pprime, qprime, and dprime
pprime=p-k'*p*k;
qprime=q-k'*q*k;
dprime=d^2-(k'*(p-q))^2;

%% No solution
if dprime<0;theta=[];return;end

%% One solution
if dprime==0;theta=subproblem1(k,pprime/norm(pprime),qprime/norm(qprime));return;end

%% Two solutions
cosarg=(norm(pprime)^2+norm(qprime)^2-dprime)/(2*norm(pprime)*norm(qprime));
if abs(cosarg)>1; theta=[];return;end   % No Solution
phi=acos(cosarg);
theta0=subproblem1(k,pprime/norm(pprime),qprime/norm(qprime));
theta=zeros(2,1);

theta(1)=theta0+phi;
theta(2)=theta0-phi;
