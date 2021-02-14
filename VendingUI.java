package vendingMachine;

import java.util.InputMismatchException;
import java.util.Scanner;

public class VendingUI {

	private Vending v = new Vending();
	private VendingVO v1 = v.getVendingVO();
	private Admin ad = new AdminImplMoney(v1);
	private Admin ad2 = new AdminImplDrink(v);
	private User user = new User();
	private Scanner sc = new Scanner(System.in);

	public void menu() {
		int ch;
		String password;
		while (true) {
			try {
				do {
					System.out.println("1. 관리자모드  2.사용자모드 3.종료");
					ch = sc.nextInt();
				} while (ch < 1 || ch > 3);
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력하세요");
				sc.nextLine();
				continue;
			}

			if (ch == 3)
				break;
			if (ch == 1) {
				System.out.println("비밀번호 입력하세요");
				password = sc.next();
				boolean check = ad2.isRightPassword(password);
				if (check == false) {
					System.out.println("비밀번호가 틀립니다");
					continue;
				}
				while (true) {
					try {
						do {
							System.out.println("-----------------------------------------------------------------");
							System.out.println("1.동전입금  2.지폐출금  3.지폐잔액조회  4.동전수량확인  5.음료관리  6.매출관리 7.종료");
							System.out.println("------------------------------------------------------------------");
							System.out.print("선택=> ");
							ch = sc.nextInt();
						} while (ch < 1 || ch > 7);
					} catch (InputMismatchException e) {

						System.out.println("숫자만 입력하세요");
						sc.nextLine();
						continue;
					}

					if (ch == 7) {
						break;
					}

					switch (ch) {
					case 1:
						deposit();
						break;
					case 2:
						withdraw();
						break;
					case 3:
						showMoney();
						break;
					case 4:
						showCoins();
						break;
					case 5:
						drinkInfo();
						break;
					case 6:
						salesInfo();
						break;

					}
				}

			}

			else if (ch == 2) {
				while (true) {
					try {
						do {
							System.out.println("-----------------------------------------------------");
							System.out.println("1.지폐투입 2.종료");
							System.out.println("-----------------------------------------------------");
							System.out.print("선택=> ");
							ch = sc.nextInt();
						} while (ch < 1 || ch > 2);
					} catch (InputMismatchException e) {
						System.out.println("숫자만 입력하세요");
						sc.nextLine();
						continue;
					}

					if (ch == 2) {
						break;
					}

					int money;//투입금액
					try {
						do {
							System.out.println("넣을 돈을 천원단위로 입력하세요(최대 만원)");
							money = sc.nextInt();
						} while (money % 1000 != 0 || money > 10000 || money < 1000);
					} catch (InputMismatchException e) {

						System.out.println("숫자만 입력하세요");
						sc.nextLine();
						continue;
					}

					user.deposit(v1, money);
					DrinkVO[] list = user.selectDrink(v, v1, (AdminImplMoney) ad, money);
					if (list==null) {
						System.out.println("투입하신 지폐를 다시 반환합니다");
						v1.setMoney(v1.getMoney() - money / 1000);
						break;
					}

					v.drinkOut(list, money, user, v1);

				}
			}
		}

	}

	public void deposit() {
		int coin1 = v1.getCoin1();
		int coin5 = v1.getCoin5();
		ad.deposit(coin1, coin5);

	}

	public void withdraw() {
		int money;
		try {
			do {
				System.out.println("출금할 금액을 천원단위로 입력하세요 ex)4000, 100000");
				money = sc.nextInt();

			} while (money % 1000 != 0);
		} catch (InputMismatchException e) {
			System.out.println("숫자만 입력하세요");
			sc.nextLine();
			return;
		}

		ad.withdraw(money / 1000);
	}

	public void showMoney() {

		ad.showMoney(v1.getMoney());
	}

	public void showCoins() {
		ad.showCoins(v1.getCoin1(), v1.getCoin5());
	}

	public void drinkInfo() {
		String input;
		System.out.println("음료 추가:add, 음료 삭제:delete, 음료 변경 update ,음료 재고를 확인하려면 아무거나 입력하세요");
		input = sc.next();
		if (input.equals("add")) {
			ad2.addDrink();
		} else if (input.equals("delete")) {
			System.out.println("삭제할 음료 이름을 입력하세요");
			ad2.deleteDrink(sc.next());
		} else if (input.equals("update")) {
			System.out.println("수정할 음료의 이름을 입력해주세요");
			ad2.changeDrink(sc.next());

		}

		if (v.getTotalDrink() > 0) {
			for (int i = 0; i < v.getTotalDrink(); i++) {

				System.out.print("이름" + v.getDrinkList()[i].getDrinkName() + "\t");
				System.out.print("가격" + v.getDrinkList()[i].getDrinkPrice() + "\t");
				System.out.print("재고" + v.getDrinkList()[i].getCount() + "\t");
				System.out.print("팔린개수" + v.getDrinkList()[i].getSoldCount() + "\t");
				System.out.println();
			}

		} else {
			System.out.println("불러올 항목이 없습니다");
		}

	}

	public void salesInfo() {

		ad.salesInfo();

	}

}
