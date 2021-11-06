package company.validators;

import company.SellRequest;

public class DoesSellerHasEnoughStock extends SellRequestValidator{



    @Override
    boolean isValid(SellRequest request) {

        long allSellerStock = company
                .getStocks()
                .stream()
                .filter(stock -> stock.getOwner().equals(request.getSeller()))
                .count();
        if (request.getAmount() <= allSellerStock){
            if (next != null ) {
                return next.isValid(request);
            }
            return true;
        }

            return false;

    }
}
