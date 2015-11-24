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

power :: Int -> Int -> Int
power _ 0 = 1
power x n
	| even n = power (x*x) (div n 2)
	| odd n  = x * power x (n-1)

{-**********-}

pow :: Int -> Int -> Int
pow _ 0         = 1
pow x n
    | m == 0    = let y=x*x in pow y d
    | otherwise = let y=pow x (n-1) in x*y
    where (d,m) = divMod n 2
    
{-**********-}

tupleize :: Integral a => a -> Int -> ( a, Int )
tupleize a b = (a,b)

sumList :: Num a => [a] -> a
sumList list
	| null list = 0
	| otherwise = head list + ( sumList $ tail list )

-- sumList [] = 0
--sumList (x:xs) = x + sumList xs

quicksort :: Ord a => [a] -> [a]
quicksort [] = []
quicksort (x:xs) = ( quicksort [ n | n <- xs, n <= x ] ) ++ [x] ++ ( quicksort [ n | n <- xs, n > x ] )
