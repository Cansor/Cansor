import java.util.Scanner;
/**
	������Ϸ

	@author: CandySorcerer
	@version: 1.2
*/
public class GuessNumber
{
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args){
		System.out.println("��������Ϸ��");
		System.out.println("���򣺵����������һ�����������ȥ��������֡�");
		play(false);
	}

	//ѡ���Ѷ�
	private static void play(boolean flags){
		int sum = 0;	//������ķ�Χ
		boolean flag = false;	//����Ƿ�Ϊ����ģʽ
		System.out.println("\n��ѡ���Ѷȣ�");
		System.out.println("1.�򵥣�0-99�� 2.һ�㣨0-999�� 3.���ѣ�0-9999��");
		//ͨ������ģʽ�ŻῪ������ģʽ
		if (flags)
		System.out.println("5.������ÿ��һ�ε��Զ������������0-9��");

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
				flag = true;	//��ǰΪ����ģʽ
				sum = 10000;
			}else if (flags && i == 5)
			{
				sum = 10;
			}
		}
		judge(sum,flag,flags);
	}

	//��Ϸ����
	private static void judge(int sum,boolean flag,boolean flags){
		int num1 = (int)(Math.random()*sum);	//��������һ�������
		int i=1;
		for (; i!=-1; i++)
		{
			if (flags) num1 = (int)(Math.random()*sum);	//����ģʽ�������
			System.out.println("\n��"+i+"��");

			System.out.println("������һ��������");
			int num2 = input.nextInt();
			//�ж�����Ƿ�¶�
			if (num1 == num2)
			{
				System.out.println("�����������¶��ˣ�");
				break;
			}else if (num1 > num2)
			{
				System.out.println("���Բ��ԣ���С�ˣ�");
			}else if (num1 < num2)
			{
				System.out.println("���Բ��ԣ��´��ˣ�");
			}
		}
		//������
		System.out.println("\n�����ǣ�" + num1);
		System.out.println("һ�����ˣ�" + i + "��");

		System.out.println("\nY - ����һ�� N - �����˵�");
		String s = input.next();
		if (s.equals("Y") || s.equals("y")){
			judge(sum,flag,flags);
		}else if (s.equals("N") || s.equals("n"))
		{
			//�ж��Ƿ�Ϊ����ģʽ
			if (flag)
			{
				flags = true;	//��������ģʽ
				play(flags);
			}else{
			play(flags);
			}
		}
	}
}