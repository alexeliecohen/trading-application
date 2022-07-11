package services;

import org.junit.jupiter.api.Test;
import service.FluxAndMonoServices;

import static reactor.test.StepVerifier.create;

class FluxAndMonoServicesTest {

    FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();

    @Test
    void helloWorldFlux() {
        var helloFlux = fluxAndMonoServices.helloWorldFlux();
        create(helloFlux)
                .expectNext("Hi!")
                .verifyComplete();

//        fluxAndMonoServices.helloWorldFlux()
    }

    @Test
    void helloWorldMono() {
        var helloMono = fluxAndMonoServices.helloWorldMono();
        create(helloMono)
                .expectNext("Hello Back!")
                .verifyComplete();
    }

    @Test
    void helloWorldMapFlux() {
        var helloFlux = fluxAndMonoServices.helloWorldMapFlux();

        create(helloFlux)
                .expectNext("HI!")
                .verifyComplete();

    }

    @Test
    void helloWorldFlatMapFluxAsync() {
        var helloFlux = fluxAndMonoServices.helloWorldFlatMapFluxAsync();
        create(helloFlux)
                .expectNextCount(3)
                .verifyComplete();
    }

  @Test
  void helloWorldFilter() {
        var helloFlux = fluxAndMonoServices.helloWorldFilter(4);

        create(helloFlux)
            .expectNext("Hello","World")
            .verifyComplete();
  }

    @Test
    void helloWorldFilterDoOnNext() {
        var helloFlux = fluxAndMonoServices.helloWorldFilterDoOnNext(4).log();

        create(helloFlux)
            .expectNext("Hello","World")
            .verifyComplete();
    }

    @Test
    void helloWorldOnErrorReturn() {
        var helloFlux = fluxAndMonoServices.helloWorldOnErrorReturn(4).log();

        create(helloFlux)
            .expectNext("Apple","Mango","Error!")
            .verifyComplete();

    }

    @Test
    void helloWorldOnErrorContinue() {
        var helloFlux = fluxAndMonoServices.helloWorldOnErrorReturn(4).log();

        create(helloFlux)
            .expectNext("Apple","Mango","Error!")
            .verifyComplete();

    }

  @Test
  void testHelloWorldOnErrorContinue() {
    var helloFlux = fluxAndMonoServices.helloWorldOnErrorContinue(4).log();

    create(helloFlux)
        .expectNext("APPLE","ORANGE")
        .verifyComplete();
  }

  @Test
  void helloWorldOnErrorMap() {
    var helloFlux = fluxAndMonoServices.helloWorldOnErrorMap(4).log();

    create(helloFlux)
        .expectNext("APPLE","ORANGE")
        .verifyComplete();
  }
}