rep::Int->String->String
rep n s
    | n<=0 = []
rep n s = foldl (++) [] (replicate n s)

allIn::Eq a => [a]->[a]->Bool
allIn xs ys = foldl (\acc x->(acc&&x)) True (map (`elem` ys) xs)

csh::Int->String->String
csh 0 s = s
csh _ [] = []
csh n s@(x:xs)
    | n>0       = csh (n-1) ((last s):(init s))
    | otherwise = csh (n+1) (xs ++ [x])

prm::String->String->Bool
prm [] [] = True
prm _ []  = False
prm [] _  = False
prm s@(x:xs) t = (x `elem` t) && prm xs (filter' x t)
    where filter'::Char -> String -> String
          filter' _ [] = []
          filter' a (q:qs)
            | a == q    = qs
            | otherwise = q:filter' a qs



lrs::String->String
lrs "" = ""
lrs s = applyfuc flist s [] True
    where
        flist=[((take n).(drop j))|n<-(reverse [1..((length s)-1)]),j<-[0..((length s)-n)]]
        applyfuc::[String->String]->String->[String]->Bool->String --FUNCTION LIST->WHOLE STRING->SUBS SO FAR->CONTINUE?->NEW RESULT
        applyfuc [] _ _ True = ""
        applyfuc _ _ r False = last r
        applyfuc (f:fs) s r True = applyfuc fs s (r++[(f s)]) (not((f s) `elem` r))


choose::Int->Int->Integer
choose _ 0 = 1
choose n k
    | n == k    = 1
    | otherwise = toInteger(product [toInteger(k+1)..(toInteger n)]) `div` toInteger(product [1..toInteger(n-k)])


pasRow::Int->[Integer]
pasRow n = [choose (n-1) k|k<-[0..(n-1)]]


renderLast::Int->String --Height->Last String
renderLast h = renderLastHelp (pasRow h) (map length (map show (pasRow (h-1))))
    where renderLastHelp::[Integer]->[Int]->String --row->lengths->laststring
          renderLastHelp [x] [] = show x
          renderLastHelp (x:xs) (y:ys) = (show x) ++ (replicate y '.') ++ renderLastHelp xs ys

renderRow::[Integer]->String->Bool->String
renderRow [] [] _     = []
renderRow [] (x:xs) _ = '.':renderRow [] xs True
renderRow nums@(x:xs) (y:ys) dot
    | dot==True && y=='.'   = '.':renderRow nums ys ((head ys)=='.')
    | dot==False && y/='.'  = '.':renderRow nums ys False
    | dot==True && y/='.'   = '.':renderRow nums ys ((head ys)/='.')
    | dot==False && y=='.'  = (centerAlign x (calcDots (y:ys)))++renderRow xs (drop ((length (centerAlign x (calcDots (y:ys))))-1) ys) True

pas::Int->String
pas n
    | n==1 = "1\n"
    | n<=0 = ""
pas n = (pasHelp (renderLast n) (n-1)) ++ "\n" ++ renderLast n ++ "\n"
    where pasHelp::String->Int->String
          pasHelp s 1 = renderRow (pasRow 1) s True
          pasHelp s i = (pasHelp tempRow (i-1)) ++ "\n" ++ tempRow
            where tempRow = renderRow (pasRow i) s True

calcDots::String->Int
calcDots (x:xs)
    | x /= '.' = 0
    | otherwise = 1 + calcDots xs

centerAlign::Integer->Int->String --Number to insert->Space to Insert->Aligned text
centerAlign n 2 = show n
centerAlign n d = (replicate (ceiling (fromIntegral(d - (length $ show n)) / 2)) '.')  ++ (show n)

{-*****************************************************************************************************************************-}

data Dir = North | East | South | West
    deriving (Eq,Enum,Show)

type DB = ([(Int,Int)],[(Int,Int)],[(Int,Int)],(Int,Int),Dir)

--turnRight::Dir->Dir
--turnRight West = North
--turnRight d    = succ d

--turnLeft::Dir->Dir
--turnLeft North = West
--turnLeft d     = pred d

switchColor::DB->DB --(List of blacks the ant passed,List of whites the ant passed,list of initial blacks,point to change,dir)->New List
switchColor (b,w,i,p,d)
    | (p `elem` i) || (p `elem` b) = ([x|x<-b,x/=p],w++[p],[q|q<-i,q/=p],p,turnLeft d)
    | otherwise  = (b++[p],[y|y<-w,y/=p],i,p,turnRight d)
    where turnRight::Dir->Dir
          turnRight West   = North
          turnRight dir    = succ dir
          turnLeft::Dir->Dir
          turnLeft North   = West
          turnLeft dir     = pred dir

boundBox::DB->(Int,Int,Int,Int)
boundBox (b,w,i,a,d) = (minx,maxx,miny,maxy)
    where minx=minimum ((map fst b)++(map fst w)++[fst a])
          maxx=maximum ((map fst b)++(map fst w)++[fst a])
          miny=minimum ((map snd b)++(map snd w)++[snd a])
          maxy=maximum ((map snd b)++(map snd w)++[snd a])

--split::Int -> String -> String
--split n [] = []
--split n s  = (fst (splitAt n s)) ++ ['\n'] ++ split n (snd (splitAt n s))

moveNUpdate::DB->DB
moveNUpdate (b,w,i,a,d) = move (switchColor (b,w,i,a,d))
    where
        move::DB->DB
        move (b,w,i,(x,y),d)
            | d == North = (b,w,i,(x,y+1),d)
            | d == East  = (b,w,i,(x+1,y),d)
            | d == South = (b,w,i,(x,y-1),d)
            | d == West  = (b,w,i,(x-1,y),d)

render::DB->String
render points@(b,w,i,a,d) = split (maxx-minx+1) [decide (x,y)|y<-(reverse [miny..maxy]),x<-[minx..maxx]]
    where (minx,maxx,miny,maxy)=boundBox points
          decide::(Int,Int)->Char
          decide pnt
            | (pnt == a)&&((pnt `elem` b)||(pnt `elem` i)) = '*'
            | (pnt == a)                                   = '+'
            | (pnt `elem` b)||(pnt `elem` i)               = 'x'
            | otherwise                                    = '.'
          split::Int -> String -> String
          split n [] = []
          split n s  = (fst (splitAt n s)) ++ ['\n'] ++ split n (snd (splitAt n s))



origin::(Int,Int)
origin=(0,0)

ant::[(Int,Int)]->Int->String
ant i 0 = (if (origin `elem` i) then '*' else '+'):"\n"
ant i n = render (antHelper ([],[],i,origin,West) n)
    where
        antHelper::DB->Int->DB
        antHelper dta 1 = moveNUpdate dta
        antHelper dta n = antHelper (moveNUpdate dta) (n-1)

{-********************************************-}

a::(t1->t2)->t1->t2
a f x = f x

b::Int->Int
b n = n

c::Bool->Int
c True  = 1
c False = 0


e::[Int]
e=[1..]

d::Int
d=2

{-*********************************************-}
