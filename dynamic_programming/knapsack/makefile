JFLAGS = -g
JC = javac
JVM = java

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = Knapsack.java

MAIN = Knapsack

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
