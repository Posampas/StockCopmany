package pl.piotr.mediatior;

public class Mediator {
    Fan fan;
    Button button;
    PowerSupply supply;
    Fan secondFan;

    public Mediator() {
        this.fan = new Fan(this);
        this.button = new Button(this);
        this.supply = new PowerSupply(this);
        secondFan = new Fan(this);
    }

    public void press() {
        if (fan.isTurnedOn() ){
            fan.turnOff();
        } else {
            fan.turnOn();
        }

        if (secondFan.isTurnedOn() ){
            secondFan.turnOff();
        } else {
            secondFan.turnOn();
        }
    }

    public void turnOnPowerSuply() {
        supply.turnOn();
    }

    public void turnOffPowerSuply() {
        supply.turnOff();
    }
}
