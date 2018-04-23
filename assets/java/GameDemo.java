import java.util.*;
/**
	ʯͷ������С��Ϸ���淨��������ʤ�ƺͻ�����

	@version: 1.1
	@author: CandySorcerer
*/
public class GameDemo
{
	public static void main(String[] args){
		System.out.println("��ʯͷ��������");
		new Games().moshi();
	}
}

/**
	��Ϸ������
*/
class Games{
	private Scanner input = new Scanner(System.in);

	//�ж���Ϸģʽ����
	public void moshi(){
		System.out.println("��ѡ��ģʽ��	A.������ʤ B.������");
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
					System.out.println("ָ����������� A �� B");
				break;
			}
		}
	}

	//������ʤ����
	private void bestOfThree(){
		
		System.out.println("\n�����ܼ�ʤ��2����Ӯ����ʤ���䣬ƽ���ؿ��֡�");
		Play((byte)2); //��������ʤ������
	}

	//�����Ʒ���
	private void accumulatePoints(){
		System.out.println("\n���򣺻������ȴﵽ100����Ӯ��");
		Play((byte)10); //��������ʤ�����������֣�
	}

	//�����淨����
	private void Play(byte num){
		//��ʼ���������Ե�ʤ������
		byte playerIntegral = 0;
		int aiIntegral = 0;
		//��������
		for (byte i=1;aiIntegral<num && playerIntegral<num;i++)
		{
			System.out.println("\n��" + i + "�֣�");
			//����ǻ����ƣ���������ֽ��
			if (num == 10){
			System.out.println("˫����ǰ���֣�");
			System.out.println("��ң�" + (playerIntegral*10) + "\n" + "���ԣ�" + (aiIntegral*10));
			}
			//��ҳ�ȭ
			System.out.println("��Ҫ����	1.���� 2.ʯͷ 3.��");
			String player = input.next();

			int ai = 1+(int)(Math.random()*3);	//���������ȭ
			//�ж�˭Ӯ
			if (ai == 1){
				if (player.equals("1")){
					System.out.println("ƽ�֣�" + "���Գ� " + ai + ".����");
				}else if (player.equals("2")){
					System.out.println("��Ӯ�ˣ�" + "���Գ� " + ai + ".����");
					playerIntegral++;
				}else if (player.equals("3")){
					System.out.println("�����ˣ�" + "���Գ� " + ai + ".����");
					aiIntegral++;
				}else{
					System.out.println("������������ȷָ�");
					}
			}else if (ai == 2){
					if (player.equals("1")){
						System.out.println("�����ˣ�" + "���Գ� " + ai + ".ʯͷ");
						aiIntegral++;
					}else if (player.equals("2")){
						System.out.println("ƽ�֣�" + "���Գ� " + ai + ".ʯͷ");
					}else if (player.equals("3")){
						System.out.println("��Ӯ�ˣ�" + "���Գ� " + ai + ".ʯͷ");
						playerIntegral++;
					}else{
					System.out.println("������������ȷָ�");
					}
			}else if (ai == 3){
					if (player.equals("1")){
						System.out.println("��Ӯ�ˣ�" + "���Գ� " + ai + ".��");
						playerIntegral++;
					}else if (player.equals("2")){
						System.out.println("�����ˣ�" + "���Գ� " + ai + ".��");
						aiIntegral++;
					}else if (player.equals("3")){
						System.out.println("ƽ�֣�" + "���Գ� " + ai + ".��");
					}else{
					System.out.println("������������ȷָ�");
					}
			}
		}
		judge(aiIntegral, playerIntegral, num);
	}

	//�ж�������Ӯ����
	private void judge(int aiIntegral, byte playerIntegral, byte num){
		System.out.println("\n���ձ��������");
		//����ǻ����ƣ���������ֽ��
		if (num == 10)
			System.out.println("��ң�" + (playerIntegral*10) + "\n���ԣ�" + (aiIntegral*10));
		if (aiIntegral > playerIntegral){
			System.out.println("\n���ź����ٽ�������");
		}else{
			System.out.println("\n��ϲ�㣬��ʤ���ˣ�");
		}
		//�ж��Ƿ������Ϸ
		System.out.println("\n����һ�֣�	Y - Yes , �رմ��� - No");
		String x = input.next();
		if (x.equals("Y") || x.equals("y")) moshi();
	}
}