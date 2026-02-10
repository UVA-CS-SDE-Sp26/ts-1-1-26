import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
public class FileHandler{
    public ArrayList<String> getFileList(){
        File folder=new File("data");
        ArrayList<String> fileList=new ArrayList<>();
        if (!folder.exists() || !folder.isDirectory()) {
            throw new IllegalStateException("Data folder not found.");
        }
        File[] data = folder.listFiles();
        if(data.length==0){
            throw new IllegalStateException("Data folder is empty.");
        }
        String str = "";
        for (int i = 0; i < data.length; i++) {
            if (data[i].isFile()) {
                str = data[i].getName();
                fileList.add(str);
            }
        }
        Collections.sort(fileList);
        return fileList;
    }
}
