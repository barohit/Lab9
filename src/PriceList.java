import java.util.ArrayList;
import java.util.Scanner; 
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
/* 
 1. Need to have a map of items and prices
 	a. Create the list of items using a HashMap with the key being Strings and the Value being Doubles.  
 	b. Display the menu using a for loop and clever printf statements
 2. Need to have a list of items ordered by the customer and their prices. 
 	a. need some method to take what is in the map, and put the item in one arraylist, and the price in another arraylist. 
 	b. Meaning I need to declare both. 
 3. I need to initially fill the HashMap with items. 
 4. I need to prompt the user for an item. 
 	a. I need to store that item somewhere, so I'll make a string.  
 	b. I need a scanner to take that in. 
 5. Now we need to make sure the item exists. 
 	a. Try to search the HashMap for it. 
 	b. If it exists, display it and then add it to the lists, otherwise, throw an exception and go back to the top
 6. Now we ask the user if they want to continue, put that in a  while loop itself so that we can check if they enter Y or N
  a. use catch statements with a new illegalargumentexception to go back to the top of the while loop for this. 
 7. Once we are done, we can go through and display all of the items 
 8. Finally make a method that will go through the double arraylist, add all the values to a sum, and divide by the length and 
 return that value. 
 
 9. I can add functionality for entering a letter by just comparing if the first letter matches the 
 first letter of anything in the hash map. I can use a switch statement to assign numbers 1-8 to this as well
 10. I can edit the average method to calculate the average of all the items and not just for one item
 of each
 11. I can edit the display method to display the third arraylist and show the quantity as well using similar logic. 
 
 */

public class PriceList {

	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap <String, Double> menu = new HashMap <String, Double>(); 
		ArrayList<String> orderNames = new ArrayList<String>(); 
		ArrayList<Double> orderPrices = new ArrayList<Double>(); 
		ArrayList<Integer> orderQuantity  = new ArrayList<Integer>(); 
		String order = "";
		int numItems = 0; 
		Scanner scan = new Scanner(System.in);  
		char ans = 'y'; 
		boolean continueCheck = false; 
		boolean quantityCheck = true; 
		boolean included = false; 
		boolean entryValidity = true; 
		
