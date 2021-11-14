public class RentalPointsContext {
    private RentalPointsStrategy _strategy;

    public RentalPointsContext(RentalPointsStrategy strategy) {
        _strategy = strategy;
    }

    public int executeRentalPointsStrategy(int points) {
        return _strategy.ComputePoints(points);
    };
}
