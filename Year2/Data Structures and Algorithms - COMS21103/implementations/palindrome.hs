{-This returns the longest palindromic subsequence-}
pal :: String -> String
pal x = snd $ lcshelp 0 (length x - 1) (0,[])
    where
        lcshelp :: Int -> Int -> (Int,String) -> (Int,String)
        lcshelp i (-1) (a,b) = (a,b)
        lcshelp i j z@(a,b)
            | i==length x    = z
            | (x!!i)==(x!!j) = lcshelp (i+1) (j-1) (a+1 , (x!!i) : b)
            | otherwise      = max' (lcshelp (i+1) j z) (lcshelp i (j-1) z)
            where
                max' :: (Int,String) -> (Int,String) -> (Int,String)
                max' (a,b) (c,d)
                    | a>=c      = (a,b)
                    | otherwise = (c,d)

{-length of the longest palindromic subsequence-}
pal_simple :: String -> Int
pal_simple x = pal_length 0 (length x - 1) 0
    where
        pal_length :: Int -> Int -> Int -> Int
        pal_length l r acc
            | (l==length x) || r==(-1)  = acc
            | (x!!l)==(x!!r)            = pal_length (l+1) (r-1) (acc+1)
            | otherwise                 = max (pal_length (l+1) r acc) (pal_length l (r-1) acc) 
