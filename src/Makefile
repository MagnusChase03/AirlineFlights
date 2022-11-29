all: run

run: build
	java Main routes.txt best.txt test.dat

build:
	javac Main.java airlines/*.java

clean:
	rm Main.class
	rm airlines/*.class