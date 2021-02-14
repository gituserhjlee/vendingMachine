package vendingMachine;

public interface Admin {
	// �����Ա�
	public void deposit(int coin1, int coin5);

	// �������
	public void withdraw(int money);

	// �����ܾ���ȸ
	public void showMoney(int money);

	// �Ž����� ����Ȯ��
	public void showCoins(int coin5, int coin1);

	// ���� ���� �Լ�
	public void salesInfo();

	// ������ ��й�ȣ �´��� Ȯ��
	public boolean isRightPassword(String password);

	// ���� ä���
	public void addDrink();

	// ���� ����
	public void deleteDrink(String drinkNum);

	// ���� ���� ����
	public void changeDrink(String drinkName);
}
