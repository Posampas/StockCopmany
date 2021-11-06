package company.validators;

import company.SellRequest;

public class IsCorrectOwnerHandler extends SellRequestValidator{


    @Override
    boolean isValid(SellRequest request) {
        if (company.getOwners().contains(request.getSeller())){
            if (next != null ) {
                return next.isValid(request);
            }
            return true;
        }
        return false;
    }

}
