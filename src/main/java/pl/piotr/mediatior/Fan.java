package pl.piotr.mediatior;

public class Fan {
    private final Mediator mediator;
    boolean isTurnedOn;

    public Fan(Mediator mediator) {
        this.mediator = mediator;
    }

    public void turnOn() {
        mediator.turnOnPowerSuply();
        isTurnedOn = true;
    }

    public void turnOff() {
        mediator.turnOffPowerSuply();
        isTurnedOn = false;

    }

    public boolean isTurnedOn() {
        return isTurnedOn;
    }
}
