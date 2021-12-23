public class Movie {

    public static final int CHILDRENS   = 2;
    public static final int REGULAR     = 0;
    public static final int NEW_RELEASE = 1;
    
    private String _title;
    private int    _priceCode;
    
    public Movie(String title, int priceCode) {
        _title = title;
        _priceCode = priceCode;
    }

    // add get and set method for price code
    public int getPriceCode() {
        return _priceCode;
    }
    
    public void setPriceCode(int priceCode) {
        _priceCode = priceCode;
    }

    // add get set method for title
    public String getTitle() {
        return _title;
    }
    
    public void setTitle(String title) {
    	_title = title;
    }

    // Move the show figures method
   	public String showFigure(String statement, double amount) { 
   		statement += "\t" + _title +
                "\t" + String.valueOf(amount) + "\n";
   		
   		return statement;
   	}
   	
   	public String figureToXML(String statement, double amount) {
   		statement += "\t" + "<Rental><Movie>" + _title + "</Movie>" + 
                "\t" + "<Customer>" + String.valueOf(amount) + "</Customer></Rental>" + "\n";
   		
   		return statement;
   	}
}