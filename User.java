package vendingMachine;

import java.util.InputMismatchException;
import java.util.Scanner;

public class User {
	private Scanner sc = new Scanner(System.in);
	private int count = 0; // �ֹ��� ����

	public int getCount() {
		return count;
	}

	// ���Ǳ⿡ �� �ֱ�
	public void deposit(VendingVO v, int money) {
		v.setMoney(v.getMoney() + money / 1000);

	}

	// ���ἱ��
	public DrinkVO[] selectDrink(Vending v, VendingVO v1, AdminImplMoney ad, int money) {
		count = 0;
		DrinkVO[] list = new DrinkVO[5];// �ѹ��� �ִ� �������ִ� ����.

		int num;
		if (v.getTotalDrink() == 0 || v1.getCoin1() < 20 || v1.getCoin5() < 20) {
			System.out.println("���Ǳ� ���� ���� ���� �Ǵ� �ֹ� ���� ���ᰡ �����ϴ�.");
			return null;
		}
		while (true) {
			try {
				do {
					System.out.println("�ֹ����ɱݾ�" + money + "�� �� ������ ��ȣ�� �����ϼ���. �ֹ��� �Ϸ��Ϸ��� 0���� �Է��ϼ���");
					DrinkVO dv = new DrinkVO();
					for (int i = 0; i < v.getTotalDrink(); i++) {
						dv = v.getDrinkList()[i];
						System.out.println((i + 1) + "." + dv.getDrinkName() + ". " + dv.getDrinkPrice() + "�� �ֹ����ɼ���"
								+ dv.getCount() + "��");
					}
					num = sc.nextInt();
				} while (num < 0 || num > v.getTotalDrink()
						|| (num > 0 && v.getDrinkList()[num - 1].getDrinkPrice() > money)
						|| (num > 0 && v.getDrinkList()[num - 1].getCount() <= 0));
				// �߸��Է� or ���������� ��� ���� ���� or ��� ���� ���Ḧ ���ý� �ٽ� ����
			} catch (InputMismatchException e) {
				System.out.println("���ڸ� �Է��ϼ���");
				sc.nextLine();
				continue;

			}

			if (num == 0) {// �׸�����
				if (count == 0)// �ƹ��͵� �ֹ� ��������.
				{
					System.out.println("�ֹ��� ���ᰡ �����ϴ�.");
					return null;
				}

				return list;
			}
			list[count++] = v.getDrinkList()[num - 1]; // �ֹ�����Ʈ�� ������ ���� �־��ֱ�
			money -= v.getDrinkList()[num - 1].getDrinkPrice(); // ���� ������ �ֹ��� ��ŭ �� ����
			v.getDrinkList()[num - 1].setCount(v.getDrinkList()[num - 1].getCount() - 1);// ���� ��� ����
			v.getDrinkList()[num - 1].setSoldCount(v.getDrinkList()[num - 1].getSoldCount() + 1);// �ȸ������� �߰�
			TransactionVO vo = new TransactionVO();
			vo.setDrinkName(v.getDrinkList()[num - 1].getDrinkName()); // �ŷ������� �߰���Ű��
			vo.setDrinkCount(1);
			vo.setTotalMoney(v.getDrinkList()[num - 1].getDrinkPrice());
			ad.addTransaction(vo);

			if (count == 5) {// �ִ� �ֹ����ɰ��� �ʰ�
				System.out.println("�ִ� �ֹ� ���� ������ 5���� ���̽��ϴ�");
				return list;
			}
			if (money == 0) {
				System.out.println("�ֹ� ������ �ݾ��� 0���Դϴ�");
				return list;
			}

		}

	}

}
