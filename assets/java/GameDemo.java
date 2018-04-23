import java.util.*;
/**
	石头剪刀布小游戏，玩法有三局两胜制和积分制

	@version: 1.1
	@author: CandySorcerer
*/
public class GameDemo
{
	public static void main(String[] args){
		System.out.println("《石头剪刀布》");
		new Games().moshi();
	}
}

/**
	游戏主体类
*/
class Games{
	private Scanner input = new Scanner(System.in);

	//判断游戏模式方法
	public void moshi(){
		System.out.println("请选择模式：	A.三局两胜 B.积分制");
		boolean flag = true;
		while (flag)
		{
			String moshi = input.next();
			switch (moshi)
			{
				case "A":
					bestOfThree();
					flag = false;
				break;
				case "a":
					bestOfThree();
					flag = false;
				break;
				case "B":
					accumulatePoints();
					flag = false;
				break;
				case "b":
					accumulatePoints();
					flag = false;
				break;
				default:
					System.out.println("指令错误！请输入 A 或 B");
				break;
			}
		}
	}

	//三局两胜方法
	private void bestOfThree(){
		
		System.out.println("\n规则：总计胜利2次者赢，非胜即输，平局重开局。");
		Play((byte)2); //传递所需胜利次数
	}

	//积分制方法
	private void accumulatePoints(){
		System.out.println("\n规则：积分优先达到100分者赢。");
		Play((byte)10); //传递所需胜利次数（积分）
	}

	//核心玩法方法
	private void Play(byte num){
		//初始化玩家与电脑的胜利次数
		byte playerIntegral = 0;
		int aiIntegral = 0;
		//决定次数
		for (byte i=1;aiIntegral<num && playerIntegral<num;i++)
		{
			System.out.println("\n第" + i + "局！");
			//如果是积分制，则输出积分结果
			if (num == 10){
			System.out.println("双方当前积分：");
			System.out.println("玩家：" + (playerIntegral*10) + "\n" + "电脑：" + (aiIntegral*10));
			}
			//玩家出拳
			System.out.println("你要出：	1.剪刀 2.石头 3.布");
			String player = input.next();

			int ai = 1+(int)(Math.random()*3);	//电脑随机出拳
			//判断谁赢
			if (ai == 1){
				if (player.equals("1")){
					System.out.println("平局！" + "电脑出 " + ai + ".剪刀");
				}else if (player.equals("2")){
					System.out.println("你赢了！" + "电脑出 " + ai + ".剪刀");
					playerIntegral++;
				}else if (player.equals("3")){
					System.out.println("你输了！" + "电脑出 " + ai + ".剪刀");
					aiIntegral++;
				}else{
					System.out.println("错误，请输入正确指令！");
					}
			}else if (ai == 2){
					if (player.equals("1")){
						System.out.println("你输了！" + "电脑出 " + ai + ".石头");
						aiIntegral++;
					}else if (player.equals("2")){
						System.out.println("平局！" + "电脑出 " + ai + ".石头");
					}else if (player.equals("3")){
						System.out.println("你赢了！" + "电脑出 " + ai + ".石头");
						playerIntegral++;
					}else{
					System.out.println("错误，请输入正确指令！");
					}
			}else if (ai == 3){
					if (player.equals("1")){
						System.out.println("你赢了！" + "电脑出 " + ai + ".布");
						playerIntegral++;
					}else if (player.equals("2")){
						System.out.println("你输了！" + "电脑出 " + ai + ".布");
						aiIntegral++;
					}else if (player.equals("3")){
						System.out.println("平局！" + "电脑出 " + ai + ".布");
					}else{
					System.out.println("错误，请输入正确指令！");
					}
			}
		}
		judge(aiIntegral, playerIntegral, num);
	}

	//判断最终输赢方法
	private void judge(int aiIntegral, byte playerIntegral, byte num){
		System.out.println("\n最终比赛结果：");
		//如果是积分制，则输出积分结果
		if (num == 10)
			System.out.println("玩家：" + (playerIntegral*10) + "\n电脑：" + (aiIntegral*10));
		if (aiIntegral > playerIntegral){
			System.out.println("\n很遗憾，再接再厉！");
		}else{
			System.out.println("\n恭喜你，你胜利了！");
		}
		//判断是否继续游戏
		System.out.println("\n再来一局？	Y - Yes , 关闭窗口 - No");
		String x = input.next();
		if (x.equals("Y") || x.equals("y")) moshi();
	}
}