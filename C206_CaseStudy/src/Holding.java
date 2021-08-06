
public class Holding {
	
	private String currencyISO;
	private String currencyName;
	private double Hamount;
	
	
	public Holding(String currencyISO, String currencyName, double Hamount) {
		this.currencyISO = currencyISO;
		this.currencyName = currencyName;
		this.Hamount = Hamount;
	
	}
	
	public String getCurrencyISO() {
		return currencyISO;
	}
	
	public String getCurrencyName() {
		return currencyName;
	}
	
	public double getHamount() {
		return Hamount;
	}
	

}
//