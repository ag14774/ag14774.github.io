#---------Assignment1------------------------------------------
def MyPowMod(a,b,c):
    curr_base=1;
    while b>0:
        if b%2==0:
            a=(a*a)%c
            b=b//2
        else:
            curr_base=(curr_base*a)%c
            b=b-1
    return curr_base%c

def MyGCD(a,b):
    while True:
        temp = b
        b=a%b
        a = temp
        if b==0: return a

def MyLCM(a,b):
    return a*b//MyGCD(a,b)

def MyXGCD(a,b):
    rprev = a; r = b
    sprev = 1; s = 0
    tprev = 0; t = 1
    while True:
        q = rprev // r
        rnext = rprev - q*r #Remainder calculations
        rprev = r
        r = rnext
        snext = sprev - q*s #First Bezout coefficient
        sprev = s
        s = snext
        tnext = tprev - q*t #Second Bezout coefficient
        tprev = t
        t = tnext
        if r==0: return (rprev,sprev,tprev)

def Findx(a,b):
    res = MyXGCD(a,b)
    if res[0]<>1: return str(a) + " is not invertible modulo " + str(b)
    ZP.<x>=Integers(b)[]
    return ZP(res[1])

def MyFactor(a):
    if a==1: return "1 cannot be factorized"
    res = []
    prime = 2
    while a<>1:
        exponent=0
        temp=0
        while temp==0:
            temp=a%prime
            if temp==0:
                exponent=exponent+1
                a=a//prime
        if exponent<>0:
            res=res+[[prime,exponent]]
        prime=next_prime(prime)
    return res
#----------Assignment2------------------------
def addToItself(P, n):
    y = P
    for i in range(n):
        y = y + P
    return y

def perm2cycles(perm):
    n = perm.ncols()
    check = range(1,n+1,1)
    nextUnused = [0]
    def UpdateNext(i):
        next = i
        while True:
            next = next + 1
            if next == n:
                nextUnused[0] = n
                return
            if check[next] != 0:
                nextUnused[0] = next
                return
    def findCycle(start):
        def findCycleHelper(start2,end):
            check[start2] = 0
            item = perm[1][start2]
            #print str(item) +" " + str(end)
            if(item == end):
                return [item]
            return [item] + findCycleHelper(item, end)
        cycle = findCycleHelper(start,start)
        UpdateNext(start)
        return cycle
    cycles = []
    while(True):
        #print str(nextUnused[0])
        cycle = findCycle(nextUnused[0])
        if(len(cycle)>1):
            cycles.append(cycle)
        if nextUnused[0] == n:
            return cycles

def solveaxb(a,b,c):
    ZP = Integers(c)
    b = ZP(b)
    a = ZP(a)
    if b == 1:
        return 0
    res = 1
    acc = a
    while True:
        if acc==b:
            return res
        res = res + 1
        acc = ZP(acc*a)

def solveaxbE(a,b,E):
    b = E(b)
    a = E(a)
    if b == E(0):
        return 0
    res = 1
    acc = a
    while True:
        if acc==b:
            return res
        res = res + 1
        acc = E(acc+a)

#--------------Assignment3-------------------
def MyPhiFun(n):
    phi = 1
    if n == 1:
        return phi
    if n <  1:
        return 0
    factors = MyFactor(n)
    for factor in factors:
        phi = phi * (1-1/factor[0])
    phi = phi * n
    return phi

#WRONG
def FindNoOfGens(p):
    if not(is_prime(p)):
        return str(p) + " is not a prime!"
    orders = []
    result = p-1
    for i in range((p-1)/2,0,-1):
        if (p-1)%i==0:
            select = True
            for o in orders:
                if(o%i==0):
                    select = False
                    break
            if select:
                orders = orders + [i]
    for o in orders:
        result = result - (o - 1)
    return result - 1

def FindGens(p):
    if not(is_prime(p)):
        return str(p) + " is not a prime!"
    orders = []
    Fp = Integers(p)
    def isGenerator(x):
        for o in orders:
            if Fp(x^o)==1:
                return False
        return True
    for i in range((p-1)/2,1,-1):
        if (p-1)%i==0:
            orders = orders + [i]
    generators = [i for i in range(1,p,1) if isGenerator(i)]
    return generators

#def FindGens(p):
#    if ~is_prime(p):
#        return str(p) + " is not a prime!"
#    order = p-1
#    subOrders = []
#    for i in range(1,(p-1)/2+1,1):
#        subOrders

