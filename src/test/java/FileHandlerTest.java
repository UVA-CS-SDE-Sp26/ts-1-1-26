import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
public class FileHandlerTest {
   // @Test 5
//    public void testFolderNotFound(){
//        FileHandler fh=new FileHandler();
//        ArrayList<String> fileList=fh.getFileList();
//Exception exception = assertThrows(IllegalStateException.class, () -> {
//    fh.getFileList();
//});
//        assertEquals("Data folder is empty.", exception.getMessage(),"when the data folder is empty, it does not return correctly.");
//    }

    @Test //2
    public void testGetFlileList_Success(){
        FileHandler fh=new FileHandler();
        fh.loadFileList();
        ArrayList<String> fileList=fh.getFileList();
        assertNotNull(fileList);
        assertEquals("filea.txt",fileList.get(0),"the first file does not match");
        assertEquals("fileb.txt",fileList.get(1),"the second file does not match");
        assertEquals("filec.txt",fileList.get(2),"the third file does not match");
    }
    @Test
    public void testGetNumbering_Success(){
        FileHandler fh=new FileHandler();
        fh.generateNumbering();
        ArrayList<String> numbering=fh.getNumbering();
        assertEquals("03",numbering.get(numbering.size()-1),"the numbering is not right.");
    }
//    @Test //4
//    public void testGetNumbering_format(){
//
//    }
//    @Test
//    public void testFolderNotExists(){
//
//    }
    @Test
    public void testReturnFileContent(){
        FileHandler fh=new FileHandler();
        String output=fh.readContent(1);
        assertEquals("file A contents as follows\n" +
                "gadjsgvasbdfjkansdjnksdgkskdng\n" +
                "dsnakdfsdkfa\n" +
                "asdfadsafxzvq\n" +
                "adfadfasdgfa",output,"the file content does not match");
    }

}
