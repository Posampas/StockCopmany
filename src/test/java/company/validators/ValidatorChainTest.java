package company.validators;

import company.BuyRequest;
import company.Company;
import company.SellRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import stock.Owner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorChainTest {

    @Test
    void ShouldAddValidatorsInCorrectOrder() {
        ValidatorChain<SellRequest> chain = new ValidatorChain<SellRequest>(new Company());
        List<Validator<SellRequest>> validator = new ArrayList<>();
        validator.add(new DoesSellerHasEnoughStock());
        validator.add(new IsCorrectOwnerHandler());
        validator.add(new DoesSellerHasEnoughStock());
        chain.swapChain(validator);
        assertEquals(chain.peekValidators()[0], DoesSellerHasEnoughStock.class);
        assertEquals(chain.peekValidators()[1], IsCorrectOwnerHandler.class);
        assertEquals(chain.peekValidators()[2], DoesSellerHasEnoughStock.class);
    }

    @Test
    void shouldNotValidateRequestWithBadStockAmount(){
        Company company = new Company();
        Owner owner = new Owner();
        company.addOwner(owner);

        ValidatorChain<BuyRequest> chain = new ValidatorChain<>(new Company());
        List<Validator<BuyRequest>> validators = new ArrayList<>();
        validators.add(new NegativeOrZeroStockAmountValidator());
        chain.swapChain(validators);

        BuyRequest request = new BuyRequest.BuyRequestBuilder().setBuyer(owner).setAmount(-1).build();
        Assertions.assertFalse(chain.validate(request));
    }

    @Test
    void shouldNotValidateBuyRequestWithPastExpirationDate(){
        Company company = new Company();
        Owner owner = new Owner();
        company.addOwner(owner);

        ValidatorChain<BuyRequest> chain = new ValidatorChain<>(new Company());
        List<Validator<BuyRequest>> validators = new ArrayList<>();
        validators.add(new PastDateBuyRequestValidator());
        chain.swapChain(validators);

        BuyRequest request = new BuyRequest.BuyRequestBuilder().setBuyer(owner).setExpirationDate(LocalDateTime.MIN).build();
        Assertions.assertFalse(chain.validate(request));
    }
}