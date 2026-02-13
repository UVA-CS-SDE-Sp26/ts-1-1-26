import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class PartCTest {

    private Path dataDir;

    @BeforeEach
    void setup() throws IOException {
        dataDir = Paths.get("data");
        deleteDataDirIfExists();
    }

    @AfterEach
    void cleanup() throws IOException {
        deleteDataDirIfExists();
    }

    private void deleteDataDirIfExists() throws IOException {
        if (!Files.exists(dataDir)) return;

        try (var walk = Files.walk(dataDir)) {
            walk.sorted(Comparator.reverseOrder())
                    .forEach(p -> {
                        try { Files.deleteIfExists(p); }
                        catch (IOException ignored) {}
                    });
        }
    }

    @Test
    void constructor_and_getFileList_coverGetter() {
        partC c = new partC();
        assertNotNull(c);

        // Covers getFileList() line in partC (it just returns FileHandler's list)
        assertNotNull(c.getFileList());
    }

    @Test
    void getFileContentsByIndex_success_readsFile() throws IOException {
        Files.createDirectories(dataDir);
        Files.writeString(dataDir.resolve("only.txt"), "HELLO");

        partC c = new partC();
        String out = c.getFileContentsByIndex(0);

        assertEquals("HELLO", out);
        assertFalse(c.error, "error should remain false on successful read");
    }

    @Test
    void getFileContentsByIndex_folderMissing_hitsFirstCatch() {
        // Do NOT create data/ folder so Files.list(folder) throws IOException -> first catch runs
        partC c = new partC();

        // After the catch, partBPath is empty, so partBPath.get(0) throws IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> c.getFileContentsByIndex(0));
    }

    @Test
    void getFileContentsByIndex_readFails_hitsSecondCatch() throws IOException {
        Files.createDirectories(dataDir);

        // Create a DIRECTORY named with .txt extension.
        // It passes the ".txt" filter, but Files.readString(dir) throws IOException ("Is a directory")
        Files.createDirectories(dataDir.resolve("bad.txt"));

        partC c = new partC();
        String out = c.getFileContentsByIndex(0);

        assertEquals("", out);
        assertTrue(c.error, "error should be set true when readString throws IOException");
    }
}

