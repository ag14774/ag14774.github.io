import Prelude hiding (Num)
import qualified Prelude (Num)

type Num = Integer
type Z   = Integer
type Var = String
type State = (Var -> Z)
type T     = Bool

data Aexp = N Num | V Var | Neg Aexp
          | Add Aexp Aexp | Mult Aexp Aexp | Sub Aexp Aexp
          deriving (Show, Eq, Read)

data Bexp = B T
          | Eq Aexp Aexp | Le Aexp Aexp
          | Not Bexp | And Bexp Bexp | Imp Bexp Bexp

a::Aexp
a = Mult (Add (V "x") (V "y")) $ Sub (V "z") (N 1)

b::Bexp
b = Not $ Eq (Add (V "x") (V "y")) (N 4)

n_val::Num -> Z
n_val n = n

a_val::Aexp -> State -> Z
a_val      (N x) _ = n_val x
a_val      (V x) q = q x
a_val    (Neg x) q = - (a_val x q)
a_val (Mult x y) q = (a_val x q) * (a_val y q)
a_val (Add  x y) q = (a_val x q) + (a_val y q)
a_val (Sub  x y) q = (a_val x q) - (a_val y q)

b_val::Bexp -> State -> T
b_val (B   t   ) _  = t
b_val (Eq  x y ) q  = (a_val x q) == (a_val y q)
b_val (Le  x y ) q  = (a_val x q) <= (a_val y q)
b_val (Not x   ) q  = not (b_val x q)
b_val (And x y ) q  = (b_val x q) && (b_val y q)
b_val (Imp x y ) q  = not (b_val x q) || (b_val y q)

subst_aexp :: Aexp -> Var -> Aexp -> Aexp
subst_aexp (N    n  ) _ _ = (N n)
subst_aexp (V    x  ) v a = if x /= v then (V x) else a
subst_aexp (Add  x y) v a = Add  (subst_aexp x v a) (subst_aexp y v a)
subst_aexp (Sub  x y) v a = Sub  (subst_aexp x v a) (subst_aexp y v a)
subst_aexp (Mult x y) v a = Mult (subst_aexp x v a) (subst_aexp y v a)

--We have a total function B, if for all n of Bexp and
--s of State, there is exactly one v of T s.t. B[n] s = v
--Base cases:
--B[n] s when n = TRUE B[TRUE] s = True
--B[n] s when n = FALSE B[FALSE] s = False
--B[n] s when n = A[.] == A[.], A is a total function -> set the first A[.]=a
--and the second A[.]=b then a==b
--Same for <=
--
--B[n] s when n = not(B[n'] s). Assuming this holds for B[n'] s=b -  not(b)
--B[n] s when n = B[n'] s && B[n''] s. Assuming this holds  b1 && b2

--Let s and s' be two states satisfying that s x=s' x for all x in FV(a). Then
--A[a]s = A[a]s'
--
--From the definition of A[.], A[n]s = N[n] as well as A[n]s' = N[n]. It hold for this case.
--If A[x]s=s x and A[x]s' = s' x, using the lemma s x = s' x. Holds
--If A[a1 + a2] s= A[a1]s + A[a2]s and A[a1+a2]s' = A[a1]s' + A[a2]s'. Applying our
--induction hypothesis A[ai]s = A[ai]s'. Clearly A[a1+a2]s = A[a1+a2]s'


s::State
s "x" = 1
s "y" = 2
s "z" = 3
s  _  = 0
