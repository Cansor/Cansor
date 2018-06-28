//package com.spellbk.copyfile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * 
 * 实现复制文件的操作（覆盖、创建副本、续写）
 * 
 * @version: 
 * 1.3	重构代码，修复一处BUG
 * 1.2	修复副本的排序BUG.
 *
 */

public class CopyFileDemo {

	public static void main(String[] args) {
		
		try {
			new CopyFile().copys();//调用CopyFile类
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
}

/**
 * 
 * 复制文件类
 *
 */
class CopyFile{
	
	public void copys() throws IOException {
		Scanner input = new Scanner(System.in);
		System.out.println("要复制的文件：  （输入完整路径，如 D:\\java.txt）");
		String path = input.nextLine();
		File file = new File(path);//创建要复制的文件的对象
		
		//判断要复制的文件是否存在或者是否是文件
		if(!file.exists() || !file.isFile()) {
			System.out.println("文件不存在！\n");
			copys();
			return;
		}
		
		System.out.println("粘贴的路径：  （输入完整路径，如 E:\\）");
		String newPath = input.nextLine();
		File newFile = new File(newPath);
		
		//判断路径是否正确
		if(!newFile.isDirectory()) {
			System.out.println("输入路径错误！\n");
			copys();
			return;
		}
		
		String name = newPath + file.getName();
		newFile = new File(name);	//创建文件副本对象
		boolean flag = false;	//文件续写开关
		
		//判断文件是否存在
		if(newFile.exists()) {
			System.out.println("\n文件已存在，要怎么做？	1、覆盖	2、跳过	3.共存，加上编号  4.续写");
			byte b = input.nextByte();
			
			if(b == 1) {
				System.out.println("将覆盖文件！");
			}else if(b == 2){
				System.out.println("结束！");
				return;
			}else if(b == 3){
				newFile = copy(newFile,newPath);
			}else {
				System.out.println("将从文件尾部续写文件！");
				flag = true;
			}
		}
		
		InputStream fis = new FileInputStream(file);//输入流，读取源文件
		int size = 1024*128;	//定义缓冲大小
		BufferedInputStream bis = new BufferedInputStream(fis,size);	//输入缓冲流
			
		//输出文件
		OutputStream out = new FileOutputStream(newFile, flag);//输出流，输出到副本文件
		BufferedOutputStream bos = new BufferedOutputStream(out,size);	//输出缓冲流
		System.out.println("\r\n"+newPath+newFile.getName());
		System.out.println("正在复制......");

		byte[] bytes = new byte[1024*16];	//每次读取的字节大小
		int len = -1;
		while((len = bis.read(bytes)) != -1) {
			bos.write(bytes, 0, len);//输出
		}
		//输出完成，关闭流
		bis.close();
		bos.close();
		
		System.out.println("\n复制完成！");
	}
	
	//-----------------------------给文件名加编号方法--------------------------------
	private File copy(File file,String path) {
		int number = 1;	//副本序号
		String name = file.getName();	//获取文件名
		StringBuilder sb = new StringBuilder(name);
		int index = name.lastIndexOf(".");	//获取后缀名的索引位置
		sb.insert(index,"-副本"+"("+number+")");	//在后缀名前加上“副本”
		file = new File(path + sb.toString());	//创建副本文件
		name = path + file.getName();	//将原文件名更改为副本的文件名
		
		int x = 2;//索引的偏移量
		int y = 10;
		//如果副本已存在，则创建新副本
		while(file.exists()) {
			number++;	//副本序号+1
			index = name.lastIndexOf(".");
			sb = new StringBuilder(name);
			
			/*
			 * 如果副本序号大于y，索引偏移量需要+1，y需要*10
			 * 因为序号位数每次多一位，文件名的长度就会+1，所以偏移量就需要+1
			 */
			if(number>y) {
				x++;
				y *= 10;
			}
			sb.replace(index-x, index-(x-1), ""+number);	//替换副本序号
			file = new File(sb.toString());
			name = path + file.getName();	//将原文件名更改为新副本的文件名
		}
		return file;
	}
}