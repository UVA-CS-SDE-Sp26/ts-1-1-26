import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class ProgramControlTest {

    @TempDir
    Path tempDir;

    private ProgramControl makePartCWithTempDataFolder() throws IOException {
        // create temp "data" folder
        Path dataDir = tempDir.resolve("data");
        Files.createDirectories(dataDir);

        // create predictable files
        Files.writeString(dataDir.resolve("a.txt"), "AAA\nline2");
        Files.writeString(dataDir.resolve("b.txt"), "BBB");

        // build partC and inject FileHandler that points at temp data folder
        ProgramControl c = new ProgramControl();
        c.fileHandler = new FileHandler(dataDir.toFile());   // relies on fileHandler NOT being private
        return c;
    }

    @Test
    public void testProgramControl_EmptyArgs_ShowsMenu() throws IOException {
        ProgramControl c = makePartCWithTempDataFolder();
        String out = c.programcontrol("");

        assertNotNull(out);
        assertTrue(out.contains("1  a.txt"));
        assertTrue(out.contains("2  b.txt"));
    }

    @Test
    public void testProgramControl_ReadIndex_ReturnsContents() throws IOException {
        ProgramControl c = makePartCWithTempDataFolder();
        String out = c.programcontrol("1");

        assertEquals("AAA\nline2", out);
    }

    @Test
    public void testProgramControl_NonNumeric() throws IOException {
        ProgramControl c = makePartCWithTempDataFolder();
        String out = c.programcontrol("abc");

        assertEquals("format not supported", out);
    }

    @Test
    public void testProgramControl_FolderMissing() {
        ProgramControl c = new ProgramControl();
        c.fileHandler = new FileHandler(new File("THIS_FOLDER_SHOULD_NOT_EXIST_12345"));

        String out = c.programcontrol("");
        assertEquals("unable to load filelist", out);
    }

    @Test
    public void testGetFileList_Direct() throws IOException {
        ProgramControl c = makePartCWithTempDataFolder();
        c.fileHandler.loadFileList(); // because getFileList() just returns the list

        assertEquals(2, c.getFileList().size());
        assertEquals("a.txt", c.getFileList().get(0));
        assertEquals("b.txt", c.getFileList().get(1));
    }

    @Test
    public void testGetFileContentsByIndex_Direct() throws IOException {
        ProgramControl c = makePartCWithTempDataFolder();
        c.fileHandler.loadFileList();

        assertEquals("BBB", c.getFileContentsByIndex(2));
    }
}



