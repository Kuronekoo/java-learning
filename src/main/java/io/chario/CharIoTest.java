package io.chario;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @description:
 * @author: kuroneko
 * @create: 2020-05-22 08:41
 **/
public class CharIoTest {
    public static final String READ_FILE_PATH="ioFiles/test.txt";
    public static final String WRITE_BASE_PATH="ioFiles/";

    @Test
    public void fileReaderTest() throws Exception{
        FileReader fileReader = new FileReader(READ_FILE_PATH);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s =null;
        while ((s=bufferedReader.readLine()) != null){
            System.out.println(s);
        }
        bufferedReader.close();
        fileReader.close();
    }
}
