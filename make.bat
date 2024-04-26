rem Batch Script

cd src
javac *.java
start /wait java Main.java > saida.txt

ping 127.0.0.1 -n 3 > nul

move AdjGraph.png ..\
del AdjGraph.dot

del *.class