import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UserInterfaceTest {

    // simple stub so UserInterface has something to call
    static class SimpleProgramControl extends ProgramControl {
        ArrayList<String> files = new ArrayList<>();
        String contents = "HELLO";
        boolean crash = false;

        @Override
        public ArrayList<String> getFileList() {
            return files;
        }

        @Override
        public String getFileContentsByIndex(int index) {
            if (crash) throw new RuntimeException("boom");
            return contents;
        }
    }

    @Test
    public void testNoArgsPrintsFileList() {
        SimpleProgramControl c = new SimpleProgramControl();
        c.files.add("a.txt");
        c.files.add("b.txt");

        UserInterface ui = new UserInterface(c);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(out));

        ui.run(new String[]{});

        System.setOut(old);
        String s = out.toString();

        assertTrue(s.contains("a.txt"));
        assertTrue(s.contains("b.txt"));
    }

    @Test
    public void testOneArgPrintsContents() {
        SimpleProgramControl c = new SimpleProgramControl();
        c.contents = "MYCONTENT";

        UserInterface ui = new UserInterface(c);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(out));

        ui.run(new String[]{"01"});

        System.setOut(old);
        String s = out.toString();

        assertTrue(s.contains("MYCONTENT"));
    }

    @Test
    public void testNonNumberArgPrintsError() {
        SimpleProgramControl c = new SimpleProgramControl();
        UserInterface ui = new UserInterface(c);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(out));

        ui.run(new String[]{"abc"});

        System.setOut(old);
        String s = out.toString();

        // this matches your catch(NumberFormatException) print
        assertTrue(s.toLowerCase().contains("index"));
        assertTrue(s.toLowerCase().contains("number"));
    }

    @Test
    public void testTooManyArgsPrintsError() {
        SimpleProgramControl c = new SimpleProgramControl();
        UserInterface ui = new UserInterface(c);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(out));

        ui.run(new String[]{"01", "key", "extra"});

        System.setOut(old);
        String s = out.toString();

        assertTrue(s.toLowerCase().contains("too many"));
    }

    @Test
    public void testGenericExceptionCatchRuns() {
        SimpleProgramControl c = new SimpleProgramControl();
        c.crash = true;

        UserInterface ui = new UserInterface(c);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(out));

        ui.run(new String[]{"01"});

        System.setOut(old);
        String s = out.toString();

        // this hits your catch(Exception e) line (the red one)
        assertTrue(s.contains("Error:"));
        assertTrue(s.contains("boom"));
    }
}
