function res = gensamples(m, c, n)
%GENSAMPLES Summary of this function goes here
%   Detailed explanation goes here
x = rand(n,1);
y = c + m*x + sqrt(0.1) .* randn(n,1);
res = cat(2,x,y);
end

