package me.jzarob.singleserver;

import me.jzarob.Event;
import me.jzarob.SimulationContext;

import java.util.LinkedList;

/**
 * Created by jzarobsky on 9/2/17.
 */
public class SingleServerSimContext extends SimulationContext<Double> {

    private LinkedList<Event<Double, SingleServerSimContext>> waitingQueue = new LinkedList<>();
    private boolean isBusy = false;

    private double waitingTime = 0;
    private double queueSize = 0;


    public double addWaitingTime(double arrivalTime) {
        return waitingTime = (waitingTime + (getClock() - arrivalTime));
    }

    public LinkedList<Event<Double, SingleServerSimContext>> getWaitingQueue() {
        return waitingQueue;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    @Override
    public void initialize() {
        addEvent(new CustomerArrivalEvent(1.0));
    }

    @Override
    public void advanceClock() {
        if(getEventList().isEmpty()) return;
        setClock(getEventList().peek().getEventTime());
    }

    @Override
    public void simulationFinished() {
        System.out.printf("Average wait time = %.2f\n", waitingTime / getClock());
    }

    @Override
    public void trace() {
        System.out.printf("TRACE [%.2f], events = %d, waitingQueue = %d\n", getClock(), getEventList().size(), waitingQueue.size());
    }
}
