import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.io.File;
import java.util.ArrayList;
import static org.mockito.Mockito.*;
import java.io.IOException;

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
    //test case 1:
    @Test
    public void testGetFlileList_Success() throws IOException{
        FileHandler fh=new FileHandler();
        fh.loadFileList();
        ArrayList<String> fileList=fh.getFileList();
        assertNotNull(fileList);
        assertEquals("filea.txt",fileList.get(0),"the first file does not match");
        assertEquals("fileb.txt",fileList.get(1),"the second file does not match");
        assertEquals("filec.txt",fileList.get(2),"the third file does not match");
    }
    //test case 2
    @Test
    public void testGetFileList_NotExist() throws IOException{
        //using Mockito
        File mockFolder=mock(File.class);
        //assign the Mockito to a non existing folder
        when(mockFolder.isDirectory()).thenReturn(false);
        when(mockFolder.exists()).thenReturn(false);
        FileHandler fh=new FileHandler(mockFolder);
        IOException exception = assertThrows(IOException.class, () -> {
            fh.loadFileList();
        });
        assertEquals("Data folder not found.", exception.getMessage());
    }
    //test case 3
    @Test
    public void testGetFileList_NoFile() throws IOException{
        //using Mockito
        File mockFolder=mock(File.class);
        FileHandler fh=new FileHandler(mockFolder);
        File[] mockFiles=new File[0];
        when(mockFolder.isDirectory()).thenReturn(true);
        when(mockFolder.exists()).thenReturn(true);
        assertThrows(IllegalStateException.class, () -> {
            fh.loadFileList();
        });
         }
    //test case 4
    @Test
    public void testNumberingOne_Success() throws IOException{
        FileHandler fh=new FileHandler();
        fh.loadFileList();
        fh.generateNumbering();
        ArrayList<String> nums=fh.getNumbering();
        assertEquals("04",nums.get(nums.size()-1),"the numbering is not right when there is one single digit.");
    }
    //test case 5
    @Test
    public void testNumberingTwo_Success() throws IOException{
        //using Mockito
        File mockFolder=mock(File.class);
        File[] mockFiles=new File[20];

        when(mockFolder.exists()).thenReturn(true);
        when(mockFolder.isDirectory()).thenReturn(true);
        //stubbing
        for(int i=0;i<20;i++) {
            File f = mock(File.class);
            when(f.isFile()).thenReturn(true);
            when(f.getName()).thenReturn("mockFile" + i + ".txt");
            mockFiles[i] = f;
        }
//        when(mockFiles==null).thenReturn(false);
//        when(mockFiles.length==0).thenReturn(false);
        when(mockFolder.listFiles()).thenReturn(mockFiles);
        //using the Mockito
        FileHandler fh=new FileHandler(mockFolder);
        fh.loadFileList();
        fh.generateNumbering();
        ArrayList<String> nums=fh.getNumbering();
        assertEquals("20",nums.get(nums.size()-1),"the numbering is not right when there is two digits.");
    }
    //test case 6
    @Test
    public void testNumberingThree_Success() throws IOException{
        //using Mockito
        File mockFolder=mock(File.class);
        File[] mockFiles=new File[100];
        //stubbing
        when(mockFolder.exists()).thenReturn(true);
        when(mockFolder.isDirectory()).thenReturn(true);
        for(int i=0;i<100;i++){
            File f=mock(File.class);
            when(f.isFile()).thenReturn(true);
            when(f.exists()).thenReturn(true);
            when(f.isDirectory()).thenReturn(true);
            when(f.getName()).thenReturn("mockFile"+(i+1)+".txt");
            mockFiles[i]=f;
        }
//
//        when(mockFiles==null).thenReturn(false);
        //when(mockFiles.length()==0).thenReturn(false);
        when(mockFolder.listFiles()).thenReturn(mockFiles);
        //using the Mockito
        FileHandler fh=new FileHandler(mockFolder);
        fh.loadFileList();
        fh.generateNumbering();
        ArrayList<String> nums=fh.getNumbering();
        assertEquals("100",nums.get(nums.size()-1),"the numbering is not right when there is three digits.");
    }

    //test case 7
    @Test
    public void testReturnContent_Success() throws IOException{
        FileHandler fh=new FileHandler();
        fh.loadFileList();
        assertEquals("this is file a line 1",fh.readContent(1),"the file content does not match");
        assertEquals("this is file b line 1\n" +
                "this is file b line 2", fh.readContent(2),"the file content does not match");
        assertEquals("this is file c line 1\n" +
                "this is file c line 2\n" +
                "this is file c line 3",fh.readContent(3),"the file content does not match");
        assertEquals("",fh.readContent(4),"the file content does not match");
    }
    //test 8
    @Test
    public void testReturnContent_Fail() throws IOException{
        FileHandler fh=new FileHandler();
        fh.loadFileList();
        assertEquals("The value you entered is out of bounds",fh.readContent(9999),"the file content does not match");
    }
}
