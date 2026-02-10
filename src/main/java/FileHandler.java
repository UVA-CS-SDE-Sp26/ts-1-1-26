import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
public class FileHandler{
    private File folder;
    //private File[] data;
    private ArrayList<String> fileList;
    private ArrayList<String> numbering;
    public FileHandler(){
        folder=new File("data");
        fileList=new ArrayList<>();
        numbering=new ArrayList<>();
    }
    public void loadFileList(){
        fileList.clear();
        if(!folder.exists() || !folder.isDirectory()){
            throw new IllegalStateException("Data folder not found.");}
        File[] data = folder.listFiles();
        if (data == null || data.length == 0) {
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
    }
    public ArrayList<String> getFileList(){
        return fileList;
    }
    public void generateNumbering(){
        this.loadFileList();
        numbering.clear();
        for (int i = 0; i < fileList.size(); i++) {
        numbering.add(String.format("%02d", i + 1));
    }

    }

    public ArrayList<String> getNumbering() {
        return numbering;
    }
}
