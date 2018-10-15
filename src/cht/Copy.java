package cht;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Copy{
	public static void copyDir(String oldPath, String newPath) throws IOException {
        File file = new File(oldPath);
        String[] filePath = file.list();
	    if (!(new File(newPath)).exists()) {
	    	(new File(newPath)).mkdir();
	    }    
	    for (int i = 0; i < filePath.length; i++) {
	        if ((new File(oldPath + File.separator + filePath[i])).isDirectory()) {
	            copyDir(oldPath  + File.separator  + filePath[i], newPath  + File.separator + filePath[i]);
	        }
	         
	        if (new File(oldPath  + File.separator + filePath[i]).isFile()) {
	            copyFile(oldPath + File.separator + filePath[i], newPath + File.separator + filePath[i]);
	        }
	    }
    
	}
	
	public static void copyFile(String oldPath, String newPath) throws IOException {
        File oldFile = new File(oldPath);
        File file = new File(newPath);
        FileInputStream in = new FileInputStream(oldFile);
        FileOutputStream out = new FileOutputStream(file);;

        byte[] buffer=new byte[1024];
        while((in.read(buffer)) != -1){
            out.write(buffer);
        }
    
        in.close();
        out.close();
    }
	
	
	
	public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入源目录：");
        String sourcePath = sc.nextLine();
        System.out.println("请输入新目录：");
        String path = sc.nextLine();
        
        //String sourcePath = "D://aa";
        //String path = "D://bb";
        copyDir(sourcePath, path);
        
        sc.close();
    }
}