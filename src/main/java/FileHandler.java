import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class FileHandler{
    //private variable, file list
    private File[] fileList;
    //constructor
    public FileHandler(){
        File folder = new File("data");
        if (folder.exists() && folder.isDirectory()) {
            fileList = folder.listFiles();
        }
        else{
            fileList=new File[0];
        }
    }
    //getter for file list
    public File[] getFileList() {
        return fileList;
    }

    //read the exact file indexed
    public String readFile(int index) {
        if (index >= fileList.length) {
            return "The index you input is out of bounds.";
        } else {
            File currentFile = fileList[index];
            String str = this.readContent(currentFile);
            return str;
        }
    }
    //helper method to read files
    public String readContent(File file) throws FileNotFoundException {
        Scanner myReader = new Scanner(file);
        String str="";
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            str=str+data+"\n";
        }
        myReader.close();
        return str;
    }

}
