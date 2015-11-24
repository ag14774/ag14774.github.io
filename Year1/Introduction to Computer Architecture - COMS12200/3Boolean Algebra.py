from sympy import *
from sympy.logic.boolalg import bool_equal, simplify_logic

a, b, c, d, e = symbols("a b c d e")

f = ( b & ~a ) | ( a & ~b )
g= ( a | b ) & ~( a & b)

print (f)
print (g)
print (bool_equal ( f, g ))
print

f=( ~(a | b) & ~(c | d | e)) | ~(a|b)

print (f)
print (simplify_logic(f))
print

f = ((a|b)&(d|~b)&(d|a))

print (f)
print (simplify_logic( f ))
print
