//package com.spellbk.copyfile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class CopyFileDemo {

	public static void main(String[] args) {
		
		try {
			new CopyFile().copy();
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
}

/**
 * 
 * 复制文件类
 * @version: 1.2	修复副本的排序BUG.
 *
 */
class CopyFile{
	
	public void copy() throws IOException {
		Scanner input = new Scanner(System.in);
		System.out.println("要复制的文件：  （输入完整路径，如 D:\\java.txt）");
		String path1 = input.nextLine();
		File file1 = new File(path1);
		
		//判断文件是否存在或者是否是文件
		if(!file1.exists() || !file1.isFile()) {
			System.out.println("文件不存在！\n");
			copy();
			return;
		}
		
		System.out.println("粘贴的路径：  （输入完整路径，如 E:\\）");
		String path2 = input.nextLine();
		File file2 = new File(path2);
		
		//判断路径是否正确
		if(!file2.isDirectory()) {
			System.out.println("输入路径错误！\n");
			copy();
			return;
		}
		
		String name = path2 + file1.getName();
		file2 = new File(name);	//输出流对象
		boolean flag = false;	//续写开关
		
		//判断文件是否存在
		if(file2.exists()) {
			System.out.println("\n文件已存在，要怎么做？	1、覆盖	2、跳过	3.创建副本  4.续写");
			byte b = input.nextByte();
			
			if(b == 1) {
				System.out.println("将覆盖文件！");
			}else if(b == 2){
				System.out.println("结束！");
				return;
			}else if(b == 3){
				file2 = fb(file2,path2);
			}else {
				System.out.println("将从文件尾部续写文件！");
				flag = true;
			}
		}
		
		InputStream fis = new FileInputStream(file1);	//读取源文件
		int size = 1024*128;	//定义缓冲大小
		BufferedInputStream bis = new BufferedInputStream(fis,size);	//输入缓冲流
			
		//输出文件
		OutputStream out = new FileOutputStream(file2, flag);
		BufferedOutputStream bos = new BufferedOutputStream(out,size);	//输出缓冲流
		System.out.println("\r\n"+path2+file2.getName());
		System.out.println("正在复制......");

		byte[] bytes = new byte[1024*16];	//每次读取的字节大小
		int len = -1;
		while((len = bis.read(bytes)) != -1) {
			bos.write(bytes, 0, len);
		}
		//关闭流
		bis.close();
		bos.close();
		
		System.out.println("\n复制完成！");
	}
	
	//创建副本方法
	private File fb(File file,String path) {
		int x = 1;	//副本序号
		String name = file.getName();	//获取文件名
		StringBuilder sb = new StringBuilder(name);
		int i = name.lastIndexOf(".");	//获取后缀名的索引位置
		sb.insert(i,"-副本"+"("+x+")");	//在后缀名前加上“副本”
		file = new File(path + sb.toString());	//创建副本文件
		name = path + file.getName();	//将原文件名更改为副本的文件名
		
		int a = 2;//索引的偏移量
		int b = 10;
		//如果副本已存在，则创建新副本
		while(file.exists()) {
			x++;	//副本序号+1
			i = name.lastIndexOf(".");
			sb = new StringBuilder(name);
			if(x>b) {
				b++;
				b *= 10;
			}
			sb.replace(i-a, i-(a-1), ""+x);	//替换副本序号
			file = new File(sb.toString());
			name = path + file.getName();	//将原文件名更改为新副本的文件名
		}
		return file;
	}
}