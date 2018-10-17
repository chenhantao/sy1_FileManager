package cht.function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;

public class Encryption {
	private Key key;

//	public Encryption(){}
//
//	public Encryption(String str)
//	{
//		getKey(str);
//	}

    private void getKey(String strKey)
	{
		try {
			KeyGenerator generator=KeyGenerator.getInstance("DES");
			generator.init(new SecureRandom(strKey.getBytes()));
			this.key=generator.generateKey();
			generator=null;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Error"+e);
		}
	}
	
	/*
	 * 加密算法
	 * 加密开始，分为目标文件和输出文件
	 * enFile为目标文件
	 * outFile为输出文件
	 */
	
	public void encrypt(File file) throws Exception
	{
        this.getKey(file.getName());
		if(file.exists()&&file.isFile())
		{
			Cipher cipher=Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE,this.key);
		
			InputStream ins=new FileInputStream(file);

            //加密完后的文件
            File out = new File(file.getParent() + File.separator + "temp");
            CreateFile createFile = new CreateFile();
            createFile.createFile(out);


			OutputStream outs=new FileOutputStream(out);
		
			CipherInputStream cis=new CipherInputStream(ins, cipher);
			byte[] buffer=new byte[1024];
			int i;
			while((i=cis.read(buffer))>0)
			{
				outs.write(buffer, 0, i);
			}
			cis.close();
			ins.close();
			outs.close();

            //关闭流后删除源文件，改目标文件的名字
            DeleteFolder delete = new DeleteFolder();
            System.out.println("删除源文件 " + (delete.deleteFile(file) ? "成功":"失败"));
            File result = new File(out.getPath());
            System.out.println("改名 "+ (result.renameTo(file) ? "成功":"失败"));
		}
	}
	
	/*
	 * 解密算法
	 * 
	 */
	public void decrypt(File file) throws Exception
	{
        this.getKey(file.getName());
		if(file.exists()&&file.isFile())
		{   //加密方式
			Cipher cipher=Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE,this.key);

			//读文件
            InputStream ins=new FileInputStream(file);

            //加密完后的文件
            File out = new File(file.getParent() + File.separator + "temp");
            CreateFile createFile = new CreateFile();
            createFile.createFile(out);

            //输出流
            OutputStream outs=new FileOutputStream(out);
            //加密输出流
            CipherOutputStream cos=new CipherOutputStream(outs, cipher);
			byte[] buffer=new byte[1024];
			int i;
			while((i=ins.read(buffer))>=0)
			{
				cos.write(buffer,0,i);
			}
            ins.close();
            cos.close();
            outs.close();
            //关闭流后删除源文件，改目标文件的名字
            //尝试一下线程操作
            DeleteFolder delete = new DeleteFolder();
            System.out.println("删除源文件 " + (delete.deleteFile(file) ? "成功" : "失败"));

            File result = new File(out.getPath());
            System.out.println("改名 " + (result.renameTo(file) ? "成功":"失败"));
		}
	}
}
