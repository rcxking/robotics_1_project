% Robotics I Project
% Sean Bayman, Matt Corsaro, Bryant Pong
% Subproblem 0 for inverse kinematics

function theta=subproblem0(p,q,k)

% Normalize the vectors
p = p/norm(p);
q = q/norm(q);
k = k/norm(k);

% Using tangent equation and norms of sum and difference:
tannum = norm(p-q);
tandenom = norm(p+q);
theta = 2*atan2(tannum, tandenom);

% check sign
if k'*(cross(p,q))<0
  theta=-theta;
end
