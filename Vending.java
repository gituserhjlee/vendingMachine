package vendingMachine;

public class Vending {
	private VendingVO vendingVO;
	private DrinkVO[] drinkList;
	private int totalDrink;

	public Vending() {
		vendingVO = new VendingVO();
		allocation(5);
		totalDrink = 0;
	}

	private void allocation(int capacity) {
		DrinkVO[] temp = new DrinkVO[capacity];
		if (drinkList != null && drinkList.length > 0) {
			System.arraycopy(drinkList, 0, temp, 0, drinkList.length);
		}
		drinkList = temp;
	}

	public boolean addDrink(DrinkVO vo) {
		// ���� ���
		if (totalDrink >= drinkList.length) {
			allocation(drinkList.length + 5);
		}
		drinkList[totalDrink++] = vo;
		System.out.println("���� ��ϵ� ������ ����:" + totalDrink);
		return true;
	}

	// �ܵ�����
	public void returnCharge(DrinkVO[] list, int money, User user, VendingVO v1) {
		int total = 0;// �ֹ� ����Ʈ�� ���� ����
		int charge = 0;// �Ž�����
		int charge1;
		int coin1, coin5;
		for (int i = 0; i < user.getCount(); i++) {
			total += list[i].getDrinkPrice();
		}
		charge = money - total;

		coin5 = charge / 500;
		charge1 = charge % 500;
		coin1 = charge1 / 100;

		System.out.println("�ܵ�: " + charge);
		System.out.println("500���� " + coin5 + "��");
		System.out.println("100���� " + coin1 + "��");

		v1.setCoin1(v1.getCoin1() - coin1);
		v1.setCoin5(v1.getCoin5() - coin5);

	}

	// ���� ����
	public void drinkOut(DrinkVO[] can, int money, User user, VendingVO v1) {
		for (int i = 0; i < user.getCount(); i++) {
			System.out.println(can[i].getDrinkName() + " ����");
		}
		System.out.println("���� �Ϸ�");
		System.out.println("===================");
		returnCharge(can, money, user, v1);

	}

	public VendingVO getVendingVO() {
		return vendingVO;
	}

	public void setVendingVO(VendingVO vendingVO) {
		this.vendingVO = vendingVO;
	}

	public int getTotalDrink() {
		return totalDrink;
	}

	public void setTotalDrink(int totalDrink) {
		this.totalDrink = totalDrink;
	}

	public DrinkVO[] getDrinkList() {
		return drinkList;
	}

	public void setDrinkList(DrinkVO[] drinkList) {
		this.drinkList = drinkList;
	}
}
