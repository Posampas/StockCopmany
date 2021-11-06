package stock;

import java.util.Objects;
import java.util.UUID;

public final class Stock {
    private final UUID id;
    private Owner owner;
    private StockState state;

    private Stock(Owner owner) {
        this.owner = owner;
        id = UUID.randomUUID();
        state = new ValidState(this);
    }

    public void sell(){
        state.sell();
    }

    public void buy(Owner newOwner){
        state.buy(newOwner);
    }

    public void vote(){
        state.vote();
    }

    public void dismiss(){
        state.dismiss();
    }


    public StockState getState() {
        return state;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void changeState(StockState state){
        this.state = state;
    }

    public Owner getOwner() {
        return owner;
    }

    public static Stock produce(Owner owner){
        return new Stock(owner);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return id.equals(stock.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
