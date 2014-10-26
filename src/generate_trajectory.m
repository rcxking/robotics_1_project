%{
    generate_trajectory.m - Function to generate a smooth trajectory.

    Bryant Pong
    Robotics 1
    10/25/14

    Last Updated: Bryant Pong - 10/26/14: 11:06 AM
%}

%{
 This function generates a smooth trajectory as a function of time.  The
 trajectory is a 5th-Degree Polynomial:

 Position: S(t) = At^5 + Bt^4 + Ct^3 + Dt^2 + Et + F
 Velocity: S'(t) = 5At^4 + 4Bt^3 + 3Ct^2 + 2Dt + E
 Acceleration: A"(t) = 20At^3 + 12Bt^2 + 6Ct + 2D

 The boundary conditions are:

 Position:
 At t = 0, S(0) = initialPos
 At t = endingTime, S(endingTime) = endingPos

 Velocity:
 At t = 0, S'(0) = initialVel
 At t = endingTime, S'(endingTime) = endingVel

 Acceleration:
 At t = 0, S"(0) = initialAcc
 At t = endingTime, S"(endingTime) = endingAcc
 

 To use this function, in the main driver code:
 syms t
 traj(t) = generate_trajectory(...)
 ezplot(traj(t), [startTime, initialTime])

 To get the position at time t:
 syms t
 subs(traj(t), t, <value of t>)
 
%}

function [trajectory] = generate_trajectory(initialPos, endingPos, ...
                                            initialVel, endingVel, ...
                                            initialAcc, endingAcc, ...
                                            endingTime)
    syms t;
    
    A = [0, 0, 0, 0, 0, 1;
         endingTime^5, endingTime^4, endingTime^3, endingTime^2, endingTime, 1;
         0, 0, 0, 0, 1, 0;
         5*endingTime^4, 4*endingTime^3, 3*endingTime^2, 2*endingTime, 1, 0;
         0, 0, 0, 2, 0, 0;
         20*endingTime^3, 12*endingTime^2, 6*endingTime, 2, 0, 0];
     
    B = [initialPos; 
         endingPos; 
         initialVel; 
         endingVel; 
         initialAcc; 
         endingAcc;];
    
    coefficients = A\B;
    
    trajectory = coefficients(1)*t^5 + coefficients(2)*t^4 + ...
                 coefficients(3)*t^3 + coefficients(4)*t^2 + ...
                 coefficients(5)*t   + coefficients(6);
end

