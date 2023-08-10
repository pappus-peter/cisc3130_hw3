package hw3_ver2;
import java.util.Scanner;

public class Sale extends Shipment{ 
	private int discount;
	
	public Sale(char typeWood, int num, int discount) {
		super(typeWood, num, discount); 
		this.discount = discount; 	
	}
	public char getTypeWood() {
		return super.getTypeWood(); 
		
	}
	public String getTypeWoodStr() {
		if(getTypeWood() == 'O') {
			return "Oak"; 
		}else if(getTypeWood() == 'C') {
			return "Cherry"; 
		}
		return "Unknown"; 
	}
	public int getNum() {
		return super.getNum();
	}
	public int getDiscount() {
		return this.discount; 
	}
	public static Sale read(int discount, String line) {
		Scanner input = new Scanner(line); 
		char typeWood = input.next().charAt(0);
		int num = input.nextInt();
		return new Sale(typeWood, num, discount);
	}
	public String toString () {
		return "TypeWood: " + this.getTypeWoodStr() + "\tquantity: " + this.getNum() + "\tdiscount: " + discount; 
	}
	
}
//public char getTypeWood() {
//	return this.typeWood; 
//	
//}
//public String getTypeWoodStr() {
//	if(typeWood == 'O') {
//		return "Oak"; 
//	}else if(typeWood == 'C') {
//		return "Cherry"; 
//	}
//	return "Unknown"; 
//}
//public int getNum() {
//	return this.num;
//}
//public int getDiscount() {
//	return this.discount; 
//}
//public static Sale read(int discount, String line) {
//	Scanner input = new Scanner(line); 
//	char typeWood = input.next().charAt(0);
//	int num = input.nextInt();
//	return new Sale(typeWood, num, discount);
//}
//public String toString () {
//	return "TypeWood: " + this.getTypeWoodStr() + "\tquantity: " + num + "\tdiscount: " + discount; 
//}