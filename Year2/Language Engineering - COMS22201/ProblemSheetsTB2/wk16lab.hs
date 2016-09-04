import Prelude hiding (Num)
import qualified Prelude (Num)

type Num = Integer
type Z   = Integer
type Var = String
type State = (Var -> Z)

data Aexp = N Num | V Var | Neg Aexp
          | Add Aexp Aexp | Mult Aexp Aexp | Sub Aexp Aexp
          deriving (Show, Eq, Read)

a::Aexp
a = Mult (Add (V "x") (V "y")) $ Sub (V "z") (N 1)

n_val::Num -> Z
n_val n = n

a_val::Aexp -> State -> Z
a_val      (N x) _ = n_val x
a_val      (V x) q = q x
a_val    (Neg x) q = - (a_val x q)
a_val (Mult x y) q = (a_val x q) * (a_val y q)
a_val (Add  x y) q = (a_val x q) + (a_val y q)
a_val (Sub  x y) q = (a_val x q) - (a_val y q)

s::State
s "x" = 1
s "y" = 2
s "z" = 3
s  _  = 0
