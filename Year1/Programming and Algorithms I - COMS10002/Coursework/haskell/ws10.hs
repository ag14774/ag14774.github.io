partition ::  (a->Bool) -> [a] -> ([a],[a])
partition _ [] = ([],[])
partition f (x:xs)
    | f x       = (x:ys,zs)
    | otherwise = (ys,x:zs)
    where
        (ys,zs) = partition f xs

qSort :: [Int]->[Int]
qSort [] = []
qSort (x:xs) = (qSort ls)++[x]++(qSort rs)
    where 
        (ls,rs)=partition (<=x) xs

pass :: [Int]->[Int]
pass [] = []
pass [x]= [x]
pass (x:n:xs) 
    | x<=n      = x:(pass (n:xs))
    | otherwise = n:(pass (x:xs))

bSort :: [Int]->[Int]
bSort []  = []
bSort [a] = [a]
bSort list= (bSort $ init x) ++ [last x]
    where x=pass list
