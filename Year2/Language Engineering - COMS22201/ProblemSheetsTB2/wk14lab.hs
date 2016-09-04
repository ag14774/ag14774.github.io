data NonZeroDigit = One|Two|Three|Four|Five|Six|Seven|Eight|Nine
data ZeroDigit = Zero
data Digit = ZD ZeroDigit | NZD NonZeroDigit
data Natural = Val NonZeroDigit | Seq Natural Digit
data Numeral = Nul ZeroDigit | Pos Natural | Neg Natural

dig2int ( ZD Zero ) = 0
dig2int (NZD One  ) = 1
dig2int (NZD Two  ) = 2
dig2int (NZD Three) = 3
dig2int (NZD Four ) = 4
dig2int (NZD Five ) = 5
dig2int (NZD Six  ) = 6
dig2int (NZD Seven) = 7
dig2int (NZD Eight) = 8
dig2int (NZD Nine ) = 9

nat2int (Val d )  = dig2int (NZD d)
nat2int (Seq s d) = 10 * nat2int s + dig2int d

num2int (Nul z)   =  dig2int (ZD z)
num2int (Pos n)   =  nat2int n
num2int (Neg n)   = -(nat2int n)

instance Show Numeral
  where show n = show (num2int n)
