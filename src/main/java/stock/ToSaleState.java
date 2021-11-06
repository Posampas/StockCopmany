package stock;

public class ToSaleState extends StockState{

    public ToSaleState(Stock stock) {
        super(stock);
    }

    @Override
    public void sell() {
        // nothing happens
    }

    @Override
    public void buy(Owner newOwner) {
        stock.setOwner(newOwner);
        stock.changeState(new ValidState(stock));
    }

    @Override
    public void vote() {
        // change owner
    }

    @Override
    public void dismiss() {
        // if no one have bought then votes are dissmisst
    }
}
