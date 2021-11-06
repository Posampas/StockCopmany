package pl.piotr.mediatior;

public class PowerSupply {
    private final Mediator mediator;
    private boolean isTurnedOn;


    public PowerSupply(Mediator mediator) {
        this.mediator = mediator;
    }

    public void turnOn() {
        isTurnedOn = true;
    }

    public void turnOff() {
        isTurnedOn = false;
    }

    public boolean isTurnedOn() {
        return isTurnedOn;
    }
}
