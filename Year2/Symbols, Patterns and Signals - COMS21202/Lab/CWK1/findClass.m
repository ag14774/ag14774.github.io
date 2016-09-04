function res = findClass(index,classAIndex, classBIndex, ~)
%FINDCLASS Summary of this function goes here
%   Detailed explanation goes here
if ismember(index,classAIndex)
    res = 1;
elseif ismember(index,classBIndex)
    res = 2;
else
    res = 3;
end

