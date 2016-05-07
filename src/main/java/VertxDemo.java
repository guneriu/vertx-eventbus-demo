import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

/**
 * Created by ugur on 14.01.2016.
 */
public class VertxDemo {


    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        EventBus eventBus = vertx.eventBus();

        eventBus.consumer("customer").handler(message -> {
            System.out.println("I have received a message: " + message.body());
            message.reply("how interesting");
        });

        CustomerService customerService = new CustomerService();
        customerService.get("Josh", eventBus);
        customerService.get("Nathan", eventBus);

    }
}
