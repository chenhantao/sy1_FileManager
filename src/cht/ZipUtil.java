package cht;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
	private static void compressFile(File file,ZipOutputStream zipOut) throws Exception
	{
		byte[] buffer = new byte[1024];
		InputStream input=new FileInputStream(file);
		zipOut.putNextEntry(new ZipEntry(file.getName()));
		int i;
		while((i=input.read(buffer))!=-1)
		{
			zipOut.write(buffer, 0, i);
		}
		input.close();
	}
	
	private static void compressFile(File file,ZipOutputStream zipOut,String baseDir) throws Exception
	{
		byte[] buffer=new byte[1024];
		InputStream input=new FileInputStream(file);
		zipOut.putNextEntry(new ZipEntry(baseDir+file.getName()));
		int i;
		while((i=input.read(buffer))!=-1)
		{
			zipOut.write(buffer, 0, i);
		}
		input.close();
	}
	
	
	/**
     * 压缩整个文件夹中的所有文件，生成指定名称的zip压缩包
     * @param filepath 文件所在目录
     * @param zippath 压缩后zip文件名称
     * @param dirFlag zip文件中第一层是否包含一级目录，true包含；false没有
     * @param zipName 压缩文件名字
     * 
     * @author 陈瀚涛
     * 2015年6月9日
     */
    public static boolean zipMultiFile(File file ,String zipName, boolean dirFlag) throws Exception{
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(new File(file.getParent()+"/"+zipName+".zip")));
            if(file.isDirectory()){
                File[] files = file.listFiles();
                if(files.length==0)
                {
                	zipOut.putNextEntry(new ZipEntry(file.getName()+"/"));
                }
                for(File fileSec:files){
                    if(dirFlag){
                        recursionZip(zipOut, fileSec, file.getName() + "/");
                    }else{
                        recursionZip(zipOut, fileSec, "");
                    }
                }
                zipOut.close();
                return true;
            }
            else if(file.isFile())
            {
            	compressFile(file, zipOut);
            	zipOut.close();
            	return true;
            }
            else {
				zipOut.close();
				return false;
			}
           
    }
     
    private static void recursionZip(ZipOutputStream zipOut, File file, String baseDir) throws Exception{
        if(file.isDirectory()){
            File[] files = file.listFiles();
            if(files.length==0)
            {
            	zipOut.putNextEntry(new ZipEntry(baseDir+file.getName()+"/"));
            }
            else
            {
            	for(File fileSec:files){
            		recursionZip(zipOut, fileSec, baseDir + file.getName() + "/");
            	}
            }
        }else{
           compressFile(file, zipOut,baseDir);
        }
    }

    public static boolean unZip(File srcFile, File destDir) throws IOException {
    	FileInputStream fis = new FileInputStream(srcFile);
    	CheckedInputStream csumi = new CheckedInputStream(fis, new Adler32());
    	ZipInputStream zis = new ZipInputStream(csumi);
    	BufferedInputStream bis = new BufferedInputStream(zis);
    	ZipEntry ze = null;
    	File file_out = null;
    	while((ze = zis.getNextEntry()) != null) {
    		if(!ze.isDirectory()) {
    			System.out.println("解压缩" + ze.getName() + "  文件");
    			file_out = new File(destDir, ze.getName());
        		if(!file_out.exists()) {
        			new File(file_out.getParent()).mkdirs();
        		}
        		FileOutputStream fos = new FileOutputStream(file_out);
        		BufferedOutputStream bos = new BufferedOutputStream(fos);
        		int x;
        		while((x = bis.read()) != -1) {
        			bos.write(x);
        		}
        		bos.close();
        		fos.close();
    		} else {
    			System.out.println("解压缩" + ze.getName() + " 文件夹");
    			new File(destDir, ze.getName()).mkdirs();
			}
    		
    	}
    	bis.close();
    	zis.close();
    	return true;
    }
    
    public static void main(String[] args) throws Exception
    {
    	zipMultiFile(new File("E:\\Java资料\\test\\asdddddd"), "test", true);
    	//unZip(new File("E:\\Java资料\\test\\test.zip"),new File("E:\\Java资料\\新建文件夹 (2)"));
 
    }
}
