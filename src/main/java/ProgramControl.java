import java.util.*;

import java.io.IOException;


public class ProgramControl {

    FileHandler fileHandler = new FileHandler();


    // calls the list of files from PartB
    public ArrayList<String> fileList = fileHandler.getFileList();

    public ArrayList<String> partBPath = new ArrayList<>();

    public boolean error = false;


    public ProgramControl() {
    }



    //programcontrol reads args as String and catches exceptions for illegal input
    public String programcontrol(String args) {
        try {
            fileHandler.loadFileList();
        } catch (IOException e) {
            return "unable to load filelist";

        }
        int number = '\0';


        if (args.isEmpty()) {
            String list = "";
            int i = 0;
            for (String x : getFileList()) {
                i++;
                list += i + "  " + x + "\n";


            }
            return list;
        }

        try {
            number = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            return "format not supported";
        }
        String contents;

        contents = getFileContentsByIndex(number);

        return contents;


    }


    public ArrayList<String> getFileList() {
        try {
            fileHandler.loadFileList();
        } catch (IOException e) {
            return new ArrayList<>();
        }
        return fileHandler.getFileList();
    }



    public String getFileContentsByIndex(int index) {
        try {
            fileHandler.loadFileList();
        } catch (IOException e) {
            return "unable to load filelist";
        }
        return fileHandler.readContent(index);
    }
}


