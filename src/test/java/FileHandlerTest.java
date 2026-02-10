import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
public class FileHandlerTest {
    @Test
    public void testFolderNotFound(){}

    @Test
    public void testGetFlileList_Success(){
        FileHandler fh=new FileHandler();
        ArrayList<String> fileList=fh.getFileList();
        assertNotNull(fileList);
        assertEquals("filea.txt",fileList.get(0));
        assertEquals("fileb.txt",fileList.get(1));
        assertEquals("filec.txt",fileList.get(2));
    }

}
