package services;

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

    public static void main(String[] args) {
        FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();
//        fluxAndMonoServices.helloWorldFlux().subscribe(System.out::println);
//        fluxAndMonoServices.helloWorldMapFlux().subscribe(System.out::println);
        fluxAndMonoServices.helloWorldFlatMapFluxAsync().subscribe(System.out::println);
        fluxAndMonoServices.helloWorldMono().subscribe(System.out::println);
    }
}