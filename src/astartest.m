%{
    Generate a test map.  The upper-left hand corner is (0, 0).
  
    The sample map looks like this:
    S X X 0 0 
    0 0 0 0 0
    0 0 0 X 0
    0 X 0 X 0
    0 0 0 0 E

    Where:
    1) "S" denotes the starting point.
    2) "E" denotes the ending points.
    3) "X" represents an obstacle.
    4) "0" represents a free space.

    The robot can move in any direction (even diagonally).  Thus, the
    shortest path in the above map is:
    S X X 0 0
    0 1 0 0 0
    0 0 2 X 0
    0 X 3 X 0
    0 0 0 4 E

    The starting point "S" is at (0, 0) and the ending point "E" is at (4,
    4) (0-Indexed).
%}

    map = [ 'S', 'X', 'X', '0', '0';
            '0', '0', '0', '0', '0';
            '0', '0', '0', 'X', '0';
            '0', 'X', '0', 'X', '0';
            '0', '0', '0', '0', 'E'];
        
    startNode = [0, 0];
    endNode = [4, 4];
    
    result = astar(startNode, endNode, map);