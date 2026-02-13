//this is a mock test, delete this later.
//fast compile in terminal: javac src\main\java\*.java src\test\java\MockTestC.java
//fast run in terminal: java -cp "src\main\java" MockTestC
public class MockTestC {

    partC test = new partC();

    public static void main(String[] args) {

        String arg1;
        if (args.length ==0){
            arg1 = "";

        }
        else { arg1= args[0];}
//example how you use programcontrol
       partC test = new partC();
        System.out.println(test.programcontrol(arg1));

    }
}