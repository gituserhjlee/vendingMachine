package vendingMachine;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionVO {

	private Date date = new Date();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String dateString = sdf.format(date);// �ش� �ŷ��� �߻��ϴ� ��¥,�ð�

	private String drinkName;
	private int drinkCount;
	private int totalMoney;

	public String getDrinkName() {
		return drinkName;
	}

	public void setDrinkName(String drinkName) {
		this.drinkName = drinkName;
	}

	public int getDrinkCount() {
		return drinkCount;
	}

	public void setDrinkCount(int drinkCount) {
		this.drinkCount = drinkCount;
	}

	public int getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}

	@Override
	public String toString() {
		return dateString + ":" + drinkName + " " + drinkCount + "�� ���� �� ����:" + totalMoney;
	}

}
