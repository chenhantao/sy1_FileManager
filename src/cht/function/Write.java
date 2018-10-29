package cht.function;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

/**
 * @author chenhantao
 * @since 2018/10/16
 */
public class Write {
    public Write(){}

    public Write(File file, String input){
        this.wirte(file, input);
    }

    public boolean wirte(File file, String input) {
        try {
            if (!file.exists()) {
                CreateFile createFile = new CreateFile();
                createFile.createFile(new File(file.getParent() + File.separator + file.getName()));
            }
            FileInputStream fis = new FileInputStream(file);
            //FileOutputStream fos = new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));


            bw.write(input);
            bw.newLine();

            fis.close();
            bw.flush();
            bw.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /*//
    public static void main(String[] args) {
        Write write = new Write();
        File file = new File("E:\\test.txt");
        System.out.println(write.wirte(file, "chenhan"));
        System.out.println(write.wirte(file, "陈瀚涛12313212131213131121231"));
    }//*/
}
