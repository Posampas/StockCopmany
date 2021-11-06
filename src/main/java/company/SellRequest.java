package company;

import stock.Owner;

public class SellRequest extends TradeRequest{
    private Owner Seller;
    private int amount;

    public SellRequest(Owner seller, int amount) {
        Seller = seller;
        this.amount = amount;

    }

    public Owner getSeller() {
        return Seller;
    }

    public int getAmount() {
        return amount;
    }
}
