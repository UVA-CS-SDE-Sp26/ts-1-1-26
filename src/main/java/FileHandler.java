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
//    //read the file list
//    public String readFileList() throws FileNotFoundException{
//        String str="";
//        if(fileList != null){
//            for(File file: fileList){
//                str=fileList+file.getName();
//            }
//        }
//        return str;
//}
    //read the exact file indexed
    public String readFile(int index){
        if(index>=fileList.length){
            throw new IndexOutOfBoundsException("file not in the list");
        }
            File currentFile=fileList[index];
            String str=this.readFile(currentFile);

        return str;
    }
    //helper method to read files
    public String readFile(File file) throws FileNotFoundException {
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
