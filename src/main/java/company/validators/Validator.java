package company.validators;

import company.Company;
import company.TradeRequest;

public abstract class Validator<T extends TradeRequest> {
    protected Validator<T> next;
    protected Company company;

    abstract boolean isValid(T request);

    public void setNext(Validator<T> next) {
        this.next = next;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
