package pl.akademiaspring.cars.model;

public class ProductionYearRange {

    private int from;
    private int to;

    public ProductionYearRange(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public ProductionYearRange() {
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }
}
