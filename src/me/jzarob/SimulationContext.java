package me.jzarob;

import java.util.PriorityQueue;

/**
 * Created by jzarobsky on 9/2/17.
 */
public abstract class SimulationContext<CType extends Comparable<?>> {

    private PriorityQueue<Event<CType, ?>> eventList;

    public CType getClock() {
        return clock;
    }

    protected void setClock(CType clock) {
        this.clock = clock;
    }

    private CType clock;

    public PriorityQueue<Event<CType, ?>> getEventList() {
        if(eventList == null) eventList = new PriorityQueue<>();
        return eventList;
    }

    protected void setEventList(PriorityQueue<Event<CType, ?>> eventList) {
        this.eventList = eventList;
    }

    public void addEvent(Event<CType, ?> e) {
        getEventList().offer(e);
    }

    public abstract void initialize();
    public abstract void advanceClock();
    public abstract void simulationFinished();

    public void runSimulation() {
        initialize();

        while(!eventList.isEmpty()) {
            advanceClock();
            Event e = eventList.poll();
            e.handleEvent(this);
            trace();
        }

        simulationFinished();
    }

    public abstract void trace();
}
