package me.jzarob;

import me.jzarob.multiline.MultiLineSimContext;
import me.jzarob.singleserver.SingleServerSimContext;

public class Main {

    public static void main(String[] args) {
        //SingleServerSimContext context = new SingleServerSimContext();
        MultiLineSimContext context = new MultiLineSimContext(10);

        context.runSimulation();
    }
}
