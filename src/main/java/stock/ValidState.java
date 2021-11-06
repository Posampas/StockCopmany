package stock;

public class ValidState extends StockState{

    public ValidState(Stock stock) {
        super(stock);
    }

    @Override
    public void sell() {
        stock.changeState(new ToSaleState(stock));
    }

    @Override
    public void buy(Owner newOwner) {
        // nothing happen
    }

    @Override
    public void vote() {
        // Owner
    }

    @Override
    public void dismiss() {
        // Done by company
    }
}
