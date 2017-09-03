package me.jzarob.multiline;

import me.jzarob.Event;

import java.util.LinkedList;

/**
 * Created by jzarobsky on 9/2/17.
 */
public class CustomerArriveEvent extends Event<Double, MultiLineSimContext> {

    private final Double duration;
    private static int CUSTOMER_NUMBER = 0;
    private LinkedList<Event<Double, MultiLineSimContext>> queue;

    private int customerNumber = ++CUSTOMER_NUMBER;

    public CustomerArriveEvent(Double time, Double duration) {
        setEventTime(time);
        this.duration = duration;
    }

    @Override
    public void handleEvent(final MultiLineSimContext simulationContext) {

        if(queue == null) {
            queue = simulationContext.shortestQueue();
            queue.add(this);
        }

        if(this.equals(queue.peek())) {
            System.out.printf("NOW SERVING %d\n", customerNumber);
            simulationContext.addWaitingTime(getEventTime());
            simulationContext.addEvent(new CustomerFinishEvent(simulationContext.getClock() + duration, queue));
        }
    }
}
