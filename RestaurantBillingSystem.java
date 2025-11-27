package SyntecxHubRestaurantBillingSystem;

import java.util.ArrayList;
import java.util.Scanner;

class FoodItem {
	String name;
	double price;

	FoodItem(String name, double price) {
		this.name = name;
		this.price = price;
	}
}

class CartItem {
	FoodItem item;
	int quantity;

	CartItem(FoodItem item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}

	double getTotal() {
		return item.price * quantity;
	}
}

public class RestaurantBillingSystem {

	static Scanner sc = new Scanner(System.in);
	static ArrayList<FoodItem> menu = new ArrayList<>();
	static ArrayList<CartItem> cart = new ArrayList<>();

	public static void main(String[] args) {

		menu.add(new FoodItem("Pizza", 150));
		menu.add(new FoodItem("Burger", 80));
		menu.add(new FoodItem("Pasta", 120));
		menu.add(new FoodItem("Sandwich", 60));

		int choice;

		do {
			printMainMenu();
			System.out.print("Enter choice: ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				viewMenu();
				break;
			case 2:
				addMenuItem();
				break;
			case 3:
				removeMenuItem();
				break;
				
			case 4:
				placeOrder();
				break;
				
			case 5:
				generateBill();
				break;
			case 0:
				System.out.println("Thank you! Visit again.");
			default:
				System.out.println("Invalid choice");
			}

		} while (choice != 0);

		sc.close();
	}


	static void printMainMenu() {
		System.out.println("\n===== RESTAURANT BILLING SYSTEM =====");
		System.out.println("1. View Menu");
		System.out.println("2. Add Menu Item");
		System.out.println("3. Remove Menu Item");
		System.out.println("4. Place Order");
		System.out.println("5. Generate Bill (with GST)");
		System.out.println("0. Exit");
	}


	static void viewMenu() {
		System.out.println("\n--------- MENU ---------");
		int i = 1;
		for (FoodItem m : menu) {
			System.out.println(i + ". " + m.name + " - ₹" + m.price);
			i++;
		}
	}

	static void addMenuItem() {
		System.out.print("Enter item name: ");
		sc.nextLine();
		String name = sc.nextLine();

		System.out.print("Enter price: ");
		double price = sc.nextDouble();

		menu.add(new FoodItem(name, price));
		System.out.println("Item added successfully!");
	}

	static void removeMenuItem() {
		viewMenu();
		System.out.print("Enter item number to remove: ");
		int itemNo = sc.nextInt();

		if (itemNo > 0 && itemNo <= menu.size()) {
			menu.remove(itemNo - 1);
			System.out.println("Item removed successfully!");
		} else {
			System.out.println("Invalid item number!");
		}
	}

	static void placeOrder() {
		viewMenu();
		System.out.print("Enter item number: ");
		int itemNo = sc.nextInt();

		System.out.print("Enter quantity: ");
		int qty = sc.nextInt();

		if (itemNo > 0 && itemNo <= menu.size()) {
			cart.add(new CartItem(menu.get(itemNo - 1), qty));
			System.out.println("Item added to cart!");
		} else {
			System.out.println("Invalid item number!");
		}
	}


	static void generateBill() {
		double subtotal = 0;

		System.out.println("\n======== ITEMIZED BILL ========");
		for (CartItem c : cart) {
			double total = c.getTotal();
			System.out.println(c.item.name + " x " + c.quantity + " = ₹" + total);
			subtotal += total;
		}

		double gst = subtotal * 0.05; 
		double finalAmount = subtotal + gst;

		System.out.println("--------------------------------");
		System.out.println("Subtotal: ₹" + subtotal);
		System.out.println("GST (5%): ₹" + gst);
		System.out.println("TOTAL AMOUNT: ₹" + finalAmount);
		System.out.println("================================");
	}
}
