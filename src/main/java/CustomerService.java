import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ugur on 14.01.2016.
 */
public class CustomerService {

    private List<Customer> customerList;

    public CustomerService() {
        customerList = new ArrayList<>();
        customerList.add(new Customer(1l, "Josh"));
        customerList.add(new Customer(2l, "David"));
        customerList.add(new Customer(3l, "Peter"));
    }


    public Customer get(String name, EventBus eventBus) {

        List<Customer> customerList = this.customerList.stream().filter(c -> c.getName().equals(name)).collect(Collectors.toList());
        if (customerList.isEmpty()) {
            eventBus.send("customer", "customer not found", ar -> {
                if (ar.succeeded()) {
                    System.out.println("Received reply: " + ar.result().body());
                }
            });
            return null;
        }

        eventBus.publish("customer", "yay found customer");

        return customerList.get(0);
    }

}
