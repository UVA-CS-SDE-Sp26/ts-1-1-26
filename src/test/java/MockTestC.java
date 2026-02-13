

public class MockTestC {

    partC test = new partC();

    public static void main(String[] args) {

        String arg1;
        if (args.length ==0){
            arg1 = "";

        }
        else { arg1= args[0];}

       partC test = new partC();
        System.out.println(test.programcontrol(arg1));

    }
}