package company;

import stock.Owner;

import java.time.LocalDateTime;
import java.util.UUID;

public class BuyRequest extends TradeRequest{
    public final UUID id;
    private final Owner buyer;
    private final int amount;
    private final LocalDateTime expirationDate;

    public BuyRequest(UUID id, Owner buyer, int amount, LocalDateTime expirationDate) {
        this.id = id;
        this.buyer = buyer;
        this.amount = amount;
        this.expirationDate = expirationDate;
    }


    public UUID getId() {
        return id;
    }

    public Owner getBuyer() {
        return buyer;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    static public class BuyRequestBuilder {
        private UUID id = UUID.randomUUID();
        private Owner buyer;
        private int amount;
        private LocalDateTime expirationDate;

        public BuyRequestBuilder setBuyer(Owner buyer) {
            this.buyer = buyer;
            return this;
        }

        public BuyRequestBuilder setAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public BuyRequestBuilder setExpirationDate(LocalDateTime expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public BuyRequest build(){
            return new BuyRequest(id,buyer,amount,expirationDate);
        }

    }


}
