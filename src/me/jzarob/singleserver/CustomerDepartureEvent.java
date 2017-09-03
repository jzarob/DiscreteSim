package me.jzarob.singleserver;

import me.jzarob.Event;

/**
 * Created by jzarobsky on 9/2/17.
 */
public class CustomerDepartureEvent extends Event<Double, SingleServerSimContext> {

    public CustomerDepartureEvent(Double time) {
        setEventTime(time);
    }

    @Override
    public void handleEvent(SingleServerSimContext simulationContext) {
        simulationContext.setBusy(false);

        if(!simulationContext.getWaitingQueue().isEmpty()) {
            simulationContext.getWaitingQueue().pollFirst().handleEvent(simulationContext);
        }
    }
}
