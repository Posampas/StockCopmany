package company.validators;

import company.BuyRequest;

import java.time.LocalDateTime;

public class PastDateBuyRequestValidator extends BuyRequestValidator {

    @Override
    boolean isValid(BuyRequest request) {
        if (request.getExpirationDate().compareTo(LocalDateTime.now()) <= 0) {
            return false;
        }

        if (next != null){
            return next.isValid(request);
        }

        return true;
    }
}
