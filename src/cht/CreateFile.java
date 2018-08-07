package cht;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class CreateFile{
	
	public static boolean createFile(File path) throws IOException{
			path.createNewFile();
			System.out.println("文件创建成功！");
			return true;
	}
	
	
	public static boolean mkdir(File path)
	{
		if(path.mkdir())
		{
			System.out.println("创建文件夹成功");
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static void main(String[] args)
	{
		@SuppressWarnings("resource")
		Scanner input=new Scanner(System.in);
		String path=new String(input.nextLine());
		File file=new File(path);
		//create(file);
		//createFile(file);
		mkdir(file);
	}
	
}
