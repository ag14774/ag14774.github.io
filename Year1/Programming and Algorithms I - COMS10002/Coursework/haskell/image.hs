{-**********-}

--  define a Point as a pair of integers
type Point = (Int,Int)

--  define the point we call the origin 
origin :: Point
origin = (0,0)

-- define a function for scaling a point
-- horizontally by h and vertically by v
scale :: Int -> Int -> Point -> Point
scale h v (x,y) = (h*x,v*y)

-- flip a point about the  horizontal axis (y=0)
flipH :: Point -> Point
flipH (x,y) = (x,-y)

-- flip a point about the  vertical axis (x=0)
flipV :: Point -> Point
flipV (x,y) = (-x,y)
 
-- flip a point about the diagonal line (x=y)
flipD :: Point -> Point
flipD (x,y) = (y,x)

-- quarter turn clockwise
turnC :: Point -> Point
turnC (x,y) = (y,-x)

-- half turn 
turnB :: Point -> Point
turnB (x,y) = (-x,-y)
 
-- quarter turn anticlockwise
turnA :: Point -> Point
turnA (x,y) = (-y,x)

{-**********-}

-- define an Image as a list of points
type Image = [Point]

-- define a T-shaped image
t :: Image
t=[(0,1),(1,0),(1,1),(2,1)]

-- transform an Image point by point
pointwise :: (Point->Point) -> (Image->Image)
pointwise f = \ps -> [f p | p <- ps]

-- overlay two images
overlay :: Image -> Image -> Image
overlay i j = i ++ j

{-**********-}

x::Image
x = [(1,1), (1,-1), (2,0), (3,1), (3,-1)]

{-**********-}

shift :: Int -> Int -> Point -> Point
shift h v (x,y) = ((x+h),(y+v))

--minimum::[Int]->Int
--minimum list
--    | length(list) == 1 = head list
--    | otherwise = min (head list) (minimum tail list) 

{-**********-}

bounds :: Image -> (Int,Int,Int,Int)
bounds a = (minx a, maxx a, miny a,maxy a)
    where
        minx i=minimum [fst p|p<-i]
        maxx i=maximum [fst p|p<-i]
        miny i=minimum [snd p|p<-i]
        maxy i=maximum [snd p|p<-i]

{-**********-}

sideBySide::Image->Image->Image
sideBySide a b = overlay shifteda shiftedb
    where
        (minx,maxx,miny,maxy)=bounds a
        (minxb,maxxb,minyb,maxyb)=bounds b
        shifteda=pointwise (shift (-maxx) (-miny)) a
        shiftedb=pointwise (shift (-minxb) (-minyb)) b

{-**********-}

split::Int -> String -> String
split n [] = []
split n s  = (fst (splitAt n s)) ++ ['\n'] ++ split n (snd (splitAt n s))

{-**********-}

render :: Image -> String
render a = split (maxx-minx+3) (line (maxy+1))
    where
        (minx,maxx,miny,maxy)=bounds a
        line i
            | i==(miny-2)   = []
            | otherwise = [if elem (p,i) a then 'x' else if (p,i)==origin then '+' else if (p,i)==(0,i) then '|' else if (p,i)==(p,0) then '-' else '.'|p<-[minx-1..maxx+1]]++line (i-1)