		menu.put("apple", 0.99);
		menu.put("banana", 0.59);
		menu.put("cauliflower", 1.59);
		menu.put("dragonfruit", 2.19);
		menu.put("Elderberry", 1.79);
		menu.put("figs", 2.09);
		menu.put("grapefruit", 1.99);
		menu.put("honeydew", 3.49);

	
		System.out.println("Welcome to Guenter's Market!\n"); 
		System.out.print("Item"); 
		System.out.printf("%21s", "Price"); 
		System.out.printf("%20s", "Quantity");
		System.out.println("=============================="); 
		displayList(menu); 
		
		
		while (ans == 'y') {
			continueCheck = false; 
			quantityCheck = true; 
			entryValidity = true; 
			System.out.println("\nWhat item would you like to order? If you would like, you can just enter the first letter or a number, numbers correspond to each item in alphabetical order, irrespective of how the list appears."); // extended challenge
			order = scan.next(); 
			
			
			
			try {
				switch (Integer.parseInt(order)) {
				case 1: 
					order = "apple"; 
					break; 
				case 2: 
					order = "banana"; 
					break; 
				case 3: 
					order = "cauliflower"; 
					break; 
				case 4: 
					order = "dragonfruit"; 
					break; 
				case 5: 
					order = "Elderberry"; 
					break; 
				case 6: 
					order = "figs"; 
					break; 
				case 7: 
					order = "grapefruit"; 
					break; 
				case 8: 
					order = "honeydew"; 
					break; 
				default: 
					break; 
				}
			} catch (NumberFormatException e){
				for (String s : menu.keySet()) {
					if (Character.toLowerCase(s.charAt(0)) == Character.toLowerCase((order.charAt(0)))) {
						order = s; 
						entryValidity = false; 
					} 
				}
				if (entryValidity == true) {
					System.out.println("Error, that was not a valid entry, please enter again"); 
					continue; 
				}
				
			} // allows user to enter a number or a letter.  
			
			
			while (quantityCheck == true)  { 
				System.out.println("How many " + order + "s would you like?"); 
				quantityCheck = false; 
				try {
					numItems = scan.nextInt();
					if (numItems <= 0) {
						InputMismatchException e = new InputMismatchException(); 
						throw e; 
					}
				} catch (InputMismatchException e) {
					System.out.print("Error, invalid quantity. ");
					quantityCheck = true; 
					scan.nextLine(); 
					continue; 
				}
				
			}
			
			try {
				NoSuchElementException e = new NoSuchElementException();  
				if (menu.get(order) == null) {
					throw e;
				} else {
					System.out.println("Adding " + numItems + " " + order + "s to cart at $" + menu.get(order));
					for (String key: orderNames) {
						if (order.equals(key)) {
							int indice = orderNames.indexOf(key);  
							orderQuantity.set(indice, orderQuantity.get(indice) + numItems); 
							included = true; 
						} 
					} 
				 if (included == false) {
					 orderNames.add(order); 
					 orderPrices.add(menu.get(order));
					 orderQuantity.add(numItems);
				 }
				}
				 
			} catch (NoSuchElementException e) {
				System.out.println("Error, this item is not on our menu. Please try again and make sure to check your spelling: "); 
				continue; 
			}
		
			included = false; 
		
			IllegalArgumentException e = new IllegalArgumentException();
			System.out.println("Would you like to order anything else? Enter (y/n)"); 
			
			while (continueCheck == false) { 
			
				ans = Character.toLowerCase(scan.next().charAt(0));
				try { 
					
					if (ans != 'y' && ans != 'n') {
						throw e; 
					} else {
						continueCheck = true; 
					}
				} catch (IllegalArgumentException f) {
					System.out.println("Error, invalid input, please enter Y for yes, and N for no");  
					continue; 
				}
			}
		} 
		System.out.println(""); 
		System.out.println("Thanks for your order!\nHere's what you got:"); 
		displayList(orderNames, orderPrices, orderQuantity); 
		System.out.println("Average price per item in order was $" + averagePrice(orderPrices, orderQuantity));  
		System.out.println("Your most expensive item was: " + orderNames.get(highestCost(orderPrices))); 
		System.out.println("Your least expensive item was: " + orderNames.get(lowestCost(orderPrices))); 
		
		scan.close(); 

	}
	
	public static double averagePrice(ArrayList<Double> lst, ArrayList<Integer> lst2) {
		double sum = 0.0; 
		double total = 0.0;
		for (Double e : lst) {
			sum += e * lst2.get(lst.indexOf(e)); 
			total += lst2.get(lst.indexOf(e)); 
		}
		return sum/(double) total; 
		
	}
	
	public static int highestCost(ArrayList<Double> lst) {
		int indice = 0; 
		Double highest = lst.get(0); 
		for (int i = 0; i < lst.size(); i++) {
			if (lst.get(i) > highest) {
				highest = lst.get(i);
				indice = i; 
			}
		}
		return indice; 
	}
	
	public static int lowestCost(ArrayList<Double> lst) {
		int indice = 0; 
		Double lowest = lst.get(0); 
		for (int i = 0; i < lst.size(); i++) {
			if (lst.get(i) < lowest) {
				lowest = lst.get(i);
				indice = i; 
			}
		}
		return indice; 
	}
	
	public static void displayList(HashMap<String, Double> h) {
		
		for (String e: h.keySet()) {
			System.out.print(e);
			System.out.printf("%" + (25 - e.length()) + "s", "$" + h.get(e)); // the complicated printf formula ensures alignment
			System.out.println(""); 
		}
	}	
	
	
	public static void displayList(ArrayList<String> a, ArrayList<Double> d, ArrayList<Integer> i) {
		for (String e: a) {
			
			System.out.print(e); 
			System.out.printf("%" + (25 - e.length()) + "s", "$" + d.get(a.indexOf(e))); 
			System.out.printf("%" + (25 - d.get(a.indexOf(e)).toString().length()) + "s", i.get(a.indexOf(e))); // the complicated printf formula ensures alignment
			System.out.println("");  
		}
	}
	
	
	 
		
	
		
		
		
}


