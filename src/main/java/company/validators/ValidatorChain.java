package company.validators;

import company.Company;
import company.TradeRequest;

import java.util.ArrayList;
import java.util.List;

public class ValidatorChain <T extends TradeRequest> {
    private final Company company;
    private final List<Validator<T>> validators = new ArrayList<>();

    public ValidatorChain(Company company) {
        this.company = company;
    }

    public void add(Validator<T> validator){
        validators.add(validator);
    }

    public boolean validate(T request){
        if (validators.isEmpty()){
            return true;
        }
        return validators.get(0).isValid(request);
    }

    public void swapChain(List<Validator<T>> validators) {
        this.validators.clear();
        if (validators.isEmpty()){
            return;
        } else if (validators.size() == 1 ){
            Validator<T> tmp = validators.get(0);
            tmp.setCompany(company);
            this.validators.add(tmp);
            return;
        }


        Validator<T> tmp = validators.get(0);
        tmp.setCompany(company);
        this.validators.add(tmp);
        for (int i = 1; i < validators.size() ; i++) {
            Validator<T> next = validators.get(i);
            next.setCompany(company);
            tmp.setNext(next);
            tmp = next;
            this.validators.add(tmp);
        }
    }

    public Class<Validator<T>>[] peekValidators(){
        Class<Validator<T>>[] arr = new Class[validators.size()];
        int i =0;
        for (Validator<T> validator: validators) {
            arr[i++] = (Class<Validator<T>>) validator.getClass();
        }

        return arr;
    }
}
