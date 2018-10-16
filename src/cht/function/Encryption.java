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

	public Encryption(){}

	public Encryption(String str)
	{
		getKey(str);
	}
	
	public void getKey(String strKey)
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
	
	public void encrypt(File enFile, File outFile) throws Exception
	{
		if(enFile.exists()&&enFile.isFile())
		{
			Cipher cipher=Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE,this.key);
		
			InputStream ins=new FileInputStream(enFile);
			OutputStream outs=new FileOutputStream(outFile);
		
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
		}
	}
	
	/*
	 * 解密算法
	 * 
	 */
	public void decrypt(File file,File srcFile) throws Exception
	{
		if(file.exists()&&file.isFile())
		{
			Cipher cipher=Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE,this.key);
			
			InputStream ins=new FileInputStream(file);
			OutputStream outs=new FileOutputStream(srcFile);
			
			CipherOutputStream cos=new CipherOutputStream(outs, cipher);
			byte[] buffer=new byte[1024];
			int i;
			while((i=ins.read(buffer))>=0)
			{
				cos.write(buffer,0,i);
			}
			cos.close();
			ins.close();
			outs.close();
		}
	}
}
