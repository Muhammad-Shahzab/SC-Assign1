public class Item {
    private final String name;
    private double currentBid;

    public Item(String name, double currentBid) {
        this.name = name;
        this.currentBid = currentBid;
    }

    public String getName() {
        return name;
    }

    public double getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(double newBid) {
        currentBid = newBid;
    }
}
