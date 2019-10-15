package cn.itsource.fdfs;

import cn.itsource.anju.util.FastDfsApiOpr;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FastDfsTest {

    /**
     * 上传文件
     * @throws Exception
     */
    @Test
    public void testUpload()throws Exception{

        String filePath = "C:\\Users\\白喵\\Pictures\\Saved Pictures\\Kitty.jpg";
        File file = new File(filePath);
        long length = file.length();
        byte[] content = new byte[(int) length];

        FileInputStream inputStream = new FileInputStream(filePath);
        inputStream.read(content);

        String extName = "jpg";

        String upload = FastDfsApiOpr.upload(content, extName);
        System.out.println(upload);//   /group1/M00/00/01/rBAEz12l2IOANdY4AAIA3IGB53E342.jpg

    }

    /**
     * 下载
     * @throws Exception
     */
    @Test
    public void testDownload()throws Exception{

        String groupName = "group1";
        String fileName = "M00/00/01/rBAELl2mGnSADVtcAAA-8AZeDdQ775.jpg";

        byte[] content = FastDfsApiOpr.download(groupName, fileName);

        FileOutputStream outputStream = new FileOutputStream("E:/aaa.jpg");
        outputStream.write(content);

        outputStream.close();

    }

    /**
     * 删除
     * @throws Exception
     */
    @Test
    public void testDelete()throws Exception{
        String groupName = "group1";
        String fileName = "M00/00/01/rBAELl2mGnSADVtcAAA-8AZeDdQ775.jpg";
        FastDfsApiOpr.delete(groupName, fileName);
    }


}