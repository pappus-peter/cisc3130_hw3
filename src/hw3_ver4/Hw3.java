package hw3_ver4;
import java.io.*; 
import java.util.*;


public class Hw3 {
	public static void main(String[] args) throws IOException{
		Scanner input = new Scanner(new File("src/hw3_ver4/input.txt/"));
		PrintWriter output = new PrintWriter(new File("src/hw3_ver4/output.txt")); 
		Stock oaks = new Stock(); 
		Stock cherrys = new Stock(); 
		Stock sales = new Stock(); 
		
		output.print("Start reading receipts and orders\n"); 
		read(input, output, oaks, cherrys, sales);
		
		output.print("All receipts and orders received\n"); 
		output.print("\n\nOaks\n"); 
		oaks.print(output);
		output.print("\n\nCherrys\n"); 
		cherrys.print(output);
		output.print("\n\nOrders\n"); 
		sales.print(output);
		
		output.print("\n\nStart processing orders\n"); 
		pay(output, oaks, cherrys, sales); 
		output.close();
	}
	
	public static void read(Scanner input, PrintWriter output, Stock oaks, Stock cherrys, Stock sales) {
		
		int discount = 0; 
		while(input.hasNext()) {
			char type = input.next().charAt(0);
			String line = input.nextLine();  
			
			if(type == 'P') {
				discount = Integer.parseInt(line.trim().replaceAll("%", ""));
				output.println("The next customer will be receiving" + discount + "%" + " discount"); 
				
				
			}else if(type == 'R') {
				Shipment add = Shipment.read(line);
				if(add.getTypeWood()== 'O') {
					oaks.insertAfter(add);
				}else if(add.getTypeWood()== 'C') {
					cherrys.insertAfter(add);
				}
				output.println("Receipt record\t" + add.toString() + "is received"); 
				
				
			}else if(type == 'S') {
				Sale deduct = Sale.read(discount, line);
				sales.insertAfter(deduct);
				discount = 0; 
			}
			
		}	
	}
	public static void pay(PrintWriter output, Stock oaks, Stock cherrys, Stock sales) {
		while(!sales.isEmpty()) {
			Shipment deduct = sales.get(sales.getFirst());
			double price;
			int discount = deduct.getDiscount();
			int num = deduct.getNum(); 
			char typeDeduct = deduct.getTypeWood(); 
			
			if(typeDeduct == 'O' && !oaks.isEmpty()) {
				price = oaks.order(num, output) * (1-discount*0.01); 
				printTotal(price, discount,  output); 
				
			}else if(typeDeduct == 'C' && !cherrys.isEmpty()) {
				price = cherrys.order(num, output) * (1-discount*0.01);
				printTotal(price, discount, output); 
			}
			
			printEmpty(typeDeduct, output, oaks, cherrys); 
			sales.deleteFirst();
		}
		
	}
	public static void printTotal(double price, int discount, PrintWriter output) {
		if(discount>0) {
			output.print("\tthis order is receiving a " + discount + "% off discount\n");
		}
		output.print("\t\tTotal Sale: $" + price + "\n");
	}
	public static void printEmpty(char typeWood, PrintWriter output, Stock oaks, Stock cherrys) {
		if(typeWood == 'O' && oaks.isEmpty()) {
			output.print("stock of Oaks is empty"); 
		}else if(typeWood == 'C' && cherrys.isEmpty()) {
			output.print("stock of cherrys is empty\n");
		}
	}

}
