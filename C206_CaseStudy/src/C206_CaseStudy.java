
import java.util.ArrayList;
import java.util.Iterator;

public class C206_CaseStudy {

	private static final int OPTION_QUIT = 5; // Extract constant

	public static void main(String[] args) {

		ArrayList<Currency> currencyList = new ArrayList<Currency>();
		// do add other arraylist if required (Siew Gek)

		currencyList.add(new Currency("USD", "United States Dollar", 1.34, 1.36));
		currencyList.add(new Currency("JPY", "Japanese Yen", 1.22, 1.25));

		int option = 0;

		while (option != OPTION_QUIT) {

			C206_CaseStudy.menu();
			option = Helper.readInt("Enter an option > ");

			if (option == 1) {
				// View all currency
				C206_CaseStudy.viewAllCurrency(currencyList);

			} else if (option == 2) {
				C206_CaseStudy.setHeader("ADD A CURRENCY");

				// Add a currency
				Currency currency = inputCurrency();
				C206_CaseStudy.addCurrency(currencyList, currency);
			}

			else if (option == 3) {
				C206_CaseStudy.setHeader("DELETE A CURRENCY");
				// Delete currency
				String dcurrencyISO = inputDeleteCurrencyISO();
				C206_CaseStudy.deleteCurrency(currencyList, dcurrencyISO);
			}

//			} else if (option == 3) {
//				// Loan item
//				ResourceCentre.setHeader("LOAN");			
//				itemTypeMenu();
//					
//				int itemType = Helper.readInt("Enter option to select item type > ");
//
//				if (itemType == 1) {
//					// Loan camcorder
//					ResourceCentre.loanCamcorder(camcorderList);
//				} else if (itemType == 2) {
//					// Loan Chromebook
//					ResourceCentre.loanChromebook(chromebookList);
//				} else {
//					System.out.println("Invalid type");
//				}

//			} else if (option == 4) {
//				// Return item
//				ResourceCentre.setHeader("RETURN");				
//				itemTypeMenu();
//					
//				int itemType = Helper.readInt("Enter option to select item type > ");
//				if (itemType == 1) {
//					// Return camcorder
//					ResourceCentre.returnCamcorder(camcorderList);
//				} else if (itemType == 2) {
//					// Return Chromebook
//					ResourceCentre.returnChromebook(chromebookList);
//				} else {
//					System.out.println("Invalid type");
//				}

			else if (option == 4) {
				// Return item
				C206_CaseStudy.setHeader("SEARCH");
				C206_CaseStudy.searchCurrency(currencyList);

			}

			else if (option == 5) {
				// Add a new item
				C206_CaseStudy.setHeader("CALCULATE");
				C206_CaseStudy.calculateCurrency(currencyList);

			} else if (option == OPTION_QUIT) {
				System.out.println("Bye!");
			} else {
				System.out.println("Invalid option");
			}

		}

	}


	public static void menu() {
		C206_CaseStudy.setHeader("MONEY EXCHANGE APP");
		System.out.println("1. View currencies");
		System.out.println("2. Add currency");
		System.out.println("3. Delete currency");
		System.out.println("4. Search BUY and SELL rate");
		System.out.println("5. Calculate Conversion");
		System.out.println("6. Quit");
		Helper.line(80, "-");

	}

	public static void setHeader(String header) {
		Helper.line(80, "-");
		System.out.println(header);
		Helper.line(80, "-");
	}

//		public static String showAvailability(boolean isAvailable) {
//			String avail;
//
//			if (isAvailable == true) {
//				avail = "Yes";
//			} else {
//				avail = "No";
//			}
//			return avail;
//		}

	// ================================= Option 1 View (CRUD - Read) =================================
	public static String retrieveAllCurrency(ArrayList<Currency> currencyList) {
		String output = "";

		for (int i = 0; i < currencyList.size(); i++) {

			output += String.format("%-15s %-30s %-10.2f %-10.2f\n", currencyList.get(i).getCurrencyISO(),
					currencyList.get(i).getCurrencyName(), currencyList.get(i).getBuyRate(),
					currencyList.get(i).getSellRate());
		}
		return output;
	}

