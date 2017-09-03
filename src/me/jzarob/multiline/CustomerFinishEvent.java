package me.jzarob.multiline;

import me.jzarob.Event;

import java.util.LinkedList;

/**
 * Created by jzarobsky on 9/2/17.
 */
public class CustomerFinishEvent extends Event<Double, MultiLineSimContext> {

    private final LinkedList<Event<Double, MultiLineSimContext>> queue;

    public CustomerFinishEvent(Double time, LinkedList<Event<Double, MultiLineSimContext>> queue) {
        setEventTime(time);
        this.queue = queue;
    }

    @Override
    public void handleEvent(MultiLineSimContext simulationContext) {
        this.queue.pollFirst();
        if(this.queue.peek() != null) {
            this.queue.peek().handleEvent(simulationContext);
        }
    }
}
