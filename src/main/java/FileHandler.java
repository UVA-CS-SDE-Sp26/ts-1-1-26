import java.io.File;
import java.util.ArrayList;
public class FileHandler{
    //private variable, file list
    private ArraryList<String> fileList;
    //constructor
    public FileHandler(){
        File folder = new File("data");
        File[] data = folder.listFiles();
        String str="";
        for(int i=0;i<data.length;i++){
            if(data[i].isFile()){
                str=data[i].getName()+".txt";
                fileList.add(str);
            }
        }
    }

    public ArrayList<String> getFileList(){
        return fileList;
    }


}
