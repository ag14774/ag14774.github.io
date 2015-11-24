-----------------------------------------------
classes.lhs by Oliver Ray, 17/12/2014

This is a "literal Haskell script" which formed 
the basis of the lecture on types and classes

Symbols in this file have the following meaning:

> 

  is code to be interpreted by haskell

* 

  is code that is incorrect or already defined

? 

  is a query that you should try in GHCI
-----------------------------------------------

1) The Haskell type system as at the very heart of this approach to programming.

2) We have seen already in previous lectures how 

a. Type definitions derive new types from old types using lists, tuples, etc., with the "type" keyword: e.g. 

> type Point = (Int,Int)
> type Image = [Point]


b. Type polymorphism can be used to avoid redefining the same code over multiple types:  
   e.g. if we want to replace the first two functions below by the third:

> allEqInt :: Int -> Int -> Int -> Bool
> allEqInt x y z = (x==y) && (y==z)

> allEqChar :: Char-> Char-> Char-> Bool
> allEqChar x y z = (x==y) && (y==z)

* allEqAny :: a -> a -> a -> Bool
* allEqAny x y z = (x==y) && (y==z)

3) But, while this example does almost work, it fails with a compilation error:

    No instance for (Eq a)
      arising from a use of `=='
    In the second argument of `(&&)', namely `(y == z)'
    In the expression: (x == y) && (y == z)
    In an equation for `allEqualAny':
        allEqualAny x y z = (x == y) && (y == z)

4) This is because Haskell does not know that a is a type on which equality is defined 
   (since, as we said before, there are types like functions and IO on which we cant define equality)

5) This motivates the notion of "type classes" which are classes of types on which certain functions are known to exist. 
   The classic example is the Eq class (from the error message above) on which the equality operator (==) is defined.

6) Classes are introduced by the "class" keyword, as in the following definition of Eq in Haskell:

* class Eq a where
*  (==) :: a -> a -> Bool

7) Te idea is that all "instances" of this class (which include Bool, Char, Int and in fact most types apart from functions and IO) 
   must have have (==) defined on them

8) Using classes ,we can prefix type declarations with the "context" of its type variables as in 

> allEqAny :: Eq a => a -> a -> a -> Bool
> allEqAny x y z = (x==y) && (y==z)

9) Here "Eq a =>" is a context which states that a is type in the Eq class

10) A type is made a member of the class using the "instance" keyword and defining the required signature functions: e.g.

* instance Eq Bool where
*  True == True = True
*  False == False = True
*  _ == _ = True

11) We can even provide default definitions for signature functions at the class level. 
    (Of course, at least one of these must be overriden by each instance of the class)

* class Eq a where
*  (==), (/=) :: a -> a -> Bool
*  x /= y = not (x==y)
*  x == y = not (x/=y)

12) We can use ":type" in GHCI to return the type of a function: e.g.

? :type (==)

  (==) :: Eq a => a -> a -> Bool

13) Classes can also be members of (aka  inherit from) other classes using class contexts.
    On exaple the Haskell Ord  class which add ordering operators to equality.

* class Eq a => Ord a where
*  (<), (<=), (>), (>=) :: a -> a -> Bool
*  x <= y = (x<y || x==y)
*  x > y = y<x

14) The point is that all instances of this class must definethese signature functions as well as those for Eq.

15) Note that compound types can also belong to classes

* instance (Eq a, Eq b) => Eq (a,b) where
*  (x,y) == (u,v) = x=u && y=v

16) Since Point has this type, it is automatically in the equality class (so we don't get errors when comparing two points)

17) The actual data types themselves are defined by type constructors using the keyword "data". e.g.

* data Bool = True | False

> data Direction = North | East | South | West

> data List a = Nil | Cons a (List a)

18) Note that the latter is equivalent ot a Haskell list if we use the following syntactic sugar:

* List a = [a] 
* Nil = []   
* Cons = :

19) We can defing instances of these type in the usual way: e.g.

> onetwo, threefourfive :: List Int
> onetwo = Cons 1 (Cons 2 Nil)
> threefourfive = Cons 3 (Cons 4 (Cons 5 Nil))

20) But the following query fails (for the same reason as before):

? onetwo == threefourfive
 
     No instance for (Eq (List Int))
       arising from a use of `=='
     Possible fix: add an instance declaration for (Eq (List Int))
     In the expression: onetwo == onetwo
     In an equation for `it': it = onetwo == onetwo

21) We need to tell Haskell that our type are in Eq

> instance Eq a => Eq (List a) where
>  Nil == Nil = True
>  (Cons x xs) == (Cons y ys) = if (x == y) then (xs == ys) else False
>  _ == _ = False

22) And now the query succeeds!

? onetwo == threefourfive
  False

23) But there is still a problem:

? threefourfive

    No instance for (Show (List Int))
      arising from a use of `print'
    Possible fix: add an instance declaration for (Show (List Int))
    In a stmt of an interactive GHCi command: print it

24) To display our lists we need it to be in the Show class:

> instance Show a => Show (List a) where
>  show Nil = "[]"
>  show xs = "[" ++ (showall xs) ++ "]"
>    where
>      showall Nil = ""
>      showall (Cons x Nil) = (show x)
>      showall (Cons x xs) = (show x) ++ " " ++ (showall xs)

25) And now everything is fine

? threefourfive
  [3 4 5]

26) But Haskell can work out for itself how to display and test lists for equality if we use the "deriving" keyword

> data L a = N | C a (L a)
>  deriving (Eq,Show)
>
> ot, tff :: L Int
> ot = C 1 (C 2 N)
> tff = C 3 (C 4 (C 5 N))

27) Now everything works first time:

? ot
? C 1 (C 2 N)
?
? ot == tff
? False

28) Summary: The main keywords covered in the lecture are

* deriving
* instance
* data
* class
* type


29) DIAGRAM OF TYPE HIERARCHY TO FOLLOW