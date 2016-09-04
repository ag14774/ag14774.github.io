function likelihood = computeLikelihood(mu, data)
%COMPUTELIKELIHOOD Summary of this function goes here
array = 2/sqrt(2*pi) * exp(-2*(data-mu).^2);
likelihood = prod(array);

end

