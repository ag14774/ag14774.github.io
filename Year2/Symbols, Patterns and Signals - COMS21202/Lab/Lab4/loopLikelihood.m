function res = loopLikelihood(data)
%LOOPLIKELIHOOD Summary of this function goes here
%   Detailed explanation goes here

res = arrayfun(@(o) computeLikelihood(o, data), 0:0.01:1)';

end

