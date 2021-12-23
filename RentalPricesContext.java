public class RentalPricesContext {
    private RentalPricesStrategy _strategy;
    
    public RentalPricesContext(RentalPricesStrategy strategy) {
        _strategy = strategy;
    }

    public double executeRentalPriceStrategy(double thisAmount, int daysRented) {
        // temporarily store the initial price of the rental
        return _strategy.computeAmount(thisAmount, daysRented);
    }
}