	public static void viewAllCurrency(ArrayList<Currency> currencyList) {
		C206_CaseStudy.setHeader("CURRENCY LIST");
		String output = String.format("%-15s %-30s %-10s %-10s\n", "CURRENCY ISO", "CURRENCY NAME", "BUY RATE",
				"SELL RATE");
		output += retrieveAllCurrency(currencyList);
		System.out.println(output);
	}

	// ================================= Option 2 Add (CRUD - Create)=================================
	public static Currency inputCurrency() {
		String currencyISO = Helper.readString("Enter currency ISO > ");
		String currencyName = Helper.readString("Enter currency name > ");
		double buyRate = Helper.readDouble("Enter buy rate > ");
		double sellRate = Helper.readDouble("Enter sell rate > ");

		Currency currency = new Currency(currencyISO, currencyName, buyRate, sellRate);
		return currency;

	}

	public static void addCurrency(ArrayList<Currency> currencyList, Currency currency) {

		currencyList.add(currency);
		System.out.println("Currency added");
	}

	// ================================= Option 3 Delete (CRUD - DELETE)=================================
	public static String inputDeleteCurrencyISO() {
		String dcurrencyISO = Helper.readString("Enter currency ISO > ");
		
		return dcurrencyISO;
	}

	public static void deleteCurrency(ArrayList<Currency> currencyList, String dcurrenyISO) {
		for (Iterator<Currency> it = currencyList.iterator(); it.hasNext(); ) {
		    Currency dcurrency = it.next();
		    if (dcurrency.getCurrencyISO().equalsIgnoreCase(dcurrenyISO)) {
		        it.remove();
		    }
		}
		System.out.println("Currency deleted");
	}


//================================= Option 4 Search BUY and SELL rate =================================

