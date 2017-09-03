package me.jzarob.multiline;

import me.jzarob.Event;
import me.jzarob.NextEventTimeSimContext;
import me.jzarob.SimulationContext;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by jzarobsky on 9/2/17.
 */
public class MultiLineSimContext extends NextEventTimeSimContext<Double> {

    private final int lineCount;
    private static Random random = new Random();
    private double waitingTime;

    public void addWaitingTime(double startTime) {
        waitingTime = waitingTime + (getClock() - startTime);
    }

    private final ArrayList<LinkedList<Event<Double, MultiLineSimContext>>> queues = new ArrayList<>();

    public MultiLineSimContext(int lineCount) {
        this.lineCount = lineCount;
    }

    @Override
    public void initialize() {
        for(int i = 0; i < lineCount; i++) {
            queues.add(new LinkedList<>());
        }

        for(int i = 0; i < 200; i++) {
            addEvent(new CustomerArriveEvent(random.nextGaussian() * 7 + 25, random.nextGaussian() * .5 + 1.25));
        }
    }

    public LinkedList<Event<Double, MultiLineSimContext>> shortestQueue() {
        LinkedList<Event<Double, MultiLineSimContext>> shortest = queues.get(0);

        for(int i = 1; i < queues.size(); i++) {
            if(queues.get(i).size() < shortest.size()) {
                shortest = queues.get(i);
            }
        }

        return shortest;
    }

    @Override
    public void simulationFinished() {
        System.out.printf("AVG WAIT TIME = %.2f\n", waitingTime / getClock());
    }

    @Override
    public void trace() {
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < queues.size(); i++) {
            builder.append(String.format("q%d=%d,", i, queues.get(i).size()));
        }

        builder.append("\n");

        System.out.println(builder.toString());
    }
}
