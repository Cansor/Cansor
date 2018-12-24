---
layout: post
title: "[JavaSE] IO之字节流-文件复制"
author: "Cansor"
categories: JavaDemo
tags: [源码,小程序,demo]
image:
---

<br>
IO流的输入输出，复制文件的操作（覆盖、创建副本、续写）。

***

使用FileInputStream来读取文件，FileOutputStream来输出文件，至于文件续写，创建FileOutputStream用有两个参数的构造器，第一个参数是File文件对象，第二个参数是一个布尔值，为true时将在文件末尾写入数据，false则会覆盖。

``` java
File file = new File("D:/demo.zip");
//输出文件
OutputStream out = new FileOutputStream(file, true);//输出流
BufferedOutputStream bos = new BufferedOutputStream(out,1024*64);//不要忘了缓冲流，第二个参数是自定义缓冲区大小
```
<br>
判断文件是否存在，用File类的exists()方法即可；文件已存在要与原文件共存的话，可以在文件名末尾加上如“-副本(1)”之类的。  
通过StringBuilder类的lastIndexOf(String str)方法，参数传“.”即可得到最后一次出现的“.”的索引（即文件后缀名），再使用insert()方法在索引之前的位置插入“-副本(1)”，就能得到一个新的文件名，将此文件名封装进File对象传给FileOutputStream，新命名的文件就能与原有文件共存了。

``` java
private File copys(File file,String path) {
    int number = 1;//副本序号
    String name = file.getName();//获取文件名
    StringBuilder sb = new StringBuilder(name);

    int index = name.lastIndexOf(".");//获取后缀名的索引位置
    sb.insert(index,"-副本"+"("+number+")");//在后缀名前加上“副本(x)”

    file = new File(path + sb.toString());//创建副本文件对象
    name = path + file.getName();//将name的值（原文件名）更改为副本的文件名
	
    int x = 2;//索引的偏移量
    int y = 10;

    //如果副本已存在，则创建新副本
    while(file.exists()) {
	number++;//副本序号+1
	index = name.lastIndexOf(".");
	sb = new StringBuilder(name);
		
	//如果副本序号大于y，索引偏移量需要+1，y需要*10
	//因为序号位数每次多一位，文件名的长度就会+1，所以偏移量就需要+1
	if(number>y) {
	    x++;
	    y *= 10;
	}
	sb.replace(index-x, index-(x-1), ""+number);//替换副本序号
	file = new File(sb.toString());
	name = path + file.getName();//将name的值（原文件名）更改为副本的文件名
    }
    return file;
}
```
<br><br>
### 源码

<a href="{{ site.github.url }}/assets/code-java/CopyFileDemo.java" class="line-color">CopyFileDemo.java</a>

提示：使用 右键—另存为 的方式下载。

程序说明：输入的路径必须是绝对路径，如果文件已存在，可以选择覆盖文件或是创建一个副本、续写（在文件尾部写入数据，目前好像没什么用······）或跳过。

vresion: 1.3	重构代码，修复一处BUG.  
version: 1.2	修复副本的排序BUG.  
version: 1.1    优化代码逻辑，增加缓冲流。

<br><br><br><br>

