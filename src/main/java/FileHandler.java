import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class FileHandler{
    File[] fileList;
    public FileHandler(){
        File folder = new File(".data");
        if (folder.exists() && folder.isDirectory()) {
            File[] fileList = folder.listFiles();
        }
    }
    public String readFileList() throws FileNotFoundException{
        if(fileList != null){
            for(File file: fileList){
                System.out.println(file.getName());
            }
        }
}   public String readFile(int index){
        if(index>=fileList.length){
            throw new IndexOutOfBoundsException("file not in the list");
        }
    }
    public String readFile(File file) throws FileNotFoundException {
        Scanner myReader = new Scanner(file);
        String str="";
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            str=str+data;
        }
        return str;
    }

}
