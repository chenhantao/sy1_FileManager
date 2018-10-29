package cht.function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Copy {
    public void copyDir(String oldPath, String newPath) throws IOException {
        if (oldPath.equals(newPath)) {
            return;
        }

        File file = new File(oldPath);
        String[] filePath = file.list();
        if (!(new File(newPath)).exists()) {
            new File(newPath).mkdir();
        }
        if (filePath != null && filePath.length != 0) {
            for (String aFilePath : filePath) {
                if ((new File(oldPath + File.separator + aFilePath)).isDirectory()) {
                    copyDir(oldPath + File.separator + aFilePath, newPath + File.separator + aFilePath);
                }

                if (new File(oldPath + File.separator + aFilePath).isFile()) {
                    this.copyFile(oldPath + File.separator + aFilePath, newPath + File.separator + aFilePath);
                }
            }
        }
    }

    public void copyFile(String oldPath, String newPath) throws IOException {
        if (oldPath.equals(newPath)) {
            return;
        }
        File oldFile = new File(oldPath);
        File file = new File(newPath);
        FileInputStream in = new FileInputStream(oldFile);
        FileOutputStream out = new FileOutputStream(file);
        ;

        byte[] buffer = new byte[1024];
        while ((in.read(buffer)) != -1) {
            out.write(buffer);
        }

        in.close();
        out.close();
    }
}