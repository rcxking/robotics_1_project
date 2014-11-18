function [ x, y ] = computePosition( logicalImage )
%COMPUTEPOSITION Computes an estimaed center-of-mass position

I = size(logicalImage,1); % size in y direction
J = size(logicalImage,2); % size in x direction

x = 0; y = 0; count = 0;

for i = 1:I
    for j = 1:J
        
        if(logicalImage(i,j))
            y = y + i;
            x = x + j;
            count = count + 1;
        end
        
    end
end

y = round( y / count );
x = round( x / count );

end

