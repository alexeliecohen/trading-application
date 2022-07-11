package services;

import org.apache.kafka.common.errors.DuplicateResourceException;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class HotAndColdStreamTest {
  @Test
  public void coldStreamTest() {
    var numbers = Flux.range(1,10);
    numbers.subscribe(integer -> {
      System.out.printf("Subscriber 1 integer %d\n",integer);
    });
    numbers.subscribe(integer -> {
      System.out.printf("Subscriber 2 integer %d\n",integer);
    });
  }

  @Test
  public void hotStreamTest() throws InterruptedException {
    var numbers = Flux.range(1,10)
        .delayElements(Duration.ofMillis(1000));
    ConnectableFlux<Integer> publisher = numbers.publish();
    publisher.connect();

    publisher.subscribe(integer -> System.out.println("Subscriber 1 = " + integer));
    Thread.sleep(4000);
    publisher.subscribe(integer -> System.out.println("Subscriber 2 = " + integer));
    Thread.sleep(10000);
  }
}
