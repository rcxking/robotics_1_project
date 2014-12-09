% Robotics I Project
% Sean Bayman, Matt Corsaro, Bryant Pong
% Subproblem 2 for inverse kinematics
function [theta1,theta2]=subproblem2(k1,k2,p,q)
%% Normalize the four vectors
k1 = k1/norm(k1);
k2 = k2/norm(k2);
p = p/norm(p);
q = q/norm(q);

%% compute dot products
k12=k1'*k2;
pk=p'*k2;
qk=q'*k1;

%% No solutions
if abs(k12^2-1)<eps;theta1=[];theta2=[];
disp('Error: No Solution to Subproblem 2');
return;end
a=[1 -k12;-k12 1]*[qk;pk]/(1-k12^2);
bb=(norm(p)^2-norm(a)^2-2*a(1)*a(2)*k12);
if abs(bb)<eps;bb=0;end
if bb<0;theta1=[NaN NaN];theta2=[NaN NaN];
disp('Error: No Solution to Subproblem 2');
return;end

%% check if there is only 1 solution
gamma=sqrt(bb)/norm(cross(k1,k2));
if abs(gamma)<eps;
  z1=[k1 k2 cross(k1,k2)]*[a;gamma];
  theta2=[subproblem1(k2,p,z1) NaN];
  theta1=[-subproblem1(k1,q,z1) NaN];  
  return
end  

%% general case: 2 solutions
theta1=zeros(2,1);
theta2=zeros(2,1);
z1=[k1 k2 cross(k1,k2)]*[a;gamma];
z2=[k1 k2 cross(k1,k2)]*[a;-gamma];
theta2(1)=subproblem1(k2,p,z1);
theta2(2)=subproblem1(k2,p,z2);
theta1(1)=-subproblem1(k1,q,z1);
theta1(2)=-subproblem1(k1,q,z2);