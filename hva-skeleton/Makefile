compile:
	javac -cp po-uilib.jar `find . -name \*.java`
run:
	java -cp po-uilib.jar:. hva.app.App
clean:
	find . -name \*.class -exec rm {} \;
jar:
	jar -cfv hva.jar hva
seeJar:
	jar -tf hva.jar
