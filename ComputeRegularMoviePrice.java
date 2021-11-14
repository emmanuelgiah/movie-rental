public class ComputeRegularMoviePrice implements RentalPricesStrategy {
    @Override
    public double computeAmount(double thisAmount, int daysRented) {
        double regularMultiplier = 1.5;
        thisAmount += 2;
        if (daysRented > 2) {
            thisAmount += (daysRented - 2) * regularMultiplier;
        }
        return thisAmount;
    }
}
