public class ComputeDoubleRentalPoints implements RentalPointsStrategy{
    @Override
    public int ComputePoints(int points) {
        points *= 2;
        return points;
    }
}
