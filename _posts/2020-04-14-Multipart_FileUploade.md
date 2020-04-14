---
layout: post
title: "使用 Multipart 进行文件上传"
author: "Cansor"
categories: text-Java
tags: [教程]
image: 
---

<br/>

SpringMVC的MultipartHttpServletRequest，通过对HttpServletRequest接口进行扩展，使得可以很好的处理文件上传。

<hr/>
<br/>
既然是前端将文件上传到服务器，那么我们先来看下前端的编写：

```html
<form method="post" enctype="multipart/form-data">
    <input type="file" name="file_1" />
    <input type="submit" value="保存" />
</form>
```

这里要注意的是，form表单的标签上必须写上 enctype=&quot;multipart/form-data&quot;，且提交方式为post。



那么后端怎么接收呢，像这样：

```java
 public Result upload(HttpServletRequest request){
     //当然也可以把MultipartHttpServletRequest直接写在方法的形参上，就不需要转换了
     MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
     //使用getFile方法获取文件，参数就是前端定义的name
     MultipartFile multipartFile = multipartRequest.getFile("file_1");
     //获取输入以流读取文件内容
     InputStream is = multipartFile.getInputStream();
     
     ......
 }
```

通过使用MultipartHttpServletRequest的getFile(String str)方法可以获取到前端上传的文件，方法的参数就是前端的name的值；因为前端可以同时上传多个文件，所以需要一个参数来判断你要获取的是哪个文件。  

getFile返回的是MultipartFile对象，这个对象包含了文件的各种信息，例如文件名，大小等等……  

使用getInputStream来获得一个输入流，通过输入流来读取文件的内容，然后再用输出流把文件写到本地，一个文件上传的流程就走完了。

```java
//getOriginalFilename方法可以获取到文件名
String fileName = multipartFile.getOriginalFilename();
//构建缓冲流
BufferedInputStream bis = new BufferedInputStream(multipartFile.getInputStream());
BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("D:/"+fileName));

int len = 0;
byte[] bytes = new byte[8*1024];

//写入磁盘
while ((len=bis.read(bytes)) != -1){
    bos.write(bytes,0,len);
}

bos.close();
bis.close();
```
<br/><br/>
如果前端不只是上传文件，也有其他参数呢，比如：

```html
<form method="post" enctype="multipart/form-data">
    <input type="file" name="file_1" />
    姓名：<input type="text" name="name" />
    年龄：<input type="number" name="age" />
    <input type="submit" value="保存" />
</form>
```
<br/>
别忘了，MultipartHttpServletRequest是对HttpServletRequest接口的扩展，也就是继承了HttpServletRequest，而HttpServletRequest又基础了ServletRequest，所以我们可以使用ServletRequest的getParameterMap()方法来获取表单里的所有参数，返回类型是一个Map&lt;String, String[]&gt;类型的集合；  
key就是前端的name属性的值，value就是对应的值了。

```java
Map<String, String[]> parameterMap = multipartRequest.getParameterMap();
```
<br/>
通过操作map集合，就可以拿到前端传过来的所有的普通表单内容了。
<br/>
---
<br/>
***如果前端使用ajax的话，可以使用FormDate来上传：

```html
<form enctype="multipart/form-data">
    <input type="file" name="file_1" />
    姓名：<input type="text" name="name" />
    年龄：<input type="number" name="age" />
    <input type="button" value="保存" class="save" />
</form>

<script>
    var form = document.getElementsByClassName('hoForm');
    var formdata = new FormData(form[0]);
    
    $('.save').click(function(){
        $.ajax({
            url: url,
            type: "POST",
            data: formdata,
            cache: false,
            processData: false, // 告诉jQuery不要去处理发送的数据
            contentType: false, // 告诉jQuery不要去设置Content-Type请求头
            success: function(result){},
            error: function(){}
        });
    }
</script>
```
<br/>
<hr/>
<br/><br/>

## MultipartHttpServletRequest常用API

```java
Iterator<String> getFileNames();
```
获得文件名，返回的是一个迭代器，通过遍历可以获得上传的文件的所有文件名。  
但要注意，该方法获取的其实是前端name属性的值，并不是真正的文件名。



```java
MultipartFile getFile(String name);
```
获取文件，返回的是一个MultipartFile对象，该对象包含了文件的一些信息，如文件名（真·文件名）、大小等等。

```java
List<MultipartFile> getFiles(String var1);
```
获取所有上传的文件，返回一个 List&lt;MultipartFile&gt; 类型的集合。

```java
Map<String, MultipartFile> getFileMap();
```
也是获取所有上传的文件，不过返回的是Map集合。以前端的name属性的值为key，value则是对应的文件（也是MultipartFile对象）。
<br/><br/>

## MultipartFile 常用API

```java
String getName();
```
获取的是文件的前端name属性的值。

```java
String getOriginalFilename();
```
获取文件的原（真）文件名。

```java
long getSize();
```
获取文件的大小，单位byte；

```java
InputStream getInputStream() throws IOException;
```
获取输入流；该方法抛出一个IO异常。

```java
boolean isEmpty();
```
判断是否为空；

<br><br><br>