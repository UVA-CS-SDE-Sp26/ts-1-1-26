import java.util.List;

public class UserInterface {
    private final partC control;

    public UserInterface(partC control) {
        this.control = control;
    }

    public void run(String[] args) {
        // Case 1: No arguments (print out all file names in the list)
        if (args.length == 0) {
            List<String> files = control.getFileList();
            int j =1;
            for (String file : files) {
                j++;
                System.out.println(j+ "  " + file);
            }
        }

        // Case 2: 1 or 2 arguments (like 01 or 01 somekey), print out contents of the file
        if (args.length == 1 || args.length == 2) {
            String choice = args[0];

            try {

                String contents = control.getFileContentsByIndex(1);
                System.out.print(contents);
                if (!contents.endsWith("\n")) System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                //printUsage();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            return;
        }

        // 3) too many args
        System.out.println("Error: Too many arguments.");
    }
}
