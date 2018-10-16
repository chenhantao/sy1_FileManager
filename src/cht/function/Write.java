package cht.function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author chenhantao
 * @since 2018/10/16
 */
public class Write {
    public boolean wirte(File file, String input) {
        try {
            if (!file.exists()) {
                CreateFile createFile = new CreateFile();
                createFile.createFile(new File(file.getParent() + File.separator + file.getName()));
            }
            FileInputStream fis = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(file);
            int i;
            byte[] bytes = new byte[1024];
            while ((i = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, i);
            }
            fos.write(input.getBytes());

            fis.close();
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        Write write = new Write();
        File file = new File("E:\\test.txt");
        System.out.println(write.wirte(file, "test"));
    }
}
