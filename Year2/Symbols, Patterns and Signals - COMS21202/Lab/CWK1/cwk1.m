function [ train, trainSel, testSel, centroids, classA, classB, classC, testDist, testClassA, testClassB, testClassC, intraClusterADist, intraClusterBDist, intraClusterCDist, classAmean, classBmean, classCmean, classAcov, classBcov, classCcov, classApdf, classBpdf, classCpdf ] = cwk1()

train = load('ag14774.train.txt');
test = load('ag14774.test.txt');

%PART 1.1
plotmatrix(train);
trainSel = [train(:,1) train(:,3)];
%*******************************************

%PART 1.2
[clusters, centroids] = kmeans(trainSel,3);

classA = trainSel(clusters == 1,1:2);
classB = trainSel(clusters == 2,1:2);
classC = trainSel(clusters == 3,1:2);

scatter(classA(:,1),classA(:,2),'red');
hold on
scatter(classB(:,1),classB(:,2),'black');
scatter(classC(:,1),classC(:,2),'blue');
%*******************************************

%PART 1.3
testSel = [test(:,1) test(:,3)];

testDist = pdist2(testSel, centroids);

[~,testClass] = min(testDist,[],2);

testClassA = testSel(testClass == 1,1:2);
testClassB = testSel(testClass == 2,1:2);
testClassC = testSel(testClass == 3,1:2);

scatter(testClassA(:,1),testClassA(:,2),'x','red');
scatter(testClassB(:,1),testClassB(:,2),'x','black');
scatter(testClassC(:,1),testClassC(:,2),'x','blue');
voronoi(centroids(:,1),centroids(:,2));
%************************************************

%PART 1.4
intraClusterADist = sum(pdist2(testClassA, centroids(1,:)));
intraClusterBDist = sum(pdist2(testClassB, centroids(2,:)));
intraClusterCDist = sum(pdist2(testClassC, centroids(3,:)));
%*************************************************

%PART 2.1
classAmean = mean (classA);
classBmean = mean (classB);
classCmean = mean (classC);

classAcov = cov (classA);
classBcov = cov (classB);
classCcov = cov (classC);

x = 0:0.1:10;
y = 0:0.1:10;
x=x';
y=y';

[X,Y] = meshgrid(x,y);

classApdf = mvnpdf([X(:) Y(:)],classAmean,classAcov);
classBpdf = mvnpdf([X(:) Y(:)],classBmean,classBcov);
classCpdf = mvnpdf([X(:) Y(:)],classCmean,classCcov);

classApdf = reshape(classApdf,size(X));
classBpdf = reshape(classBpdf,size(X));
classCpdf = reshape(classCpdf,size(X));

Ea = 2*pi * sqrt(det(classAcov));
Eb = 2*pi * sqrt(det(classBcov));
Ec = 2*pi * sqrt(det(classCcov));

Pa = (1/Ea) * exp(-3);
Pb = (1/Eb) * exp(-3);
Pc = (1/Ec) * exp(-3);

contour(X,Y,classApdf,[Pa Pa]);
contour(X,Y,classBpdf,[Pb Pb]);
contour(X,Y,classCpdf,[Pc Pc]);
%*****************************************************

%Part 2.2
LRAB = classApdf ./ classBpdf;
LRAC = classApdf ./ classCpdf;
LRBC = classBpdf ./ classCpdf;

LRAB = reshape(LRAB,size(X));
LRAC = reshape(LRAC,size(X));
LRBC = reshape(LRBC,size(X));

contour(X,Y,LRAB,[1 1],'r');
contour(X,Y,LRAC,[1 1],'b');
contour(X,Y,LRBC,[1 1],'black');
%******************************************************

%Part 2.3:
classApdf = mvnpdf([X(:) Y(:)],classAmean,[1 0 ; 0 1]);
classBpdf = mvnpdf([X(:) Y(:)],classBmean,[1 0 ; 0 1]);
classCpdf = mvnpdf([X(:) Y(:)],classCmean,[1 0 ; 0 1]);

LRAB = classApdf ./ classBpdf;
LRAC = classApdf ./ classCpdf;
LRBC = classBpdf ./ classCpdf;

LRAB = reshape(LRAB,size(X));
LRAC = reshape(LRAC,size(X));
LRBC = reshape(LRBC,size(X));

LRAB2 = classApdf ./ classBpdf *2;
LRAC2 = classApdf ./ classCpdf *2;

LRAB2      = reshape(LRAB2,size(X));
LRAC2      = reshape(LRAC2,size(X));

Ea = 2*pi * sqrt(det([1 0 ; 0 1]));
Eb = 2*pi * sqrt(det([1 0 ; 0 1]));
Ec = 2*pi * sqrt(det([1 0 ; 0 1]));

Pa = (1/Ea) * exp(-3);
Pb = (1/Eb) * exp(-3);
Pc = (1/Ec) * exp(-3);

contour(X,Y,classApdf,[Pa Pa]);
contour(X,Y,classBpdf,[Pb Pb]);
contour(X,Y,classCpdf,[Pc Pc]);

contour(X,Y,LRAB,[1 1],'r');
contour(X,Y,LRAC,[1 1],'b');
contour(X,Y,LRBC,[1 1],'black');

contour(X,Y,LRAB2,[1 1],'r');
contour(X,Y,LRAC2,[1 1],'b');
%*******************************************

end

