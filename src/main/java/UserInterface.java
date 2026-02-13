import java.util.List;

public class UserInterface {
    private final ProgramControl control;

    public UserInterface(ProgramControl control) {
        this.control = control;
    }

    public void run(String[] args) {

        if (args.length == 0) {
            List<String> files = control.getFileList();
            for (int i = 0; i < files.size(); i++) {
                System.out.println((i + 1) + "  " + files.get(i));
            }
            return;
        }

        // Case 2: 1 or 2 arguments (like 01 or 01 somekey), print out contents of the file
        if (args.length == 1 || args.length == 2) {
            String choice = args[0];

            try {
                int num = Integer.parseInt(choice); // "01" becomes 1 automatically
                String contents = control.getFileContentsByIndex(num);
                System.out.print(contents);
                if (!contents.endsWith("\n")) System.out.println();
            } catch (NumberFormatException e) {
                System.out.println("Error: index must be a number.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            return;
        }

        System.out.println("Error: Too many arguments.");
    }
}
