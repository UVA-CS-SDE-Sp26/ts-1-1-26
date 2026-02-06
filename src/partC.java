

import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

/* NOTES:
Whats needed: Arraylists containing lists of filenames & locations.

 */


public class partC{

    ArrayList<String> partBPath = new ArrayList<>();
    ArrayList<String> partBName = new ArrayList<>();

    boolean error = false;
    String filepath;
    int number;
    Scanner scan = new Scanner(System.in);
    String filenamePath;
    String filename;






    public void nameHandler(){


    }


    partC(){
        Path folder = Paths.get("data");
        try{Files.list(folder)
                .filter(p -> p.toString().endsWith(".txt"))
                .forEach(p -> {partBName.add(p.getFileName().toString());
                    partBPath.add(p.toString());
                } );

        }


        catch(IOException e){
            System.out.println("cant find folder");
        }
        System.out.println(getContent(inputChecker()));

    }






    partC(boolean test){

        partBPath = new ArrayList<>(List.of("C:\\User_1\\3140\\tester1.txt","C:\\User_1\\3140\\tester2.txt","C:\\User_1\\3140\\tester3.txt"));
        partBName = new ArrayList<>(List.of("tester1","tester2","tester3"));
        System.out.println(getContent(inputChecker()));
    }




    public String inputChecker() {
        while (true) {
            System.out.println("Select from below files, enter an int:");
            for (int i = 0; i < partBName.size(); i++) {
                System.out.println((i + 1) + "  " + partBName.get(i));
            }

            String line = scan.nextLine();
            if (line == null) line = "";
            line = line.trim();

            if (line.isEmpty()) {
                System.out.println("Input can't be empty.");
                continue;
            }

            int choice;
            try {
                choice = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Enter a number!");
                continue;
            }

            if (choice < 1 || choice > partBPath.size()) {
                System.out.println("Number not in range!");
                continue;
            }

            return partBPath.get(choice - 1);
        }
    }




    public String getContent(String filenamePath){
        String contents;

        try{Path filepath = Paths.get(filenamePath);
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
