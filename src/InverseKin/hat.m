function M = hat( k )
%hat creates cross product matrix for a vector
M = [0 -k(3) k(2); k(3) 0 -k(1); -k(2) k(1) 0];
end

