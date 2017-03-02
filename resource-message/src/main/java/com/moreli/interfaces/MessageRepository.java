package com.moreli.interfaces;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class MessageRepository {

    Flux<String> messages() {
        return Flux.just(
                "message1",
                "messag2",
                "m3",
                "m4"
        );
    }
}
