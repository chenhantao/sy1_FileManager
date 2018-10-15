package cht;

import java.io.File;

public class Path {
	
	private String path;

	public String getPath() {
		return path;
	}



	public void setPath(String path) {
		this.path = path;
	}
	
	public void updatePath(String str)
	{
		this.path=this.path+File.separator+str;
	}

	public String[] fileList()
	{
		return (new File(getPath())).list();
	}
	
	public void returnBack()
	{
		this.path=new File(getPath()).getParent();
	}
	
	public static void main(String[] args)
	{
		Path path=new Path();
		path.setPath("E:\\Java资料\\test");
		System.out.println(path.getPath());
		path.updatePath("cht");
		System.out.println(path.getPath());
		for(String s:path.fileList())
		{
			System.out.println(s);
		}
	}
}
