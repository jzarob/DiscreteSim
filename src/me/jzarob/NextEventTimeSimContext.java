package me.jzarob;

/**
 * Created by jzarobsky on 9/2/17.
 */
public abstract class NextEventTimeSimContext<CType extends Comparable<?>> extends SimulationContext<CType> {
    @Override
    public void advanceClock() {
        if(getEventList().isEmpty()) return;
        setClock(getEventList().peek().getEventTime());
    }
}
