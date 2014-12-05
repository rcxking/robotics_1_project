% Robotics I Project
% Sean Bayman, Matt Corsaro, Bryant Pong
% Subproblem 1 for inverse kinematics
function theta=subproblem1(k,p,q)
  
% Normalize the three vectors
knormalized = k/norm(k);
pnormalized = p/norm(p);
qnormalized = q/norm(q);

% Use Subproblem 1 equations to find p' and q'
pprime = pnormalized - dot(pnormalized, knormalized)*knormalized;
qprime = qnormalized - dot(qnormalized, knormalized)*knormalized;

pprimen=pprime/norm(pprime);
qprimen=qprime/norm(qprime);

% Sign is taken care of here
theta=subproblem0(pprimen,qprimen,knormalized);
end
