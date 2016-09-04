function inverse = myinv(A)
%myinv calculates the inverse of a 2x2 matrix
%
if ~isequal(size(A),[2 2])
    error('Matrix not 2x2');
end
determinant = A(1,1)*A(2,2)-A(1,2)*A(2,1);
if determinant == 0
    error('Matrix is not invertible');
end

inverse = (1/determinant) * [A(2,2) -A(1,2) ; -A(2,1) A(1,1)];

end

