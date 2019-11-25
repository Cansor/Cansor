---
layout: post
title: "使用VMware安装MacOS系统"
author: "Cansor"
categories: test-course
tags: [教程]
image: https://i.loli.net/2018/12/24/5c20772508aa6.png
---

<br>

利用VMware虚拟机，可以在Windows体验Mac OS系统。

<hr>

<br/><br/>

## 工具

以下工具也可以自己从网上下载，这里提供一些：

VMware虚拟机：<a href="https://pan.baidu.com/s/1LeA0bO7L7KI7EykX-Yb-_g" target="_blank" class="line-color" title="VM虚拟机下载">https://pan.baidu.com/s/1LeA0bO7L7KI7EykX-Yb-_g</a>  
密钥：<span class="hide">CG392-4PX5J-H816Z-HYZNG-PQRG2</span>

MacOS Unlocker for VMware：<a href="https://github.com/DrDonk/unlocker" target="_blank" class="line-color" title="解锁器下载">https://github.com/DrDonk/unlocker</a>

Mac OS 系统镜像：<a href="https://pan.baidu.com/s/1teSR1320fXhaLn5cdUwc6Q" target="_blank" class="line-color" title="Mac OS下载">https://pan.baidu.com/s/1teSR1320fXhaLn5cdUwc6Q</a>  
提取码: tgsc  
注意VMware不支持dmg镜像，请下载iso或者cdr镜像。

## 配置虚拟机

安装好VMware后，这个时候的VMware还没有 Apple Mac OS X 的选项，需要先对VMware进行破解才行。

先退出VMware，然后打开服务（开始菜单中搜索“服务”），如图把VMware的五项服务全部停止。

![icon](https://i.loli.net/2018/12/24/5c205a7d0345b.png)

<br/>
全部关闭之后解压 MacOS Unlocker for VMware 工具的压缩包，找到 win-install.cmd 文件右键，以管理员身份运行此文件，等待安装完成；安装过程不要关闭控制台窗口，程序安装完成后会自动关闭。安装成功后目录中会多出两个文件夹。

![icon](https://i.loli.net/2018/12/24/5c205d518c9ba.png)

<br/>
这个时候打开VMware，点击“文件——新建虚拟机”，选择典型的方式创建，点下一步，选择光盘镜像文件安装（目录最好不要有中文），这个时候会出现无法检测的情况，不要管它，直接点下一步。

![icon](https://i.loli.net/2018/12/24/5c20623d354c6.png)

<br/>
这时操作系统选择 Apple Mac OS X 的选项（如果没有这个选择则说明破解不成功，按照上面所说的重新破解吧），然后选择与安装的系统镜像一致的版本，点击下一步。

![icon](https://i.loli.net/2018/12/24/5c20629938e25.png)

<br/>
这一步虚拟机名称可以自定义，位置不要放C盘，另外选个位置，然后记住这个路径，后面要用到，点下一步。

指定磁盘容量，根据需要选择，一般默认的40G够了，系统装完一般占用10+G，下面的虚拟磁盘可以选择拆分成多个文件也可以选择单个，点击下一步。

这个时候需要设置一下硬件，点自定义硬件，内存根据自己计算机的物理内存和使用情况来分，比如8G的内存可以分出3-4G给虚拟机，内存大的则可以分配多一些，注意要留出足够的内存给系统。

例如这里使用的 Mac OS 10.13 系统分配给虚拟机的内存不建议低于3G。

处理器数量，根据自己机器的处理器数量选择。

网络适配器默认NAT，也可以选择桥接；其他保持默认就行。

![icon](https://i.loli.net/2018/12/24/5c206e916cde5.png)

<br/>
设置好后点关闭，然后点完成，这时先别启动虚拟机，还有最后一步需要设置。

找到你刚刚安装虚拟机时选择的路径，找到 xxxxxx.vmx 这个文件；

![icon](https://i.loli.net/2018/12/24/5c207123a458c.png)

<br/>
用编辑器打开，在 smc.present = "TRUE" 这一行下面写上 smc.version = "0"

![icon](https://i.loli.net/2018/12/24/5c2071239ae87.png)

保存并退出，这时可以启动虚拟机了。

<br/><br/>

## 安装 Mac OS 系统

点击“开启此虚拟机”，然后等待启动，等待一会后出现选择语言的界面，选择需要的语言，然后一路常规设置，直到出现没有足够的空间安装的提示。

此时点击上方的工具，选择磁盘工具；

![icon](https://i.loli.net/2018/12/24/5c2074efb1df6.png)

<br/>
然后选择 VMware... 的那个磁盘，点击抹掉，弹出一个窗口，名称可以自己命名，然后再次点击抹掉。

![icon](https://i.loli.net/2018/12/24/5c20757a885b3.png)

<br/>
抹掉之后点击上方的磁盘工具——退出磁盘工具，这时候点击多出来的一个磁盘，点击继续就OK了。之后就是等待安装完成以及一些常规操作了。

![icon](https://i.loli.net/2018/12/24/5c20772508aa6.png)

<br/>
由于前面把VMwaer的5项服务给关了，所以这个时候是没网的，设置网络的时候直接跳过就好，等以后再设置。

至此，Mac OS 操作系统安装完成，这个时候可以把VMwaer的5项服务给打开了（其实在破解完VMware后就可以打开了）。

<br/><br/>

## 优化体验

首先，启动Mac OS，右键点击光盘图标，选择推出xxx；

![icon](https://i.loli.net/2018/12/24/5c2079e8ea677.png)

<br/>
然后点击上方的虚拟机，安装VMware Tools，这个选项平时是无法点击的，需要启动虚拟机才可以点击。

![icon](https://i.loli.net/2018/12/24/5c20787904825.png)

<br/>
然后打开新出现的图标，点击安装 VMware Tools.app 的图标，根据提示开始安装。第一次安装会受到系统的阻止，根据提示设置好后再次安装即可。

安装完成后，点击上方的苹果图标，点关于本机，可以看到显存增加到了128M……  
（在这之前好像只有4M吧，虽然还是很少，但至少比之前好多了…… orz）

![icon](https://i.loli.net/2018/12/24/5c207c0278fbd.png)

<br/>
然后继续到虚拟机的设置里设置一下显示器，把拉伸模式打开，选择保持纵横比拉伸，在全屏模式下Mac OS系统的窗口就可以扩大了（部分机器还可以在Mac OS里设置分辨率）。没安装 VMware Tools 的话这个设置是不会生效的。

![icon](https://i.loli.net/2018/12/24/5c207dd835997.png)

<br/>
最后，如果觉得卡的话，就在Mac OS的系统偏好设置里把一些特效给关掉吧：

进入“辅助功能” 然后勾选”减少透明度”；  
进入”Dock”最小化窗口时使用选择“缩放效果”；  
进入“扩展”，然后将不使用的组件勾掉。

设置完成，现在，可以去体验一下Mac OS系统了。

<br><br><br>

