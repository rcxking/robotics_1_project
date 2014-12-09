C = -0.132; x = 0.03; y = 0.055;

p0 = [C+x;  y; 0];
p1 = [C+x; -y; 0];
p2 = [C-x; -y; 0];
p3 = [C-x;  y; 0];


moveud(C+2*x, y, 1);
pause;

moveud(p0(1),p0(2),1);
pause;
moveud(p1(1),p1(2),1);
pause;
moveud(p2(1),p2(2),1);
pause;
moveud(p3(1),p3(2),1);
pause;

moveud(C+2*x, y, 1);
