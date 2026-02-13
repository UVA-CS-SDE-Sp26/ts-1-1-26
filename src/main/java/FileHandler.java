import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.IOException;

public class FileHandler{
    private File folder;
    private ArrayList<String> fileList;
    private ArrayList<String> numbering;
    public FileHandler(){
        folder=new File("data");
        fileList=new ArrayList<>();
        numbering=new ArrayList<>();
    }
    public FileHandler(File folder) {
        this.folder = folder;
        this.fileList = new ArrayList<>();
        this.numbering = new ArrayList<>();
    }
    public void loadFileList() throws IOException{
        fileList.clear();
        if(!folder.exists() || !folder.isDirectory()){
            throw new IOException("Data folder not found.");}
        File[] data = folder.listFiles();
        if (data==null||data.length == 0) {
            throw new IllegalStateException("The data folder is empty.");
        }
        String str = "";
        for (int i = 0; i < data.length; i++) {
            if (data[i].isFile()) {
                str = data[i].getName();
                fileList.add(str);
            }}
        Collections.sort(fileList);

    }
    public ArrayList<String> getFileList(){
        return fileList;
    }

    public void generateNumbering()throws IOException{
        this.loadFileList();
        numbering.clear();

            for (int i = 0; i < fileList.size(); i++) {
                numbering.add(String.format("%02d", i + 1));


    }}
    public ArrayList<String> getNumbering() {
        return numbering;
    }

    public String readContent(int index) throws IndexOutOfBoundsException{
        if (index >fileList.size()||index<0) {
            return "The value you entered is out of bounds";
        }
        String str = "";
        try {
            Scanner myReader = new Scanner(new File(folder, fileList.get(index - 1)));
            while (myReader.hasNextLine()) {
                String content = myReader.nextLine();
                str = str + content + "\n";
            }
                if(!str.isEmpty()){
                    str=str.substring(0,str.length()-1);
                }
        }catch(Exception e){
            System.out.println("File does not exist");
        }
        return str;
    }
}
