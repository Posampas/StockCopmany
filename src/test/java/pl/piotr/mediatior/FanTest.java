package pl.piotr.mediatior;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



class FanTest {

    @Test
    void shouldTurnOnFan() {
        Mediator mediator = new Mediator();
        mediator.button.press();
        Assertions.assertTrue(mediator.fan.isTurnedOn());
        Assertions.assertTrue(mediator.secondFan.isTurnedOn());

    }

    @Test
     void shouldTurnOffFan() {
        Mediator mediator = new Mediator();
        mediator.fan.isTurnedOn = true;
        mediator.secondFan.isTurnedOn = true;
        mediator.button.press();
        Assertions.assertFalse(mediator.fan.isTurnedOn());
        Assertions.assertFalse(mediator.secondFan.isTurnedOn());
    }

}