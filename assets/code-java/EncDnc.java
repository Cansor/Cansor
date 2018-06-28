//package com.spell.encdnc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 使用异或（^）对文件作简单加密与解密，并能验证密码是否正确。
 * @author Cansor
 * @version 1.1		重构源代码，解决 InputMismatchException 异常。
 */
public class EncDnc {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("1、加密     2、解密");
		int key = input.nextInt();
		switch (key) {
		case 1:
			new EncDnc().encFile();
			break;

		default:
			new EncDnc().dncFile();
			break;
		}
	}

	
	//----------判断要加密的文件是否存在以及输入密码----------------
	private void encFile() {
		Scanner input = new Scanner(System.in);
		System.out.println("输入文件：");
		String filePath = input.nextLine();
		
		File file = new File(filePath);
		//判断文件是否存在
		if(!file.isFile()) {
			System.out.println("找不到此文件！");
			encFile();
			return;
		}
		
		int password;//密码
		//判断密码是否合法
		
		try {
			while(true) {
				System.out.println("输入密码（9位数以内）：");
				password = input.nextInt();
				if(password > 999999999 || password < 0) {
					System.out.println("密码不合法！");
					
				}else {
					break;//退出循环
				}
			}
			
			encrypt(file, password);//调用加密方法，把文件和密码传过去
			
		}catch(InputMismatchException e){
			System.out.println("错误，密码不合法！");
			encFile();
		}
		
	}
	
	
	//----------------------------加密方法----------------------------------
	private void encrypt(File file, int password) {
		//创建被加密的文件，扩展名加上 .sp
		File newFile = new File(file.getAbsolutePath() + ".sp");
		
		try {
			//建立缓冲流
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile,true),32*1024);
			
			System.out.println("加密中......");
			System.out.println(newFile.getName());
			
			StringBuilder sb = new StringBuilder();
			sb.append(password);//把int型密码转成字符
			int sbLen = sb.length();//获取密码长度
			bos.write((byte)sbLen ^ 33);//把密码的长度写入文件
			bos.flush();//刷新输出缓冲区
			
			//把密码逐位写入文件
			int tem;
			byte pw;
			for(int i=0;i<sbLen;i++) {
				tem = Integer.valueOf(sb.substring(i, i+1));//把字符转成int，每次截取其中一位密码
				pw = (byte)(tem ^ 66);//加密密码
				bos.write(pw);//写入文件
			}
			bos.flush();//刷新输出缓冲区
			
			//将文件主体写入加密文件中
			byte[] bytes = new byte[4*1024];//定义每次读取文件的字节数
			int len = -1;
			int mi = 0;
			//输出文件
			while((len=bis.read(bytes)) != -1) {
				//加密算法
				for(int i=0; i<bytes.length; i++) {
					mi = bytes[i] ^ password;//与用户密码异或
					bytes[i] = (byte)(mi ^ 127);//再异或127
				}
				
				bos.write(bytes, 0, len);//将加密过的字节写进加密文件中
			}
			
			//关闭流
			bis.close();
			bos.close();
			
			System.out.println("加密完成！");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//-----------------判断要解密的文件是否存在方法------------------
	private void dncFile() {
		Scanner input = new Scanner(System.in);
		System.out.println("输入要解密的文件：");
		String filePath = input.nextLine();
		
		File file = new File(filePath);
		//判断文件是否存在，扩展名是否为 .sp
		if(!file.isFile() || !file.getName().endsWith(".sp")) {
			System.out.println("找不到此文件或文件不是加密文件！");
			dncFile();
			return;
		}
		
		//还原文件名
		StringBuilder sb = new StringBuilder(filePath);
		int a = file.getAbsolutePath().length();//获取文件名长度
		sb.replace(a-3, a+1, "");//去掉 .sp 扩展名
		//创建解密文件
		File newFile = new File(sb.toString());
		
		decrypt(file, newFile);//调用解密方法并把文件传过去
	}
	
	
	//-----------------------解密方法-----------------------------
	private void decrypt(File file, File newFile) {
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			Scanner input = new Scanner(System.in);
			
				System.out.println("输入密码：");
				int password = input.nextInt();
				
				StringBuilder sb = new StringBuilder();
				sb.append(password);
				int pwLen = (bis.read()^33);//将加密的密码位数解密
				//判断密码位数是否一致
				if(sb.length() != pwLen) {
					System.out.println("密码错误！");
					decrypt(file, newFile);
					return;
				}
				
				sb = new StringBuilder();
				int tem;
				//逐位解密密码
				for(int i=0;i<pwLen;i++) {
					tem = bis.read()^66;//解密密码
					sb.append(tem);//连接密码串
				}
				
				tem = Integer.valueOf(sb.toString());//将字符型密码转成int型
				//判断密码是否正确
				if(tem != password) {
					System.out.println("密码错误！");
					decrypt(file, newFile);
					return;
				}
			
			//判断文件是否已存在
			if(newFile.exists()) {
				System.out.println("\n文件已存在，要怎么做？	1、覆盖	2、创建副本 ");
				byte b = input.nextByte();
				
				if(b == 1) {
					System.out.println("将覆盖文件！");
				}else if(b == 2){
					System.out.println("创建副本！");
					newFile = copy(newFile);//调用创建副本方法
				}else {
					System.out.println("创建副本！");
					newFile = copy(newFile);
				}
			}
		
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile),32*1024);
			
			System.out.println("解密中......");
			System.out.println(newFile.getName());
			
			byte[] bytes = new byte[4*1024];
			int len = -1;
			int mi = 0;
			//输出文件主体
			while((len=bis.read(bytes)) != -1) {
				//解密文件
				for(int i=0; i<bytes.length; i++) {
					mi = bytes[i] ^ 127;
					bytes[i] = (byte)(mi ^ password);//与用户密码异或，得到原文件
				}
				
				bos.write(bytes, 0, len);//输出文件
			}
			
			//关闭流
			bis.close();
			bos.close();
			
			System.out.println("解密完成！");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InputMismatchException e) {
			System.out.println("密码异常！");
			decrypt(file, newFile);
		}
	}
	
	
	//-----------------------------创建副本方法----------------------------------
	private File copy(File file) {
		int number = 1;	//副本序号
		String name = file.getAbsolutePath();	//获取文件名
		StringBuilder sb = new StringBuilder(file.getAbsolutePath());
		int index = name.lastIndexOf(".");	//获取后缀名的索引位置
		sb.insert(index,"-副本"+"("+number+")");	//在后缀名前加上“-副本(序号)”
		file = new File(sb.toString());	//创建副本文件
		name = file.getAbsolutePath();	//将原文件名更改为副本的文件名
		
		int x = 2;//索引的偏移量
		int y = 10;
		//如果副本已存在，则创建新副本
		while(file.exists()) {
			number++;	//副本序号+1
			index = name.lastIndexOf(".");//获取后缀名的索引位置
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
			name = file.getAbsolutePath();	//将原文件名更改为新副本的文件名
		}
		return file;
	}
}
