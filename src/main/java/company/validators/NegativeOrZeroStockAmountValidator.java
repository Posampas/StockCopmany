package company.validators;

import company.BuyRequest;

public class NegativeOrZeroStockAmountValidator extends BuyRequestValidator {

    @Override
    boolean isValid(BuyRequest request) {
        if (request.getAmount() <= 0 ){
            return false;
        }
        if (next != null){
            next.isValid(request);
        }
        return true;
    }
}
