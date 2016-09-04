function [ class ] = ag14774(name)
%CWK2 Summary of this function goes here
%   Detailed explanation goes here

%***OPTIONS*******************
%Choose classifier(1-knn, 2-knnweight, 3-hardcodedbounds)
c = 2;
%Plots?(0-no, 1-boundaries, 2-scatter)
p = 0;
%*****************************

trainT = train('T');
trainS = train('S');
trainV = train('V');
cov(trainT); %remove semicolon to print
cov(trainS);
cov(trainV);
trainSet = [trainT;trainS;trainV];

[magtestS,~,q] = fft2visual(name);
feats = extractFeat(magtestS,q);

if c==1
    class = knn(trainSet, [ones(10,1);repmat(2,10,1);repmat(3,10,1)], feats);
elseif c==2
    class = knnweight(trainSet, [ones(10,1);repmat(2,10,1);repmat(3,10,1)], feats);
else
    class = alternative(feats);
    %class=alternative2(trainSet, [ones(10,1);repmat(2,10,1);repmat(3,10,1)], feats);
end

if p==1
    clf;
    plot(trainSet,[ones(10,1);repmat(2,10,1);repmat(3,10,1)],c);
elseif p==2
    %clf;
    scatter(trainSet(1:10,1),trainSet(1:10,2),'r');
    hold on
    scatter(trainSet(11:20,1),trainSet(11:20,2),'b');
    scatter(trainSet(21:30,1),trainSet(21:30,2),'black');
    scatter(feats(:,1),feats(:,2),'green');
end

end

function features = extractFeat(mag,q)
p1 = 0;
p2 = 0;
max = 0;
min = 999999999;
for j = 1:400
    for k = 1:640
        if mag(j,k)<min
            min = mag(j,k);
        end
        if mag(j,k)>max
            max = mag(j,k);
        end
    end
end
for j = 1:400
    for k = 1:640
        newmag = (mag(j,k)-min)/(max-min);
        %newmag = mag(j,k);
        %inc = 1;
        if ((k-320)^2+(j-200)^2)<=60^2 && ((k-320)^2+(j-200)^2)>=10^2
            if ((atan((k-320)/(j-200))<=45*pi/180) && (atan((k-320)/(j-200))>=-45*pi/180)) || ((atan((k-320)/(j-200))>=85*pi/180) && (atan((k-320)/(j-200))<=90*pi/180)) || ((atan((k-320)/(j-200))>=-90*pi/180) && (atan((k-320)/(j-200))<=-85*pi/180))
                p1 = p1 + newmag^2;
                %inc = 1;
                %q(j,k)=0;
            end
        end
        if ((k-320)^2+(j-200)^2)<=60^2 && ((k-320)^2+(j-200)^2)>=10^2
            if ((atan((k-320)/(j-200))<=80*pi/180) && (atan((k-320)/(j-200))>=37*pi/180)) || ((atan((k-320)/(j-200))<=-37*pi/180) && (atan((k-320)/(j-200))>=-80*pi/180)) 
                p2 = p2 + newmag^2;
                %inc = 1;
                %q(j,k)=0;
            end
        end
        %if inc==0
        %    q(j,k)=0;
        %end
    end
end
%imagesc(log(abs(q)+1));
features = [p1 p2];
end

function class = knn(trainset, trainclass, test)
    k=1;
    [~,I] = pdist2(trainset, test,'euclidean', 'Smallest',k);
    classes=zeros(3,1);
    results = trainclass(I);
    for i = 1:k
        classes(results(i)) = classes(results(i)) + 1;
    end
    [~,c] = max(classes);
    if c==1
        class='T';
    elseif c==2
        class='S';
    else
        class='V';
    end
end

function class = alternative(test)
    if(test(1)<0.003&&test(2)>0.0025)
        class = 'V';
    elseif(test(2)<0.003&&test(1)>0.003)
        class = 'T';
    else
        class = 'S';
    end
end

%***********************IGNORE*********************
function class = alternative2(trainset, trainclass, test)
    trainT = trainset(trainclass==1,1:2);
    trainS = trainset(trainclass==2,1:2);
    trainV = trainset(trainclass==3,1:2);
    centroids = zeros(3,2);
    centroids(1,:) = mean(trainT);
    centroids(2,:) = mean(trainS);
    centroids(3,:) = mean(trainV);
    %voronoi(centroids(:,1),centroids(:,2));
    %hold on
    [~,c] = pdist2(centroids, test, 'euclidean', 'Smallest', 1);
    if c==1
        class='T';
    elseif c==2
        class='S';
    else
        class='V';
    end
end
%************************************************************

function class = knnweight(trainset, trainclass, test)
    k=3;
    [D,I] = pdist2(trainset, test,'euclidean', 'Smallest',k);
    classes=zeros(3,1);
    results = trainclass(I);
    for i = 1:k
        classes(results(i)) = classes(results(i)) + 1;
    end
    dists=ones(3,1);
    for i = 1:k
        if dists(results(i)) == 1
            dists(results(i)) = 0;
        end
        dists(results(i)) = dists(results(i)) + (D(i)/classes(results(i)));
    end
    [~,c] = min(dists);
    if c==1
        class='T';
    elseif c==2
        class='S';
    else
        class='V';
    end
end

function [Magq, Phaseq, q] = fft2visual(name)
f = imread(name); %read in image
z = fft2(double(f)); % do fourier transform
q = fftshift(z); % puts u=0,v=0 in the centre
Magq = abs(q); % magnitude spectrum
Phaseq=angle(q); % phase spectrum
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Usually for viewing purposes:
%imagesc(log(abs(q)+1));
%colorbar;
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%w = ifft2(ifftshift(q)); % do inverse fourier transform
%imshow(w); 
end

function class = train(letter)
    class = zeros(10,2);
    for i=1:10
        name = strcat(letter,int2str(i),'.GIF');
        [mag,~,q] = fft2visual(name);
        class(i,:) = extractFeat(mag,q);
    end
end

function plot(trainset, trainclass, c)
points = 0:0.0005:0.02;
[x, y] = meshgrid(points);
grid = [x(:) y(:)];
for i = 1:size(grid,1)
    v = grid(i,:);
    if c==1
        class=knn(trainset, trainclass, v);
    elseif c==2
        class=knnweight(trainset, trainclass, v);
    else
        class=alternative(v);
        %class=alternative2(trainset, trainclass, v);
    end
    if class=='T'
        scatter(v(:,1),v(:,2),40,'filled','r','s');
        hold on
    elseif class=='S'
        scatter(v(:,1),v(:,2),40,'filled','b','s');
        hold on
    else
        scatter(v(:,1),v(:,2),40,'filled','black','s');
        hold on
    end
end
end

