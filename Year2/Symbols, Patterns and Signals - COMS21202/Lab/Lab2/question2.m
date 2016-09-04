function res = question2(X)
%QUESTION2 Summary of this function goes here
%   Detailed explanation goes here
mymu = [2 2];
mycov = [4 2 ; 2 6];
res = mvnpdf(X,mymu,mycov);

end

