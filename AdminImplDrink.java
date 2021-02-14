package vendingMachine;

import java.util.InputMismatchException;
import java.util.Scanner;

//������-����
public class AdminImplDrink implements Admin {
	private Scanner sc = new Scanner(System.in);
	private Vending v;
	private String password = "admin";

	public AdminImplDrink(Vending v) {

		this.v = v;
	}

	@Override
	public void deposit(int coin1, int coin5) {
		// TODO Auto-generated method stub

	}

	@Override
	public void withdraw(int money) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showMoney(int money) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showCoins(int coin5, int coin1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void salesInfo() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isRightPassword(String password) {
		if (password.equals(this.password)) {
			return true;
		}
		return false;

	}

	@Override
	public void addDrink() {
		try {
			DrinkVO drink = new DrinkVO();
			System.out.println("�����̸��� �Է��ϼ���.");
			drink.setDrinkName(sc.next());
			System.out.println("���ᰡ���� �Է��ϼ���.");
			drink.setDrinkPrice(sc.nextInt());
			System.out.println("���� ������ �Է��ϼ���.");
			drink.setCount(sc.nextInt());
			v.addDrink(drink);

		} catch (InputMismatchException e) {
			System.out.println("�Է� ������ Ʋ���ϴ�.");
			sc.nextLine();
		}

	}

	@Override
	public void deleteDrink(String drinkName) {
		DrinkVO vo = loadDrinkName(drinkName);
		if (vo == null) {
			System.out.println("��ϵ� ���ᰡ �����ϴ�");
			return;
		}
		int index = 0;
		DrinkVO[] list = v.getDrinkList();

		for (int i = 0; i < v.getTotalDrink(); i++) {
			if (v.getDrinkList()[i].getDrinkName().equals(drinkName)) {
				index = i;
				break;
			}
		}
		for (int i = index; i < v.getTotalDrink() - 1; i++) {
			list[i] = list[i + 1];
		}
		list[v.getTotalDrink() - 1] = null;
		v.setTotalDrink(v.getTotalDrink() - 1);
		System.out.println("������ �Ϸ�Ǿ����ϴ�");

	}

	@Override
	public void changeDrink(String drinkName) {
		DrinkVO drink;
		drink = loadDrinkName(drinkName);
		if (drink == null) {
			System.out.println("��ϵ� ���ᰡ �����ϴ�");
			return;
		} else {
			System.out.print("���� �̸��� �Է��ϼ���.");
			drink.setDrinkName(sc.next());
			System.out.print("���� ������ �Է��ϼ���");
			drink.setDrinkPrice(sc.nextInt());
			System.out.println("���� ������ �Է��ϼ���");
			drink.setCount(sc.nextInt());

			System.out.println(drink.getDrinkName() + "�� ���������� �����Ǿ����ϴ�.");
		}

	}

	public DrinkVO loadDrinkName(String drinkName) {
		DrinkVO vo = null;
		for (int i = 0; i < v.getTotalDrink(); i++) {
			DrinkVO vv = v.getDrinkList()[i];
			if (vv.getDrinkName().equals(drinkName)) {
				vo = vv;
				break;
			}
		}

		return vo;

	}

}
