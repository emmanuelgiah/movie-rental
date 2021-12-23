import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Customer {
    // instance variables for coupon implementation
    private static final char HALF_OFF = 'H';
    private static final char DISCOUNT = 'D';
    private static final double PRICE_THRESHOLD = 50.00;
    private String _name;
    private int _age;
    private List<Rental> _rentals = new ArrayList<>();

    // the coupon types are 'N': none, 'H': half off, 'D': discount
    private char _coupon;

    public Customer (String name, int age) {
        _name = name;
        _age = age;
        _coupon = 'N';
    }

    public Customer (String name, int age, char coupon) {
        _name = name;
        _age = age;
        _coupon = coupon;
    }

    // alternative Customer classes
    public Customer (String name, int age, Rental rental) {
    	_name = name;
        _age = age;
    	_rentals.add(rental);
        _coupon = 'N';
    }

    public Customer (String name, int age, Rental rental, char coupon) {
    	_name = name;
        _age = age;
    	_rentals.add(rental);
        _coupon = coupon;
    }

    // rental methods
    public void addRental(Rental rental) {
        _rentals.add(rental);
    }

    // add get and set methods
    public String getName() {
        return _name;
    }
    
    public void setName(String name) {
    	_name = name;
    }

    public int getAge() {
        return _age;
    }

    public void setAge(int age) {
        _age = age;
    }

   	// add footer method
    public String addFooter(String statement, double amount, int renterPoints) {
   		statement += "\nAmount owed is $" + String.valueOf(amount) + "\n";
   		statement += "You earned "  + String.valueOf(renterPoints) +
                  " frequent renter points\n";
   		return statement;
   	}
    
   	public String footerToXML(String statement, double amount, int renterPoints) {
   		statement += "\nAmount owed is $" + "<Customer>" + String.valueOf(amount) + "</Customer>" + "\n";
   		statement += "You earned " + "<Customer>" + String.valueOf(renterPoints) + "</Customer>" +
                  " frequent renter points\n";
   		return statement;
   	}

    // new frequent rental point strategy
    private int frequentPointDoubler(int age, List<Integer> movieTypes, int points) {
        RentalPointsContext bonus;
        int movieTypesThreshold = 2;
        int minAge = 18;
        int maxAge = 22;

        if (movieTypes.size() > movieTypesThreshold) {
            // double points if there are several movieTypes
            bonus = new RentalPointsContext(new ComputeDoubleRentalPoints());
			points = bonus.executeRentalPointsStrategy(points);
            System.out.println("|  You Received Double Frequent Rental Points! | ");

        } else if ((age >= minAge && age <= maxAge) && movieTypes.contains(1)) {
            // double points if customer age is between 18-22 & is renting a new movie
            bonus = new RentalPointsContext(new ComputeDoubleRentalPoints());
			points = bonus.executeRentalPointsStrategy(points);
            System.out.println("|  You Received Double Frequent Rental Points! | ");
        }

        return points;
    }

    // new coupon strategy
    private double applyCoupon(double subtotal, char couponType) {
        double total = subtotal;
        double discount = 0;

        // apply corresponding discounts
        if (couponType != 'N') {

            if (couponType == HALF_OFF) {
                double percentOff = 50;
                discount = subtotal * (percentOff/100);

            } else if (couponType == DISCOUNT && subtotal > PRICE_THRESHOLD) {
                discount = 10.0;
            }
            
            total -= discount;
            System.out.println("| Congratulations! Your Coupon Saved You $" + discount + " | ");
        }

        return total;
    }

   	// modified statement method
    public String printStatement() {
        double      totalAmount           = 0;
        int         frequentRenterPoints  = 0;
        int         renterPointsThreshold = 0;

        List<Integer> movieTypes          = new ArrayList<>();
        List<Rental> rentals              = new ArrayList<>(_rentals);
        String      result               = "-\n| Rental Record for " + getName() + " Age " + getAge() + "\n";
        Iterator iterate = rentals.iterator();
        
        while (iterate.hasNext()) {
            double thisAmount = 0;
            Rental each       = (Rental) iterate.next();
            int    currentMovieType = each.getMovie().getPriceCode();
            // compute number of unique movie types
            if (!movieTypes.contains(currentMovieType)) {
                movieTypes.add(currentMovieType);
            }
            // determine amounts for each line
            thisAmount = each.computePrice(thisAmount);
            // add frequent renter points
            frequentRenterPoints = each.checkRenterPoints(frequentRenterPoints, renterPointsThreshold);
            // show figures for this rental
            result = each.getMovie().showFigure(result, thisAmount);
            totalAmount += thisAmount;
        }

        // apply any existing coupons to the transaction
        totalAmount = applyCoupon(totalAmount, _coupon);
        // execute the movie type rental strategy
        frequentRenterPoints = frequentPointDoubler(getAge(), movieTypes, frequentRenterPoints);
        // add footer lines
        result = addFooter(result, totalAmount, frequentRenterPoints);
        return result;
    }
    
    public String statementToXML() {
    	double      totalAmount           = 0;
        int         frequentRenterPoints  = 0;
        int         renterPointsThreshold = 0;

        List<Integer> movieTypes          = new ArrayList<>();
        List<Rental> rentals              = new ArrayList<>(_rentals);
        String      result               = "-\n| Rental Record for " + _name + " Age " + _age + "\n";
        Iterator iterate = rentals.iterator();
        
        while (iterate.hasNext()) {
            double thisAmount = 0;
            Rental each       = (Rental) iterate.next();
            int    currentMovieType = each.getMovie().getPriceCode();
            // compute number of unique movie types
            if (!movieTypes.contains(currentMovieType)) {
                movieTypes.add(currentMovieType);
            }
            // determine amounts for each line
            thisAmount = each.computePrice(thisAmount);
            // add frequent renter points
            frequentRenterPoints = each.checkRenterPoints(frequentRenterPoints, renterPointsThreshold);
            // show figures for this rental
            result = each.getMovie().figureToXML(result, thisAmount);
            totalAmount += thisAmount;
        }
        // apply any existing coupons to the transaction
        totalAmount = applyCoupon(totalAmount, _coupon);
        // execute the movietype rental strategy
        frequentRenterPoints = frequentPointDoubler(_age, movieTypes, frequentRenterPoints);
        // add footer lines
        result = footerToXML(result, totalAmount, frequentRenterPoints);
        return result;
    }
}