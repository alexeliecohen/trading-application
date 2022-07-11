package service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;


public class FluxAndMonoServices {
  public Flux<String> helloWorldFlux() {
    return Flux.fromIterable(List.of("Hello", "World", "Test"));
//        return Flux.just("Hi!").log();
  }

  public Flux<String> helloWorldMapFlux() {
//       return Flux.fromIterable(List.of("Hello","World","Test"));
    return Flux.just("Hi!").map(String::toUpperCase).log();
  }

  public Flux<String> helloWorldFlatMapFluxAsync() {
//       return Flux.fromIterable(List.of("Hello","World","Test"));
    return Flux.just("Hi!")
        .flatMap(s -> Flux.just(s.split(""))
            .delayElements(Duration.ofMillis(new Random().nextInt(5000)))
        )
        .log();
//                .delayElements(Duration.ofMillis(new Random().nextInt(1000)))
//                .log();
  }

  public Mono<String> helloWorldMono() {
    return Mono.just("Hello Back!");
  }


  public Flux<String> helloWorldFilter(int number) {
    return Flux.fromIterable(List.of("Hello", "World", "Alex"))
        .filter(s -> s.length() > number);
  }

  public Flux<String> helloWorldFilterDoOnNext(int number) {
    return Flux.fromIterable(List.of("Hello", "World", "Alex"))
        .filter(s -> s.length() > number)
        .doOnSubscribe(subscription -> {
          System.out.printf("Subscription.toString() = %s", subscription.toString());
        })
        .doOnNext(System.out::println)
        .doOnComplete(() -> System.out.println("Stream Completed!"));
  }

  public Flux<String> helloWorldOnErrorReturn(int number) {
    return Flux.just("Apple", "Mango")
        .concatWith(Flux.error(
            new RuntimeException("Exception Occurred")
        ))
        .onErrorReturn("Error!");
  }

  public Flux<String> helloWorldOnErrorContinue(int number) {
    return Flux.just("Apple", "Mango", "Orange")
        .map(
            s -> {
              if (s.equals("Mango")) {
                throw new RuntimeException("Mango Exception!");
              }
              return s.toUpperCase();
            }
        )
        .onErrorContinue((e, f) -> {
          System.out.printf("e = %s\nf = %s", e, f.toString());
        });
  }

  public Flux<String> helloWorldOnErrorMap(int number) {
    return Flux.just("Apple", "Mango", "Orange")
        .map(
            s -> {
              if (s.equals("Mango")) {
                throw new RuntimeException("Mango Exception!");
              }
              return s.toUpperCase();
            }
        )
        .onErrorMap(throwable -> {
              System.out.printf("throwable = %s\n", throwable.toString());
              return new IllegalStateException("From OnError Map");
            }
        );
  }


  public static void main(String[] args) {
    FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();
//        fluxAndMonoServices.helloWorldFlux().subscribe(System.out::println);
//        fluxAndMonoServices.helloWorldMapFlux().subscribe(System.out::println);
//        fluxAndMonoServices.helloWorldFlatMapFluxAsync().subscribe(System.out::println);
    fluxAndMonoServices.helloWorldFilter(5).subscribe(System.out::println);
    fluxAndMonoServices.helloWorldMono().subscribe(System.out::println);
  }
}