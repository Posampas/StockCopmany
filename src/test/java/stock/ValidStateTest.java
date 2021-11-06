package stock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidStateTest {

    private Stock stock;
    private Owner owner = new Owner();

    @Test
    public void shouldChangeStateToToSaleWhenSellClicked(){
        stock = Stock.produce(new Owner());
        stock.sell();
        Assertions.assertTrue( stock.getState() instanceof  ToSaleState);
    }



}