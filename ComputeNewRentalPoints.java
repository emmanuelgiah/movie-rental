public class ComputeNewRentalPoints implements RentalPointsStrategy {
    @Override
    public int ComputePoints(int points) {
        points++;
        return points;
    }
}