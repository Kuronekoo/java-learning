package io.byteio;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @description:
 * @author: kuroneko
 * @create: 2020-05-21 16:58
 **/
public class ByteIoTest {
    public static final String INPUT_FILE_PATH="ioFiles/test.txt";
    public static final String OUTPUT_BASE_PATH="ioFiles/";

    @Test
    public void testFileByteStream() throws Exception{
        FileInputStream fileInputStream = new FileInputStream(INPUT_FILE_PATH);
        //true就是不覆盖输出文件，往后追加
        FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_BASE_PATH +"testFileByteStream.txt",true);
        //定义一个长度为1024的字节数组
        byte[] bytes = new byte[1024];
        //默认长度-1
        int len = -1;
        //将文件的字节流读入bytes数据，fileInputStream.read返回读入的长度，如果已经读到文件末尾，则会返回-1
        while ((len = fileInputStream.read(bytes)) != -1){
            System.out.println(len);
            //将bytes的内容输出，从bytes的0开始，写到len-1
            fileOutputStream.write(bytes,0,len);
        }

        fileOutputStream.close();
        fileInputStream.close();

    }

    @Test
    public void testByteArrayOutputStream() throws Exception{
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //写入字母A
        out.write(65);
        System.out.println(out.toString());
        //写入字母B
        out.write(66);
        System.out.println(out.toString());
        //重置输出流，清空数组，设置数组的count为0
        out.reset();
        System.out.println(out.toString());
        //写入字母k
        out.write(75);
        System.out.println(out.toString());

    }
}
