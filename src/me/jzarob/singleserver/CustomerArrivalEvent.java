package me.jzarob.singleserver;

import me.jzarob.Event;

import java.util.Random;

/**
 * Created by jzarobsky on 9/2/17.
 */
public class CustomerArrivalEvent extends Event<Double, SingleServerSimContext> {

    private static int CUSTOMER_NUMBER = 0;
    private static Random random = new Random();

    private int customerNumber = ++CUSTOMER_NUMBER;

    public CustomerArrivalEvent(Double time) {
        setEventTime(time);
    }

    @Override
    public void handleEvent(SingleServerSimContext simulationContext) {
        if(simulationContext.isBusy()) {
            simulationContext.getWaitingQueue().add(this);
        } else {
            simulationContext.setBusy(true);
            simulationContext.addWaitingTime(getEventTime());
            simulationContext.addEvent(new CustomerDepartureEvent(simulationContext.getClock() + Math.abs(random.nextGaussian() * 1.5 + 5)));
        }

        if(CUSTOMER_NUMBER < 100) {
            simulationContext.addEvent(new CustomerArrivalEvent(simulationContext.getClock() + Math.abs(random.nextGaussian() * 1.2 + 8)));
        }
    }
}
