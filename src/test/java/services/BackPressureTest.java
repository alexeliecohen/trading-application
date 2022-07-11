package services;

import org.intellij.lang.annotations.JdkConstants;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

public class BackPressureTest {

  @Test
  public void testBackPressure() {
    var numbers = Flux.range(1, 100).log();
    numbers.subscribe(new BaseSubscriber<Integer>() {
      @Override
      protected void hookOnSubscribe(Subscription subscription) {
        request(3);
      }

      @Override
      protected void hookOnNext(Integer value) {
        System.out.printf("value = %d\n", value);
        ;
        if (value == 3) {
          cancel();
        }
        super.hookOnNext(value);
      }

      @Override
      protected void hookOnComplete() {
        System.out.printf("Completed\n");
        super.hookOnComplete();
      }

      @Override
      protected void hookOnError(Throwable throwable) {
        super.hookOnError(throwable);
      }

      @Override
      protected void hookOnCancel() {
        super.hookOnCancel();
      }

      @Override
      protected void hookFinally(SignalType type) {
        super.hookFinally(type);
      }
    });
  }

  @Test
  public void testBackPressureOnDrop() {
    var numbers = Flux.range(1, 100).log();
    numbers
        .onBackpressureDrop(integer -> {
          System.out.printf("Dropped Values = %d\n", integer);
          ;
        })
        .subscribe(new BaseSubscriber<Integer>() {
          @Override
          protected void hookOnSubscribe(Subscription subscription) {
            request(5);
          }

          @Override
          protected void hookOnNext(Integer value) {
            System.out.printf("value = %d\n", value);
            if (value == 5) {
              hookOnCancel();
            }
            super.hookOnNext(value);
          }

          @Override
          protected void hookOnComplete() {
            System.out.printf("Completed\n");
            super.hookOnComplete();
          }

          @Override
          protected void hookOnError(Throwable throwable) {
            super.hookOnError(throwable);
          }

          @Override
          protected void hookOnCancel() {
            super.hookOnCancel();
          }

          @Override
          protected void hookFinally(SignalType type) {
            super.hookFinally(type);
          }
        });
  }

  @Test
  public void testBackPressureOnBuffer() {
    var numbers = Flux.range(1, 100).log();
    numbers
        .onBackpressureBuffer(10, (integer)-> {
          System.out.printf("Buffered = %d\n",integer);
        })
        .subscribe(new BaseSubscriber<Integer>() {
          @Override
          protected void hookOnSubscribe(Subscription subscription) {
            request(3);
          }

          @Override
          protected void hookOnNext(Integer value) {
            System.out.printf("value = %d\n", value);
            if (value == 3) {
              hookOnCancel();
            }
            super.hookOnNext(value);
          }

          @Override
          protected void hookOnComplete() {
            System.out.printf("Completed\n");
            super.hookOnComplete();
          }

          @Override
          protected void hookOnError(Throwable throwable) {
            super.hookOnError(throwable);
          }

          @Override
          protected void hookOnCancel() {
            super.hookOnCancel();
          }

          @Override
          protected void hookFinally(SignalType type) {
            super.hookFinally(type);
          }
        });
  }
  @Test
  public void testBackPressureError() {
    var numbers = Flux.range(1, 100).log();
    numbers
        .onBackpressureError()
        .subscribe(new BaseSubscriber<Integer>() {
          @Override
          protected void hookOnSubscribe(Subscription subscription) {
            request(3);
          }

          @Override
          protected void hookOnNext(Integer value) {
            System.out.printf("value = %d\n", value);
            if (value == 3) {
              hookOnCancel();
            }
          }

          @Override
          protected void hookOnComplete() {
            System.out.printf("Completed\n");
            super.hookOnComplete();
          }

          @Override
          protected void hookOnError(Throwable throwable) {
            System.out.println("throwable = " + throwable);
          }

          @Override
          protected void hookOnCancel() {
            super.hookOnCancel();
          }

          @Override
          protected void hookFinally(SignalType type) {
            super.hookFinally(type);
          }
        });
  }
}
