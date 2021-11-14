public class RentalPricesContext {
    private RentalPricesStrategy _strategy;

    public RentalPricesContext(RentalPricesStrategy strategy) {
        _strategy = strategy;
    }

    public double executeRentalPriceStrategy(double thisAmount, int daysRented) {
        return _strategy.computeAmount(thisAmount, daysRented);
    }
}
