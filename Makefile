build:
	javac Stratego.java
	echo "Main-Class: Stratego" >> Manifest.txt
	jar cfm Stratego.jar Manifest.txt Stratego.class
	rm Manifest.txt

clean:
	rm *.class

