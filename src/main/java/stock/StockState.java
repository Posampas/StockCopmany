package stock;

public abstract class StockState {
    protected Stock stock;

    public StockState(Stock stock) {
        this.stock = stock;
    }

    public abstract void sell();

    public abstract void buy(Owner owner);

    public abstract void vote();

    public abstract void dismiss();

}
