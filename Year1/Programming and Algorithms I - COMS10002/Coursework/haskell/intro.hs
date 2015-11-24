{-**********-}

greeting :: String
greeting = "Hello "++"world!"

{-**********-}

value :: Int
value = 5

{-**********-}

fac :: Int -> Int
fac n
	| n==0  = 1
	| n>0   = n * fac (n-1)

{-**********-}

power :: Integer -> Integer -> Integer
power x n       
	| n==0	  = 1
	| even n = power (x*x) (div n 2)
	| odd n  = x * power x (n-1)


time :: Integer
time
    | (3^1000000)<3 = 1
    | otherwise                             = 0
{-**********-}

pow :: Integer -> Integer -> Integer
pow x 0         = 1
pow x n
    | m == 0    = let y=x*x in pow y d
    | otherwise = let y=pow x (n-1) in x*y
    where (d,m) = divMod n 2
    
{-**********-}

f :: Int -> Int
f n
    | n < 2     = n
    | otherwise = f (n-1) + f (n-2)

{-**********-}

g :: Int -> Int -> Int -> Int
g 0 a _  = a
g n a b  = g (n-1) b (a+b)

{-**********-}

mid :: Int -> Int -> Int -> Int
mid a b c
    | a==b      = a
    | a==c      = a
    | b==c      = b
    | otherwise = head [l | l<-[a,b,c] , l /= maximum [a,b,c], l /= minimum [a,b,c]]

{-**********-}

intRoot :: Int -> Int
intRoot a = test (a `div` 2)
  where
    test x
      | x*x > a = test (x-1)
      | otherwise = x

{-**********-}

numRoots :: Int -> Int -> Int -> Int
numRoots 0 0 0  = 0
numRoots 0 _ _  = 1
numRoots a b c
    | d>0       = 2
    | d==0      = 1
    | otherwise = 0
  where d=b^2-4*a*c
