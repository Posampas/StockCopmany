package company;

import company.validators.Validator;
import company.validators.ValidatorChain;
import stock.Owner;
import stock.Stock;
import stock.ToSaleState;
import stock.ValidState;

import java.util.*;

public class Company {
    private final Set<Owner> owners = new HashSet<>();
    private final Set<Stock> stocks = new HashSet<>();
    private final Stack<ProposedLegalDocument> documentsToVote = new Stack<>();
    private final Set<Legislation> legislations = new HashSet<>();
    private final ValidatorChain<SellRequest> sellValidatorChain = new ValidatorChain<>(this);
    private final ValidatorChain<BuyRequest> buyRequestValidatorChain = new ValidatorChain<>(this);

    public boolean sell(SellRequest request) {
        if (!sellValidatorChain.validate(request)) {
            return false;
        }
        stocks.stream()
                .filter(stock -> stock.getOwner().equals(request.getSeller()))
                .filter(stock -> stock.getState() instanceof ValidState)
                .limit(request.getAmount())
                .forEach(Stock::sell);
        return true;
    }

    public boolean buy(BuyRequest request) {
        if (!buyRequestValidatorChain.validate(request)) {
            return false;
        }
        stocks.stream()
                .filter(stock -> !stock.getOwner().equals(request.getBuyer()))
                .filter(stock -> stock.getState() instanceof ToSaleState)
                .limit(request.getAmount())
                .forEach(stock -> stock.buy(request.getBuyer()));

        return true;
    }


    public Set<Owner> getOwners() {
        return new HashSet<>(owners);
    }

    public Set<Stock> getStocks() {
        return new HashSet<>(stocks);
    }

    public Stack<ProposedLegalDocument> getDocumentsToVote() {
        return documentsToVote;
    }

    public Set<Legislation> getLegislations() {
        return legislations;
    }

    public void addSellRequestValidatorChain(List<Validator<SellRequest>> validators) {
        sellValidatorChain.swapChain(validators);
    }


    public boolean emitStock(Owner owner) {
        if (owners.contains(owner)) {
            return stocks.add(Stock.produce(owner));
        }
        return false;
    }

    public boolean addOwner(Owner owner) {
        return owners.add(owner);
    }
}