	public static Currency searchCurrency(ArrayList<Currency> currencyList) {

		String searchCountry = Helper.readString("Enter country ISO (e.g. USD) > ");
		for (int i = 0; i < currencyList.size(); i++) {
			if (currencyList.get(i) != null
					&& currencyList.get(i).getCurrencyISO().toLowerCase().contains(searchCountry)) {
				System.out.println("The BUY rate of this currency is " + currencyList.get(i).getBuyRate());
				System.out.println("The SELL rate of this currency is " + currencyList.get(i).getSellRate());
			}
		}

		return null;

	}

//================================= Option 5 Calculate =================================
	public static Currency calculateCurrency(ArrayList<Currency> currencyList) {
		char buyOrsell = Helper.readChar("Buying or Selling (b/s) > ");
		if (buyOrsell == 'b' || buyOrsell == 'B') {
			String searchISO = Helper.readString("Enter country ISO (e.g. USD) > ");
			for (int i = 0; i < currencyList.size(); i++) {
				if (currencyList.get(i) != null && currencyList.get(i).getCurrencyISO().contains(searchISO)) {
					double buyAmount = Helper.readDouble("Enter amount converting (2d.p.) > ");
					double conversion = currencyList.get(i).getBuyRate() * buyAmount;
					System.out.println(
							"The converted amount of " + searchISO + " is $" + String.format("%.2f", conversion));
				}
			}

		}

		else if (buyOrsell == 's' || buyOrsell == 'S') {
			String searchISO = Helper.readString("Enter country ISO (e.g. USD) > ");
			for (int i = 0; i < currencyList.size(); i++) {
				if (currencyList.get(i) != null && currencyList.get(i).getCurrencyISO().contains(searchISO)) {
					double sellAmount = Helper.readDouble("Enter amount converting (2d.p.) > ");
					double conversion = currencyList.get(i).getSellRate() * sellAmount;
					System.out.println("The converted amount of " + searchISO.toUpperCase() + " is $"
							+ String.format("%.2f", conversion));
				}
			}

		}
		return null;

	}

//	public static void addChromebook(ArrayList<Chromebook> chromebookList, Chromebook cb) {
//
//		chromebookList.add(cb);
//		System.out.println("Chromebook added");
//	}

//		//================================= Option __ Loan (CURD- Update) =================================
//		public static boolean doLoanCamcorder(ArrayList<Camcorder> camcorderList, String tag, String dueDate) {
//			
//			boolean isLoaned = false;
//
//			for (int i = 0; i < camcorderList.size(); i++) {
//				
//				String assetTag = camcorderList.get(i).getAssetTag();   //Extract Variable 
//				
//				if (tag.equalsIgnoreCase(assetTag)				
//						&& camcorderList.get(i).getIsAvailable() == true) {
//					
//					camcorderList.get(i).setIsAvailable(false);
//					camcorderList.get(i).setDueDate(dueDate);
//					
//					isLoaned = true;
//					
//				}
//			}
//			return isLoaned;
//		}
//
//		public static void loanCamcorder(ArrayList<Camcorder> camcorderList) {
//			ResourceCentre.viewAllCamcorder(camcorderList);
//			String tag = Helper.readString("Enter asset tag > ");
//			String due = Helper.readString("Enter due date > ");
//			Boolean isLoaned =doLoanCamcorder(camcorderList, tag, due);
//			if (isLoaned == false) {
//				System.out.println("Invalid asset tag");
//			} else {
//				System.out.println("Camcorder " + tag + " loaned out");
//			}
//		}
//		
//
//		public static boolean doLoanChromebook(ArrayList<Chromebook> chromebookList, String tag, String dueDate) {
//			// write your code here
//			boolean isLoaned = false;
//
//			for (int i = 0; i < chromebookList.size(); i++) {
//				String assetTag = chromebookList.get(i).getAssetTag();
//				
//				if (tag.equalsIgnoreCase(assetTag)
//						
//						&& chromebookList.get(i).getIsAvailable() == true) {
//					
//					chromebookList.get(i).setIsAvailable(false);
//					chromebookList.get(i).setDueDate(dueDate);
//					
//					isLoaned = true;
//					
//				}
//			}
//			return isLoaned;
//		}
//
//		public static void loanChromebook(ArrayList<Chromebook> chromebookList) {
//			// write your code here
//			ResourceCentre.viewAllChromebook(chromebookList);
//			String tag = Helper.readString("Enter asset tag > ");
//			String due = Helper.readString("Enter due date > ");
//			Boolean isLoaned =doLoanChromebook(chromebookList, tag, due);
//			if (isLoaned == false) {
//				System.out.println("Invalid asset tag");
//			} else {
//				System.out.println("Chromebook " + tag + " loaned out");
//			}	
//			
//		}
//		//================================= Option __ Return (CURD- Update)=================================
//		public static boolean doReturnCamcorder(ArrayList<Camcorder> camcorderList,String tag) {
//			boolean isReturned = false;
//
//			for (int i = 0; i < camcorderList.size(); i++) {
//				String assetTag = camcorderList.get(i).getAssetTag();
//				if (tag.equalsIgnoreCase(assetTag)
//						&& camcorderList.get(i).getIsAvailable() == false) {
//					camcorderList.get(i).setIsAvailable(true);
//					camcorderList.get(i).setDueDate("");
//					isReturned = true;
//					
//				}
//			}
//			return isReturned;
//			
//		}
//
//		public static void returnCamcorder(ArrayList<Camcorder> camcorderList) {
//			ResourceCentre.viewAllCamcorder(camcorderList);
//			String tag = Helper.readString("Enter asset tag > ");
//			Boolean isReturned = doReturnCamcorder(camcorderList, tag);
//			
//			if (isReturned == false) {
//				System.out.println("Invalid asset tag");
//			} else {
//				System.out.println("Camcorder " + tag + " returned");
//			}
//		}
//		// write your doReturnChromebook code here
//		public static boolean doReturnChromebook(ArrayList<Chromebook> chromebookList,String tag) {
//			boolean isReturned = false;
//
//			for (int i = 0; i < chromebookList.size(); i++) {
//				String assetTag = chromebookList.get(i).getAssetTag();
//				if (tag.equalsIgnoreCase(assetTag)
//						&& chromebookList.get(i).getIsAvailable() == false) {
//					chromebookList.get(i).setIsAvailable(true);
//					chromebookList.get(i).setDueDate("");
//					isReturned = true;
//					
//				}
//			}
//			return isReturned;
//			
//		}
//		public static void returnChromebook(ArrayList<Chromebook> chromebookList) {
//			// write your code here
//			ResourceCentre.viewAllChromebook(chromebookList);
//			String tag = Helper.readString("Enter asset tag > ");
//			Boolean isReturned = doReturnChromebook(chromebookList, tag);
//			
//			if (isReturned == false) {
//				System.out.println("Invalid asset tag");
//			} else {
//				System.out.println("Chromebook " + tag + " returned");
//			}
//		}
}
