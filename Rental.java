public class Rental {
	// instance variables for coupon implementation
    private static final char HALF_OFF = 'H';
    private static final char DISCOUNT = 'D';
    private static final double PRICE_THRESHOLD = 50.00;

    private Movie _movie;
    private int   _daysRented;
    
	// rental constructors
    public Rental(Movie movie, int daysRented) {
        _movie      = movie;
        _daysRented = daysRented;
    }

    public Rental(String title, int priceCode, int daysRented) {
        _movie = new Movie(title, priceCode);
        _daysRented = daysRented;
    }

    // add get and set method for movie 
    public Movie getMovie() {
        return _movie;
    }
    
    public void setMovie(String title, int priceCode) {
    	_movie.setTitle(title);
    	_movie.setPriceCode(priceCode);
    }

    // add get and set method for days rented
    public int getDaysRented() {
        return _daysRented;
    }
   
    public void setDaysRented(int days) {
    	_daysRented = days;
    }
    // compute new amount
   	public double computePrice(double thisAmount) {
		RentalPricesContext price;
   		switch (_movie.getPriceCode()) {
   		    case Movie.REGULAR:
				price = new RentalPricesContext(new ComputeRegularMoviePrice());
				thisAmount = price.executeRentalPriceStrategy(thisAmount, _daysRented);
				break;
   		    case Movie.NEW_RELEASE:
				price = new RentalPricesContext(new ComputeNewMoviePrice());
				thisAmount = price.executeRentalPriceStrategy(thisAmount, _daysRented);
				break;
   		    case Movie.CHILDRENS:
				price = new RentalPricesContext(new ComputeKidMoviePrice());
				thisAmount = price.executeRentalPriceStrategy(thisAmount, _daysRented);
				break;
   		}
   		return thisAmount;
   	}

	// compute frequent renter points
	public int checkRenterPoints(int points, int threshold) {
		RentalPointsContext bonus;
		if ((_movie.getPriceCode() == Movie.NEW_RELEASE) &&
                (_daysRented > threshold)) {
			bonus = new RentalPointsContext(new ComputeNewRentalPoints());
			points = bonus.executeRentalPointsStrategy(points);
		} else {
			bonus = new RentalPointsContext(new ComputeOldRentalPoints());
			points = bonus.executeRentalPointsStrategy(points);
		}

		return points;
	}
}