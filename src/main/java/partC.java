package main.java;

import java.io.File;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;


public class partC{

    FileHandler fileHandler = new FileHandler();

    // calls the list of files from PartB
    File[] fileList =fileHandler.getFileList();

    ArrayList<String> partBPath = new ArrayList<>();

    boolean error = false;








public partC(){};

//Get file list, uses the PartB list getter

    public List<String> getFileList() {
        ArrayList<String> partBName = new ArrayList<>();
        int i =0;

        for(File x : fileList){

            partBName.add(i,  fileList[i].toString() );
            i++;
        }
        for (int j = 0; j < partBName.size(); j++) {
            String s = partBName.get(j);
            partBName.set(j, s.length() > 5 ? s.substring(5): s);
        }



        return partBName;

    }







    public String getFileContentsByIndex(int index){
        ArrayList<String> partBName = new ArrayList<>();
        Path folder = Paths.get("data");

//File path getter

        try{Files.list(folder)
                .filter(p -> p.toString().endsWith(".txt"))
                .forEach(p -> {partBName.add(p.getFileName().toString());
                    partBPath.add(p.toString());
                } );}
        catch(IOException e){
                System.out.println("cant find folder");
            }


        String contents;
//File path reader
        try{Path filepath = Paths.get(partBPath.get(index));
            contents = Files.readString(filepath);
        }
        catch(IOException a){
            System.out.println("error reading text or fetching files. check file format or location.");
            error = true;
            return "";
        }
        return contents;

    }

}
