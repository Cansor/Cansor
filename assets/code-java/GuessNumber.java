import java.util.Scanner;
/**
	猜数游戏

	@author: CandySorcerer
	@version: 1.2
*/
public class GuessNumber
{
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args){
		System.out.println("《猜数游戏》");
		System.out.println("规则：电脑随机生成一个整数，玩家去猜这个数字。");
		play(false);
	}

	//选择难度
	private static void play(boolean flags){
		int sum = 0;	//随机数的范围
		boolean flag = false;	//标记是否为困难模式
		System.out.println("\n请选择难度：");
		System.out.println("1.简单（0-99） 2.一般（0-999） 3.困难（0-9999）");
		//通关困难模式才会开启地狱模式
		if (flags)
		System.out.println("5.地狱（每猜一次电脑都会重新随机，0-9）");

		int i = input.nextInt();
		while (sum == 0)
		{
			if (i == 1)
			{
				sum = 100;
			}else if (i == 2)
			{
				sum = 1000;
			}else if (i == 3)
			{
				flag = true;	//当前为困难模式
				sum = 10000;
			}else if (flags && i == 5)
			{
				sum = 10;
			}
		}
		judge(sum,flag,flags);
	}

	//游戏主体
	private static void judge(int sum,boolean flag,boolean flags){
		int num1 = (int)(Math.random()*sum);	//电脑生成一个随机数
		int i=1;
		for (; i!=-1; i++)
		{
			if (flags) num1 = (int)(Math.random()*sum);	//地狱模式重新随机
			System.out.println("\n第"+i+"次");

			System.out.println("请输入一个整数：");
			int num2 = input.nextInt();
			//判断玩家是否猜对
			if (num1 == num2)
			{
				System.out.println("厉害厉害，猜对了！");
				break;
			}else if (num1 > num2)
			{
				System.out.println("不对不对，猜小了！");
			}else if (num1 < num2)
			{
				System.out.println("不对不对，猜大了！");
			}
		}
		//输出结果
		System.out.println("\n数字是：" + num1);
		System.out.println("一共用了：" + i + "次");

		System.out.println("\nY - 再来一次 N - 回主菜单");
		String s = input.next();
		if (s.equals("Y") || s.equals("y")){
			judge(sum,flag,flags);
		}else if (s.equals("N") || s.equals("n"))
		{
			//判断是否为困难模式
			if (flag)
			{
				flags = true;	//开启地狱模式
				play(flags);
			}else{
			play(flags);
			}
		}
	}
}