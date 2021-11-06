package company;

import company.validators.DoesSellerHasEnoughStock;
import company.validators.IsCorrectOwnerHandler;
import company.validators.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import stock.Owner;
import stock.ToSaleState;
import stock.ValidState;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    @Test
    void ShouldAcceptWhenFilterChainIeEmpty() {
        Company company = new Company();
        Owner owner = new Owner();
        SellRequest request = new SellRequest(owner,100);
        Assertions.assertTrue(company.sell(request));
    }

    @Test
    void shouldNotAcceptSellRequestWhenOwnerNotInCompany() {
        Company company = new Company();
        Owner owner = new Owner();
        SellRequest request = new SellRequest(owner,100);
        List<Validator<SellRequest>> validatorList = new ArrayList<>();
        validatorList.add(new IsCorrectOwnerHandler());
        company.addSellRequestValidatorChain(validatorList);
        Assertions.assertFalse(company.sell(request));
    }

    @Test
    void shouldNotAcceptSellRequestWhenOwnerWantToSellMoreStockThanShePosses() {
        Company company = new Company();
        Owner owner = new Owner();
        company.addOwner(owner);
        company.emitStock(owner);
        List<Validator<SellRequest>> validatorList = new ArrayList<>();
        validatorList.add(new IsCorrectOwnerHandler());
        validatorList.add(new DoesSellerHasEnoughStock());
        company.addSellRequestValidatorChain(validatorList);
        SellRequest sellRequest  = new SellRequest(owner, 2);
        Assertions.assertFalse(company.sell(sellRequest));

    }

    @Test
    void shouldAddOwner(){
        Company company = new Company();
        Owner owner = new Owner();
        company.addOwner(owner);
        Assertions.assertTrue(company.getOwners().contains(owner));
    }

    @Test
    void shouldChangeStocksStateToToSellWhenSellRequestCorrect(){
        Company company = new Company();
        Owner owner = new Owner();
        company.addOwner(owner);
        final int STOCK_NUMBER = 100;
        final int STOCKS_TO_SELL = 2;
        for (int i = 0; i < STOCK_NUMBER ; i++) {
            company.emitStock(owner);
        }
        List<Validator<SellRequest>> validatorList = new ArrayList<>();
        validatorList.add(new IsCorrectOwnerHandler());
        validatorList.add(new DoesSellerHasEnoughStock());
        company.addSellRequestValidatorChain(validatorList);
        SellRequest sellRequest  = new SellRequest(owner, STOCKS_TO_SELL);
        company.sell(sellRequest);
        long actualStockToSell = company.getStocks().stream()
                .filter(stock -> stock.getState() instanceof ToSaleState)
                .count();
        assertEquals(STOCKS_TO_SELL,actualStockToSell);

    }

    @Test
    void shouldChangeStocksStateToValidAndOwnerShouldBeChangedWhenBought(){
        Company company = new Company();
        Owner seller = new Owner();
        Owner buyer = new Owner();
        company.addOwner(seller);
        company.addOwner(buyer);    
        final int STOCK_NUMBER = 50;
        for (int i = 0; i < STOCK_NUMBER ; i++) {
            company.emitStock(seller);
        }
        for (int i = 0; i < STOCK_NUMBER ; i++) {
            company.emitStock(buyer);
        }
        SellRequest request = new SellRequest(seller,50);
        company.sell(request);
        BuyRequest buyRequest = new BuyRequest.BuyRequestBuilder().setBuyer(buyer).setAmount(50).build();
        company.buy(buyRequest);
        long stockPossesedBySeller = company.getStocks().stream().filter(stock -> stock.getOwner().equals(seller)).count();
        Assertions.assertEquals(0,stockPossesedBySeller);
        long stockInValidState = company.getStocks().stream().filter(stock -> stock.getState() instanceof ValidState).count();
        Assertions.assertEquals(100,stockInValidState);
    }

}