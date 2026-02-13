import java.util.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;


public class partC{

    FileHandler fileHandler = new FileHandler();


    // calls the list of files from PartB
    public ArrayList<String> fileList =fileHandler.getFileList();

    public ArrayList<String> partBPath = new ArrayList<>();

    public boolean error = false;








public partC(){};

//programcontrol reads args as String and catches exceptions for illegal input
    public String programcontrol(String  args){
        try{fileHandler.loadFileList();}
        catch (IOException e){
            return "unable to load filelist";

        }
        int number = '\0';


        if (args.isEmpty()){
            String list="";
            int i=0;
            for (String x : getFileList()){
                i++;
                list += i +"  "+ x +"\n";


            }
            return list;
        }

        try {number = Integer.parseInt(args);}
        catch (NumberFormatException e){ System.out.println("format not supported");}
        String contents;

        contents = getFileContentsByIndex(number);

return contents;





        }



    public ArrayList<String> getFileList() {

        return fileHandler.getFileList();

       /* ArrayList<String> partBName = new ArrayList<>();
        int i =0;

        for(String x : fileList){

            partBName.add(i, fileList.get(i));
            i++;
        }
        for (int j = 0; j < partBName.size(); j++) {
            String s = partBName.get(j);
            partBName.set(j, s.length() > 5 ? s.substring(5): s);
        }



        return partBName;
        */

    }







    public String getFileContentsByIndex(int index){
        String contents;
        contents = fileHandler.readContent(index);
        return contents;


       /* ArrayList<String> partBName = new ArrayList<>();
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
        } */


    }

}
