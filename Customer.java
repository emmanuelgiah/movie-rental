import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Customer {
    private String _name;
    private List<Rental> _rentals = new ArrayList<>();
    
    public Customer (String name) {
        _name = name;
    }
    //alternative Customer class
    public Customer (String name, Rental rental) {
    	_name = name;
    	_rentals.add(rental);
    }
    public void addRental(Rental rental) {
        _rentals.add(rental);
    }
    //add get and set methods
    public String getName() {
        return _name;
    }
    
    public void setName(String name) {
    	_name = name;
    }
   	//add footer method
    public String addFooter(String statement, double amount, int renterPoints) {
   		statement += "Amount owed is " + String.valueOf(amount) + "\n";
   		statement += "You earned "  + String.valueOf(renterPoints) +
                  " frequent renter points";
   		return statement;
   	}
    
   	public String footerToXML(String statement, double amount, int renterPoints) {
   		statement += "Amount owed is " + "<Customer>" + String.valueOf(amount) + "</Customer>" + "\n";
   		statement += "You earned " + "<Customer>" + String.valueOf(renterPoints) + "</Customer>" +
                  " frequent renter points";
   		return statement;
   	}
   	//modified statement method
    public String printStatement() {
        double      totalAmount          = 0;
        int         frequentRenterPoints = 0;
        int         renterPointsThreshold = 0;
        List<Rental> rentals              = new ArrayList<>(_rentals);
        String      result               = "Rental Record for " + getName() + "\n";
        Iterator iterate = rentals.iterator();
        
        while (iterate.hasNext()) {
            double thisAmount = 0;
            Rental each       = (Rental) iterate.next();
            // determine amounts for each line
            thisAmount = each.computeAmount(thisAmount);
            // add frequent renter points
            frequentRenterPoints = each.checkRenterPoints(frequentRenterPoints, renterPointsThreshold);
            // show figures for this rental
            result = each.getMovie().showFigure(result, thisAmount);
            totalAmount += thisAmount;
        }
        // add footer lines
        result = addFooter(result, totalAmount, frequentRenterPoints);
        return result;
    }
    
    public String statementToXML() {
    	double      totalAmount          = 0;
        int         frequentRenterPoints = 0;
        int         renterPointsThreshold = 0;
        List<Rental> rentals              = new ArrayList<>(_rentals);
        String      result               = "Rental Record for " + getName() + "\n";
        Iterator iterate = rentals.iterator();
        
        while (iterate.hasNext()) {
            double thisAmount = 0;
            Rental each       = (Rental) iterate.next();
            // determine amounts for each line
            thisAmount = each.computeAmount(thisAmount);
            // add frequent renter points
            frequentRenterPoints = each.checkRenterPoints(frequentRenterPoints, renterPointsThreshold);
            // show figures for this rental
            result = each.getMovie().figureToXML(result, thisAmount);
            totalAmount += thisAmount;
        }
        // add footer lines
        result = footerToXML(result, totalAmount, frequentRenterPoints);
        return result;
    }
}