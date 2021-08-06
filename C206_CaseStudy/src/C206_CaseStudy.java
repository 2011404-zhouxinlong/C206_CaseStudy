
import java.util.ArrayList;
import java.util.Iterator;

public class C206_CaseStudy {

	private static final int OPTION_QUIT = 8; // Extract constant

	public static void main(String[] args) {

		ArrayList<Currency> currencyList = new ArrayList<Currency>();
		ArrayList<Holding> HoldingList = new ArrayList<Holding>();
// do add other arraylist if required (Siew Gek)
		



		currencyList.add(new Currency("USD", "United States Dollar", 1.34, 1.36));
		currencyList.add(new Currency("JPY", "Japanese Yen", 1.22, 1.25));
		
		HoldingList.add(new Holding("SGD", "Singapore Dollar",10000));
		
		
		
		
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

			
			else if (option == 4) {
				// Search Currency
				C206_CaseStudy.setHeader("SEARCH");
				String searchCountry = inputSearchCurrency();
				C206_CaseStudy.searchCurrency(currencyList, searchCountry);

			}

			else if (option == 5) {
				// Calculate Currency 
				C206_CaseStudy.setHeader("CALCULATE");
				C206_CaseStudy.calculateCurrency(currencyList);

			} else if (option == 6) {
				C206_CaseStudy.setHeader("BUY CURRENCY");
				C206_CaseStudy.BuyCurrency(currencyList, HoldingList);
			}else if (option == 7) {
				C206_CaseStudy.setHeader("SELL CURRENCY");
				C206_CaseStudy.SellCurrency(currencyList, HoldingList);
			}
			else if (option == OPTION_QUIT) {
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
		System.out.println("6. Buy Currency");
		System.out.println("7. Sell Currency");
		System.out.println("8. Quit");
		Helper.line(80, "-");

	}

	public static void setHeader(String header) {
		Helper.line(80, "-");
		System.out.println(header);
		Helper.line(80, "-");
	}

	// ================================= Option 1 View (CRUD - Read)
	// =================================
	public static String retrieveAllCurrency(ArrayList<Currency> currencyList) {
		String output = "";

		for (int i = 0; i < currencyList.size(); i++) {

			output += String.format("%-15s %-30s %-10.2f %-10.2f\n", currencyList.get(i).getCurrencyISO(),
					currencyList.get(i).getCurrencyName(), currencyList.get(i).getBuyRate(),
					currencyList.get(i).getSellRate());
		}
		return output;
	}
//
	public static void viewAllCurrency(ArrayList<Currency> currencyList) {
		C206_CaseStudy.setHeader("CURRENCY LIST");
		String output = String.format("%-15s %-30s %-10s %-10s\n", "CURRENCY ISO", "CURRENCY NAME", "BUY RATE",
				"SELL RATE");
		output += retrieveAllCurrency(currencyList);
		System.out.println(output);
	}

	// ================================= Option 2 Add (CRUD -
	// Create)=================================
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

	// ================================= Option 3 Delete (CRUD -
	// DELETE)=================================
	public static String inputDeleteCurrencyISO() {
		String dcurrencyISO = Helper.readString("Enter currency ISO > ");

		return dcurrencyISO;
	}

	public static void deleteCurrency(ArrayList<Currency> currencyList, String dcurrenyISO) {
		for (Iterator<Currency> it = currencyList.iterator(); it.hasNext();) {
			Currency dcurrency = it.next();
			if (dcurrency.getCurrencyISO().equalsIgnoreCase(dcurrenyISO)) {
				it.remove();
			}
		}
		System.out.println("Currency deleted");
	}

//================================= Option 4 Search BUY and SELL rate =================================

	public static String inputSearchCurrency() {
		String searchCountry = Helper.readString("Enter country ISO (e.g. USD) > ");
		return searchCountry;
	}

	public static void searchCurrency(ArrayList<Currency> currencyList, String searchCountry) {

		for (int i = 0; i < currencyList.size(); i++) {

			if (currencyList.get(i) != null && currencyList.get(i).getCurrencyISO().equalsIgnoreCase(searchCountry)) {
				double buyRate = currencyList.get(i).getBuyRate();
				double sellRate = currencyList.get(i).getSellRate();

				System.out.println("The BUY rate of this currency is " + buyRate);
				System.out.println("The SELL rate of this currency is " + sellRate);
			}
		}

	}

//================================= Option 5 Calculate =================================

	public static void calculateCurrency(ArrayList<Currency> currencyList) {
		String searchCountry = Helper.readString("Enter country ISO (e.g. USD) > ");
		char buyOrsell = Helper.readChar("Buying or Selling (b/s) > ");
		if (buyOrsell == 'b' || buyOrsell == 'B') {
			for (int i = 0; i < currencyList.size(); i++) {
				if (currencyList.get(i) != null && currencyList.get(i).getCurrencyISO().contains(searchCountry)) {
					double buyAmount = Helper.readDouble("Enter amount converting (2d.p.) > ");
					double conversion = currencyList.get(i).getBuyRate() * buyAmount;
					System.out.println(
							"The converted amount of " + searchCountry + " is $" + String.format("%.2f", conversion));
					System.out.println("1 SGD = " + currencyList.get(i).getBuyRate() + " " + searchCountry);
				} else if (!currencyList.get(i).getCurrencyISO().contains(searchCountry)) {
					System.out.println("Invalid ISO code!");
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
					System.out.println("1 SGD = " + currencyList.get(i).getSellRate() + " " + searchISO);

				} else if (!currencyList.get(i).getCurrencyISO().contains(searchISO)) {
					System.out.println("Invalid ISO code!");
				}

			}

		}

	}

	// ================================= Option 6 Buy Currency
	// =================================
	public static void BuyCurrency(ArrayList<Currency> currencyList,ArrayList<Holding>HoldingList) {
		
		String searchCountry = Helper.readString("Enter country ISO (e.g. USD) > ");
		char buy = Helper.readChar("Buying Currency? (Y/N) > ");
		if (buy == 'Y' || buy == 'y') {
			for (int i = 0; i < currencyList.size(); i++) {
				if (currencyList.get(i) != null && currencyList.get(i).getCurrencyISO().contains(searchCountry)) {
	//				x = currencyList.get(i).getCurrencyISO().contains(searchCountry);
					double buyAmount = Helper.readDouble("Enter amount buying (2d.p.) > ");
					double conversion = currencyList.get(i).getBuyRate() * buyAmount;
					System.out.println(
							"The Currency being bought of " + searchCountry + " is $" + String.format("%.2f", conversion));
					double Holding = (HoldingList.get(i).getHamount() - buyAmount);
					System.out.println("Deducted holding From Singapore dollar To purchase " + searchCountry );
					System.out.println("Total of holding left in SGD $" + Holding);
				} 
			}

		}


	}
	public static void SellCurrency(ArrayList<Currency> currencyList,ArrayList<Holding>HoldingList) {
		
		String searchCountry = Helper.readString("Enter country ISO (e.g. USD) > ");
		char sell = Helper.readChar("Selling Currency? (Y/N) > ");
		if (sell == 'Y' || sell == 'y') {
			for (int i = 0; i < currencyList.size(); i++) {
				if (currencyList.get(i) != null && currencyList.get(i).getCurrencyISO().contains(searchCountry)) {
	//				x = currencyList.get(i).getCurrencyISO().contains(searchCountry);
					double sellAmount = Helper.readDouble("Enter amount selling in SGD (2d.p.) > ");
					double conversion = currencyList.get(i).getBuyRate() * sellAmount;
					System.out.println(
							"The Currency being Sold of " + searchCountry + " is $" + String.format("%.2f", conversion));	
					System.out.println("Added to holding From " + searchCountry + " to Singapore Dollar From Selling");	
					double Holding = (HoldingList.get(i).getHamount() + conversion);
					System.out.println("Total of holding left in SGD $" + Holding);
					
				} 
			}

		}


	}

}
//