package hw3_ver4;
import java.util.*;

public class Shipment {
	int next; 
	private char typeWood;
	private int num; 
	private int discount; 
	private double price; 
	
	public Shipment() {
	}
	
	public Shipment(char typeWood, int num, double price) {
		this.typeWood = typeWood; 
		this.num = num; 
		this.price = price; 
	}
	public Shipment(char typeWood, int num, int discount) {
		this.typeWood = typeWood; 
		this.num = num; 
		this.discount = discount; 
	}

	public int remove(int deduct) {
		int removed; 
		if(deduct >= this.num ) {
			removed = this.num;
			this.num = 0; 
		}else {
			removed = deduct;
			this.num -= deduct; 
		}
		return removed; 
	}
	public int getNext() {
		return this.next; 
	}
	public int getNum() {
		return this.num; 
	}
	public double getPrice() {
		return this.price;
	}
	public int getDiscount() {
		return this.discount; 
	}
	public char getTypeWood() {
		return this.typeWood;
	}
	public String getTypeWoodStr() {
		if(typeWood == 'O') {
			return "Oak"; 
		}else if(typeWood == 'C') {
			return "Cherry"; 
		}
		return "Unknown"; 
	}
	public String toString() {
		return " typeWood: " + this.getTypeWoodStr() + "\t\tquantity: " + num + "\tprice: " + price; 
	}
	
	public static Shipment read(String line) {
		Scanner input = new Scanner(line); 
		char typeWood = input.next().charAt(0); 
		int num = input.nextInt(); 
		double price = Double.parseDouble(input.next().replaceAll("\\$", ""));
		return new Shipment(typeWood, num, price); 
	}
}
