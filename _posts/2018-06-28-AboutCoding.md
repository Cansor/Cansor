---
layout: post
title: "关于文本编码的问题"
author: "Candy Sorcerer"
categories: help
tags: [help]
image:
---

***

这里发布的源代码，文本编码方式一般为 UTF-8 或 GBK ,如果在打开时乱码或者在编译时报错，有两种选择：

一种是将开放环境所使用的编码临时更改为源码文件的编码，例如 Eclipes ,在 窗口——首选项——常规——工作空间（Window-preferences-General-Workspace）中，下方的文本编码选项里点选其他（other）即可修改。坏处是打开自己的代码时可能又会不兼容，最后还得改回来。

<img src="https://i.loli.net/2018/06/30/5b378b9e6288e.png" alt="Eclipes" title="Eclipes" />

另一种是将源码文件的编码转换成你的开发环境所用的编码，一劳永逸。

这里以 EditPlus 为例做一个说明，你也可以使用其他工具甚至自己写程序来转换：

用 EditPlus 打开 .java 文件，在上方菜单栏选择 文档——文本编码——转换文本编码。

<img src="https://i.loli.net/2018/06/28/5b34beebb5046.png" alt="转换文本编码" title="转换文本编码" />

点击“转换文本编码”后，点击“选择编码方式”即可看到编码列表。

<img src="https://i.loli.net/2018/06/28/5b34b54291a04.png" alt="选择编码方式" title="选择编码方式" />

然后选择你需要转换成的编码（<b>如果没有修改过开发环境的编码，一般是系统默认的编码</b>），点击确定后，再点击左上方的保存按钮（快捷键 Ctrl+S）即可完成转码。

批量转码可以从左边的目录中选择文件双击，同时打开多个文件，然后选择批量转换编码，在弹出的选择文件框中按 Ctrl+A 键全选，点击确定后选择编码即可。

至于 EditPlus 的下载，通过搜索引擎搜索就能找到，这里放出官方地址：

<line>
<a href="https://www.editplus.com/" target="_blank">https://www.editplus.com/</a>
</line>

有什么问题可以点击页面上方的 Contact 联系。

<br><br><br><br>

