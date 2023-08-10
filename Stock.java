package hw3_ver2;

import java.io.PrintWriter;

public class Stock {
	private static final int SIZE = 50; 
	Shipment[] stock; 
	int total = 0; 
	int avail = 0; 
	int last = -1;
	double markup = 1.4; 
	
	public Stock() {
		stock = new Shipment[SIZE];
		createList(); 
	}
	public void createList() {
		// circular linked list
		// all element has its next as the next element
		for(int i=0; i<stock.length; i++) {
			stock[i] = new Shipment(); 
			stock[i].next = i+1;
		}
		stock[stock.length-1].next = 0; 
	}
	
	public int getNode() {
		if(avail == -1) {
			System.out.print("array is full");
			return -1; 
		}
		int pos = avail;
		avail = stock[avail].next; 
		return pos; 
	}
	public void freeNode(int pos) {
		stock[last].next = stock[pos].next;
		stock[pos].next = avail; 
		avail = pos; 
		
		// if the last and only note is freed
		if(last == stock[last].getNext()) {
			last = -1; 
		}
	}
	public void insertAfter(Shipment add) {
		int pos = getNode();
		if(last == -1) {
			last = pos;
		}
		
		stock[pos] = add; 
		stock[pos].next = stock[last].next; 
		stock[last].next = pos; 
		last = pos;
		total += stock[pos].getNum();
	}
	public void deleteFirst() {
		freeNode(stock[last].next);
	}
	public void freeEmptyFirst() {
		if(stock[this.getFirst()].getNum()==0) {
			freeNode(stock[last].next);
		}
	}
	public Boolean isEmpty() {
		if(last == -1) {
			System.out.println("stock of "+ stock[avail].getTypeWoodStr() + " is empty"); 
//			System.out.println("stock is empty"); 
		}
		return last == -1; 
	}

	public int numFixed (int num, PrintWriter output) {
		int numFixed; 
		if(num > total) {
			numFixed = total; 
		}else {
			numFixed = num; 			
		}
		output.print(numFixed + " " + stock[this.getFirst()].getTypeWoodStr() + "s sold");
		total -= num; 
		return numFixed; 
	}
	public double order(int num, PrintWriter output) {
		num = numFixed(num, output); 
		double sum = 0;
		
		while(num>0 && !this.isEmpty()) {
			int removed = stock[this.getFirst()].remove(num);
			double price = stock[this.getFirst()].getPrice()*removed;
			sum += price; 
			num -= removed;
			output.print("\n\t\t" + removed + " at " + stock[this.getFirst()].getPrice()+ " each Sales: $" +  price + "\n"); 
			freeEmptyFirst();
		}
		return sum; 
	}
	
	public Shipment get(int pos) {
		return stock[pos];
	}
	public int getFirst() {
		return stock[last].next;
	}
	public int getLast() {
		return last; 
	}
	public int getAvail() {
		return this.avail; 
	}
	public int getNumTotal() {
		return this.total;
	}
	
	public void printArr(PrintWriter output) {
		for(int i=0; i<stock.length; i++) {
			if(stock[i] != null) {
				output.print("position: " + i + " " + stock[i].toString() + "\n");
			}
		}
	}
	public void printWithNext(PrintWriter output) {
		int i = this.getFirst(); 
		do {				
			output.print("Next: " + stock[i].next);
			output.print("\tposition: " + i + " " + stock[i].toString() + "\n");
			i = stock[i].next; 
		} while(i != this.getFirst());
		output.print("Total: " + this.getNumTotal() + "\n"); 
	}
	public void print(PrintWriter output) {
		int i = this.getFirst(); 
		do {				
			output.print("Next: " + stock[i].next);
			output.print("\tposition: " + i + " " + stock[i].toString() + "\n");
			i = stock[i].next; 
		} while(i != this.getFirst());
		output.print("Total: " + this.getNumTotal() + "\n"); 
	}
}
