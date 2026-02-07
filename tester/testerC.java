import main.java.UserInterface;
import main.java.partC;

public class testerC {

    partC test = new partC();
    void main(){


        //System.out.println(test.getFileList());
       // System.out.println(test.num());
        System.out.println(test.getFileList() +"\n");


        System.out.println(test.getFileContentsByIndex(0)+"\n");
        System.out.println(test.getFileContentsByIndex(1)+ "\n");
        System.out.println(test.getFileContentsByIndex(2)+"\n");
        System.out.println(test.getFileContentsByIndex(3)+"\n");
        UserInterface one = new UserInterface(test);

    }
}
