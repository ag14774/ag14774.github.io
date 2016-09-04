function res = testNormal()
%This generates 1000 numbers from N(0,1)
%and calculates mean and variance
A = randn(1000,1);
res = [mean(A) ; var(A)];
end