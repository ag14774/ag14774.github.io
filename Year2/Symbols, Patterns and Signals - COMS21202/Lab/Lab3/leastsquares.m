function res = leastsquares(X, Y)
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here
Xnew = cat(2,ones(size(X,1),1),X);
%Xnew = X;
XnewT = Xnew';
res =  (XnewT * Xnew) \ (XnewT * Y);

end

