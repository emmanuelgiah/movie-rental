public class ComputeNewMoviePrice implements RentalPricesStrategy {
    @Override
    public double computeAmount(double thisAmount, int daysRented) {
        double newMultiplier = 3;
        thisAmount += daysRented * newMultiplier;
        return thisAmount;
    }
}
