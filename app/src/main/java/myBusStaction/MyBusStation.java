package myBusStaction;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by Prime Tech on 7/26/2016.
 */
public class MyBusStation {
    public static final Bus bus = new Bus(ThreadEnforcer.ANY);

    public static Bus getBus(){
        return bus;
    }
}
