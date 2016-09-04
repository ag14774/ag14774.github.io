data Bit = Off | On deriving (Show, Enum, Ord, Eq, Read)
data Word = Seq Bit Word | Val Bit

bit2string Off = '0'
bit2string On  = '1'

word2string :: Word -> String
word2string (Val x)   = [bit2string x]
word2string (Seq v w) = (bit2string v) : word2string w

instance Show Word
  where show w = show (word2string w)

word2int (Val x) = fromEnum x
word2int (Seq v w) = fromEnum v + 2*(word2int w)

type Num = Integer
type Var = String

data Factor = N Main.Num | V Main.Var
data Aexp   = Value Factor | Plus Aexp Aexp | Mul Aexp Aexp | Sub Aexp Aexp
data Bexp   = T | F | Equ Aexp Aexp | LTE Aexp Aexp | Not Bexp | And Bexp Bexp
data Stm    = Assign Main.Var Aexp | Skip | Sem Stm Stm | If Bexp Stm Stm | While Bexp Stm

prog = Sem (Assign "z" (Value $ V "x")) (While (LTE (Value $ N 2)(Value $ V "y")) (Sem (Assign "z" (Mul (Value $ V "z")(Value $ V "x")))(Assign "y" (Sub (Value $ V "y") (Value $ N 1))) ))

--Set y_0 = y (initial value)
--z:=x;
--while (y>=2) do
--z:=x^(y_0-y+1) && x>=0 && y>=1
--  z:=z*x;
--  y:=y-1;
--z:=x^(y_0-y+1) && x>=0 && y_0>=1
--Post: z = x^y_0
--
--Initialisation:
--As we have z = x and y_0 = y, z=x^(y_0-y+1) => z=x^y_0/x^(y-1)
-- z * x^(y-1) = x * x^(y-1) = x^y = x^y_0
--
--Termination:
--if y>=1 and !(x>=2)=x<2 -> y=1.
--z * x^(1-1) = z * x^0 = z = x^y_0
--
--Maintenace:
--if z*x^(y-1) = x^y_0 and x>=0 and y>=1
--z' = z*x
--y' = y-1
--then z'*x^(y'-1) = z*x*x^(y-1-1) = z*x^(y-1) = x^y_0
