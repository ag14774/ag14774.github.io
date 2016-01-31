all:
	/home/ageorgiou/opt/antlr/antlr3 Lex.g
	/home/ageorgiou/opt/antlr/antlr3 Syn.g
	javac TokenConv.java
	java TokenConv Syn.tokens Irt.java 
	/home/ageorgiou/opt/antlr/antlr3 *.java
	/home/ageorgiou/opt/antlr/antlr3 camle testsk.w
	./assmule testsk.ass

clean:
	rm -f *.class
	rm -f *.txt
	rm -f *.tokens
	rm -f *.ass
	rm -f Lex.java
	rm -f Syn.java
