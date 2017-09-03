package me.jzarob;

/**
 * Created by jzarobsky on 9/2/17.
 */
public abstract class Event<CType extends Comparable, ContextType> implements Comparable {

    public CType getEventTime() {
        return eventTime;
    }

    protected void setEventTime(CType eventTime) {
        this.eventTime = eventTime;
    }

    private CType eventTime;

    public abstract void handleEvent(ContextType simulationContext);

    @Override
    public int compareTo(Object o) {
        if(!(o instanceof Event)) {
            throw new RuntimeException("Attempt to compare non event to event");
        }

        Event other = (Event)o;

        return eventTime.compareTo(other.eventTime);
    }

}
