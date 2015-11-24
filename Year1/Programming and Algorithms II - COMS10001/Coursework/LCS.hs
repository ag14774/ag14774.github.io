lcs :: String -> String -> String
lcs x y = snd $ lcshelp (length x - 1) (length y - 1) (0,[])
    where
        lcshelp :: Int -> Int -> (Int,String) -> (Int,String)
        lcshelp (-1) j (a,b) = (a,b)
        lcshelp i (-1) (a,b) = (a,b)
        lcshelp i j z@(a,b)
            | (x!!i)==(y!!j) = lcshelp (i-1) (j-1) (a+1 , (x!!i) : b)
            | otherwise      = max' (lcshelp (i-1) j z) (lcshelp i (j-1) z)
            where
                max' :: (Int,String) -> (Int,String) -> (Int,String)
                max' (a,b) (c,d)
                    | a>=c      = (a,b)
                    | otherwise = (c,d)
