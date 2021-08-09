import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class C206_CaseStudyTest {

	private Currency currency1;
	private Currency currency2;

	private ArrayList<Currency> currencyList;
	private ArrayList<Holding> HoldingList;

	@Before
	public void setUp() throws Exception {
		// prepare test data
		currency1 = new Currency("EUR", "Euro", 1.58, 1.62);
		currency2 = new Currency("GBP", "British Pound", 1.86, 1.90);

		currencyList = new ArrayList<Currency>();
	}

	@After
	public void tearDown() throws Exception {

		currency1 = null;
		currency2 = null;
		currencyList = null;

	}

	@Test
	public void c206_test() {
		// fail("Not yet implemented");
		assertTrue("C206_CaseStudy_SampleTest ", true);
	}

	@Test
	public void retrieveAllCurrencyTest() {
		// Test if Currency list is not null -boundary
		assertNotNull("Test if there is valid Currency arraylist to retrieve item", currencyList);

		// test if the list of currency retrieved from the CaseStudy is empty - boundary
		String allCurrency = C206_CaseStudy.retrieveAllCurrency(currencyList);
		String testOutput = "";
		assertEquals("Check that ViewAllCamcorderlist", testOutput, allCurrency);

		// Given an empty list, after adding 2 currencies, test if the size of the list
		// is 2 - normal
		C206_CaseStudy.addCurrency(currencyList, currency1);
		C206_CaseStudy.addCurrency(currencyList, currency2);
		assertEquals("Test that Currency arraylist size is 2", 2, currencyList.size());

		// test if the expected output string same as the list of currency retrieved
		// from the CaseStudy
		allCurrency = C206_CaseStudy.retrieveAllCurrency(currencyList);
		testOutput = String.format("%-15s %-30s %-10.2f %-10.2f\n", "EUR", "Euro", 1.58, 1.62);
		testOutput += String.format("%-15s %-30s %-10.2f %-10.2f\n", "GBP", "British Pound", 1.86, 1.90);

		assertEquals("Test that ViewAllCurrencylist", testOutput, allCurrency);

	}

	@Test
	public void addCurrencyTest() {
		// Currency list is not null, so that can add a new currency - boundary
		assertNotNull("Check if there is valid Currency arraylist to add to", currencyList);

		// Given an empty list, after adding 1 currency, the size of the list is 1 -
		// normal
		// The currency just added is as same as the first currency of the list
		C206_CaseStudy.addCurrency(currencyList, currency1);
		assertEquals("Check that Currency arraylist size is 1", 1, currencyList.size());
		assertSame("Check that Currency is added", currency1, currencyList.get(0));

		// Add another currency. test The size of the list is 2? -normal
		// The currency just added is as same as the second currency of the list
		C206_CaseStudy.addCurrency(currencyList, currency2);
		assertEquals("Check that Currency arraylist size is 2", 2, currencyList.size());
		assertSame("Check that Currency is added", currency2, currencyList.get(1));
	}
//
	@Test
	public void deleteCurrencyTest() {
		// Currency list is not null - boundary
		assertNotNull("Check if there is valid Currency arraylist to add to", currencyList);

		// Given an empty list, after adding 2 currencies, test if the size of the list
		// is 2 - normal
		C206_CaseStudy.addCurrency(currencyList, currency1);
		C206_CaseStudy.addCurrency(currencyList, currency2);
		assertEquals("Test that Currency arraylist size is 2", 2, currencyList.size());

		// Test that the second currency is deleted, the size of the list is 1
		C206_CaseStudy.deleteCurrency(currencyList, "GBP");
		assertEquals("Check that Currency arraylist size is 1", 1, currencyList.size());
		
	}

	@Test
	public void updateCurrencyTest() {
		// Currency list is not null - boundary
		assertNotNull("Check if there is valid Currency arraylist to add to", currencyList);

		
		// Given an empty list, after adding 2 currencies, test if the size of the list
		// is 2 - normal
		C206_CaseStudy.addCurrency(currencyList, currency1);
		C206_CaseStudy.addCurrency(currencyList, currency2);
		
		
		
		// Test that the list is updated with new buy rate and sell rate
		C206_CaseStudy.updateBuySellRate(currencyList, "EUR", "Euro", 1.20, 3.20);
		String allCurrency = C206_CaseStudy.retrieveAllCurrency(currencyList);
		String testOutput = String.format("%-15s %-30s %-10.2f %-10.2f\n", "EUR", "Euro", 1.20, 3.20);
		testOutput += String.format("%-15s %-30s %-10.2f %-10.2f\n", "GBP", "British Pound", 1.86, 1.90);
		
		assertEquals("Test that ViewAllCurrencylist", testOutput, allCurrency);
		
	}	
	
	
	
	@Test
	public void searchCurrencyTest() {
		// Currency list is not null
		assertNotNull("Check if there is valid Currency arraylist to search from", currencyList);

		// The currency searched is the same as the first currency
		// normal
		C206_CaseStudy.searchCurrency(currencyList, "EUR");
		assertSame("Check that Currency is searched", "EUR", currency1);

		// The currency searched is the same as the first currency
		// boundary
		C206_CaseStudy.searchCurrency(currencyList, "EUR");
		assertNotSame("Check that Currency is searched", "GBP", currency2);
	

		// The currency searched is the same as the first currency
		// boundary
		C206_CaseStudy.searchCurrency(currencyList, "EUR");
		assertNotSame("Check that Currency is searched", "GBP", currency2);
	}

	@Test
	public void BuyCurrencyTest() {
		assertNotNull("Check if there is valid Currenncy arrayList to buy from", currencyList);
		
		C206_CaseStudy.BuyCurrency(currencyList,HoldingList,"EUR");
		assertSame("Check that Currency is searched", "EUR", currency1);
		
		C206_CaseStudy.BuyCurrency(currencyList,HoldingList,"EUR");
		assertNotSame("Check that Currency is searched", "GBP", currency2);
		
	}
	
	@Test
	public void SellCurrencyTest() {
		assertNotNull("Check if there is valid Currenncy arrayList to sell ", currencyList);
		
		C206_CaseStudy.SellCurrency(currencyList,HoldingList,"EUR");
		assertSame("Check that Currency is searched", "EUR", currency1);
		
		C206_CaseStudy.SellCurrency(currencyList,HoldingList,"EUR");
		assertNotSame("Check that Currency is searched", "GBP", currency2);
		
		
		
		
	}
	



}