#--------Assignment 4-------------
def invert(n,m):
    (g,a,b) = MyXGCD(n,m)
    if g!=1:
        raise Exception("Not invertible!")
    return Integers(m)(a)

def findAllQ2():
    var('X')
    g7 = FiniteField(7)[X]
    res = []
    for i in range(7):
        for j in range(7):
            p = g7((j,i,1))
            if p.is_irreducible():
                print p
                res = res + [p]
    print len(res)

def findAutomorphisms(p,n,q):
    F.<x>=FiniteField(p^n,'x',modulus=q)
    AUT = []
    for k in range(n):
       f = x^p^k
       AUT = AUT + [f]
    return AUT

def solveQ73(q,i):
    var("a")
    var("b")
    var("c")
    F.<x> = GF(3^3,modulus=q)
    frob = x^3^i
    p = a + b*frob + c*frob^2
    p = p.full_simplify()
    coeffA = p.leading_coefficient(a).list()
    coeffB = p.leading_coefficient(b).list()
    coeffC = p.leading_coefficient(c).list()
    for k in range(3):
        if k>=len(coeffA):
            coeffA.append(0)
        if k>=len(coeffB):
            coeffB.append(0)
        if k>=len(coeffC):
            coeffC.append(0)
    VARS = ["u = ","v = ","w = "]
    for k in range(3):
        print VARS[k] + str(coeffA[k])+"a + "+str(coeffB[k])+"b + "+str(coeffC[k])+"c"

#--------Assignment 5-------------------
def Dist(x,y):
    return (x-y).hamming_weight()

#--------Assignment 6-------------------
def SystematicEncoding(G2,m):
    G = copy(G2);
    G = copy(G.rref());
    pivots = G.pivots();
    i = 0;
    while True:
        G.swap_columns(i,pivots[i])
        pivots = G.pivots();
        i = i + 1
        if i == len(pivots):
            break
    return m*G

def DecodeLinear(G, r):
    #G = copy(G.rref())
    #pivots = G.pivots();
    #i = 0;
    #while i != len(pivots):
    #    G.swap_columns(i,pivots[i])
    #    pivots = G.pivots();
    #    i = i + 1
    def minHamming(vecList):
        minH = 999999
        minV = vector(GF(2),[])
        for v in vecList:
            if v.hamming_weight() < minH:
                minH = v.hamming_weight()
                minV = v
        return minV
    L = G.image().list()
    coset = [i + r for i in L]
    leader = minHamming(coset)
    return r - leader

def parityCheck(r):
    L = []
    for i in range(1,2^r,1):
        l = Integer(i).digits(base=2,padto=r)
        l.reverse()
        L.append(l)
    return matrix(GF(2),L).transpose()

def DecHamming(r, m):
    v = copy(m)
    H = parityCheck(r)
    e = v * H.transpose()
    e = e.list()
    e.reverse()
    pos_of_error = ZZ(e,base=2) - 1
    if pos_of_error >= 0:
        v[pos_of_error] = 1 - v[pos_of_error]
    #Assume that parity bits are located in all
    #positions that are powers of 2
    k = 2^r-1 - r;
    V=VectorSpace(GF(2),2^r-1)
    zero = VectorSpace(GF(2),k).zero().list()
    parities = copy(V.zero())
    for i in range(r):
        parities[2^i-1] = 1
    dataList = []
    iden = identity_matrix(k)
    j=0
    for i in range(0,2^r-1):
        if parities[i] == 1:
            dataList.append(zero)
        else:
            dataList.append(iden.row(j).list())
            j = j + 1
    dataMat = matrix(GF(2),dataList).transpose()
    print "Corrected codeword: "
    print v
    print "Extracted data: "
    return dataMat * v

def solveQ6():
    H = parityCheck(4)
    print H
    iden = identity_matrix(15)
    for i in range(15):
        print str(iden[i])+" : "+ str(iden[i]*H.transpose())


def GenerateReedMatrix(Field, n, k, points):
    if len(points) != n:
        raise Exception("You need exactly "+str(n)+" points")
    if Field.modulus().degree() != k-1:
        raise Exception("Your field is not of the form GF(p^"+str(k-1)+")")
    genlist = []
    for i in range(k):
        row = []
        row = [Field(a^i) for a in points]
        genlist.append(row)
    genmat = matrix(Field,genlist)
    return genmat


#--------Extras-------------------

def sumOfPhiUpto(a):
    sum=0
    for i in range(1,a+1):
        sum=sum+MyPhi(i)
    return sum
