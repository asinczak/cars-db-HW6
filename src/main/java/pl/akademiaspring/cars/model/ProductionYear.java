package pl.akademiaspring.cars.model;

public class ProductionYear {

    private long from;
    private long to;

    public ProductionYear(long from, long to) {
        this.from = from;
        this.to = to;
    }

    public ProductionYear() {
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }
}
