public class ComputeKidMoviePrice implements RentalPricesStrategy{
    @Override
    public double computeAmount(double thisAmount, int daysRented) {
        double regularMultiplier = 1.5;
        thisAmount += regularMultiplier;
        if (daysRented > 3) {
            thisAmount += (daysRented - 3) * regularMultiplier;
        }
        return thisAmount;
    }
}
