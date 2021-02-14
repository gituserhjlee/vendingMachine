package vendingMachine;

import java.util.InputMismatchException;
import java.util.Scanner;

public class User {
	private Scanner sc = new Scanner(System.in);
	private int count = 0; // 주문한 개수

	public int getCount() {
		return count;
	}

	// 자판기에 돈 넣기
	public void deposit(VendingVO v, int money) {
		v.setMoney(v.getMoney() + money / 1000);

	}

	// 음료선택
	public DrinkVO[] selectDrink(Vending v, VendingVO v1, AdminImplMoney ad, int money) {
		count = 0;
		DrinkVO[] list = new DrinkVO[5];// 한번에 최대 뽑을수있는 개수.

		int num;
		if (v.getTotalDrink() == 0 || v1.getCoin1() < 20 || v1.getCoin5() < 20) {
			System.out.println("자판기 내부 동전 부족 또는 주문 가능 음료가 없습니다.");
			return null;
		}
		while (true) {
			try {
				do {
					System.out.println("주문가능금액" + money + "원 고를 음료의 번호를 선택하세요. 주문을 완료하려면 0번을 입력하세요");
					DrinkVO dv = new DrinkVO();
					for (int i = 0; i < v.getTotalDrink(); i++) {
						dv = v.getDrinkList()[i];
						System.out.println((i + 1) + "." + dv.getDrinkName() + ". " + dv.getDrinkPrice() + "원 주문가능수량"
								+ dv.getCount() + "개");
					}
					num = sc.nextInt();
				} while (num < 0 || num > v.getTotalDrink()
						|| (num > 0 && v.getDrinkList()[num - 1].getDrinkPrice() > money)
						|| (num > 0 && v.getDrinkList()[num - 1].getCount() <= 0));
				// 잘못입력 or 가진돈보다 비싼 음료 선택 or 재고가 없는 음료를 선택시 다시 선택
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력하세요");
				sc.nextLine();
				continue;

			}

			if (num == 0) {// 그만고르기
				if (count == 0)// 아무것도 주문 안했을때.
				{
					System.out.println("주문된 음료가 없습니다.");
					return null;
				}

				return list;
			}
			list[count++] = v.getDrinkList()[num - 1]; // 주문리스트에 선택한 음료 넣어주기
			money -= v.getDrinkList()[num - 1].getDrinkPrice(); // 가진 돈에서 주문한 만큼 돈 차감
			v.getDrinkList()[num - 1].setCount(v.getDrinkList()[num - 1].getCount() - 1);// 음료 재고 차감
			v.getDrinkList()[num - 1].setSoldCount(v.getDrinkList()[num - 1].getSoldCount() + 1);// 팔린개수에 추가
			TransactionVO vo = new TransactionVO();
			vo.setDrinkName(v.getDrinkList()[num - 1].getDrinkName()); // 거래내역에 추가시키기
			vo.setDrinkCount(1);
			vo.setTotalMoney(v.getDrinkList()[num - 1].getDrinkPrice());
			ad.addTransaction(vo);

			if (count == 5) {// 최대 주문가능개수 초과
				System.out.println("최대 주문 가능 개수인 5개를 고르셨습니다");
				return list;
			}
			if (money == 0) {
				System.out.println("주문 가능한 금액이 0원입니다");
				return list;
			}

		}

	}

}
