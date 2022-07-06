package services;

import org.junit.jupiter.api.Test;

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
}