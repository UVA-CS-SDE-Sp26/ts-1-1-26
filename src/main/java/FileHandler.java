import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class FileHandler{
    public static void main(String[] args) {
        File folder = new File(".data");
        if (folder.exists() && folder.isDirectory()) {
            File[] fileList = folder.listFiles();



    public String readFile() throws FileNotFoundException{
        if (fileList != null) {
            for (File file : fileList) {
                System.out.println(file.getName());
            }
        }
    }}
    public String readFile(File file) throws FileNotFoundException {

        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            System.out.println(data);
        }
    }

}
