import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

public class ProgramControlTest {

    // Simple assertions (no JUnit)
    private static void assertTrue(boolean cond, String msg) {
        if (!cond) throw new AssertionError("FAIL: " + msg);
    }

    private static void assertEquals(String expected, String actual, String msg) {
        if (expected == null && actual == null) return;
        if (expected != null && expected.equals(actual)) return;
        throw new AssertionError("FAIL: " + msg + "\nExpected:\n" + expected + "\nActual:\n" + actual);
    }

    private static void assertContains(String haystack, String needle, String msg) {
        if (haystack == null || !haystack.contains(needle)) {
            throw new AssertionError("FAIL: " + msg + "\nMissing: " + needle + "\nActual:\n" + haystack);
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("=== TesterPartC starting ===");
        System.out.println("Working dir: " + System.getProperty("user.dir"));


        Path dataDir = Paths.get("data");
        recreateDir(dataDir);

        // Ensure predictable sort order in FileHandler (a.txt then b.txt)
        writeFile(dataDir.resolve("a.txt"), "AAA\nline2");
        writeFile(dataDir.resolve("b.txt"), "BBB");


        partC c1 = new partC();
        String listOutput = c1.programcontrol("");
        System.out.println("[empty-args output]\n" + listOutput);

        // Should include numbered entries for a.txt and b.txt
        assertContains(listOutput, "1  a.txt", "Menu should include first file a.txt with number 1");
        assertContains(listOutput, "2  b.txt", "Menu should include second file b.txt with number 2");


        partC c2 = new partC();
        String content1 = c2.programcontrol("1");
        System.out.println("[read #1 output]\n" + content1);


        assertEquals("AAA\nline2", content1, "Reading file #1 should return full contents of a.txt");


        partC c3 = new partC();

        // Reminder TesterPartC must be in the same package (no package line) to access it.
        c3.fileHandler = new FileHandler(new File("THIS_FOLDER_SHOULD_NOT_EXIST_12345"));
        String ioFail = c3.programcontrol("");
        System.out.println("[io-fail output]\n" + ioFail);
        assertEquals("unable to load filelist", ioFail, "Should return unable to load filelist when folder missing");


        partC c4 = new partC();
        String nonNumericResult = c4.programcontrol("abc");
        System.out.println("[non-numeric output]\n" + nonNumericResult);


        assertTrue(nonNumericResult != null, "Non-numeric input should not return null");

        System.out.println("=== ALL TESTS PASSED ===");
    }

    private static void recreateDir(Path dir) throws IOException {
        if (Files.exists(dir)) {
            // delete contents
            try (var walk = Files.walk(dir)) {
                walk.sorted((a, b) -> b.compareTo(a))
                        .forEach(p -> {
                            try { Files.deleteIfExists(p); }
                            catch (IOException e) { throw new RuntimeException(e); }
                        });
            }
        }
        Files.createDirectories(dir);
    }

    private static void writeFile(Path file, String contents) throws IOException {
        Files.writeString(file, contents, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
