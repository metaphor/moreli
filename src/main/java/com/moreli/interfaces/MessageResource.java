package com.moreli.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.stream.Collectors;

import static com.moreli.interfaces.MessageDTO.newBuilder;

@RestController
public class MessageResource {

    private final AuthorRepository authorRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public MessageResource(final AuthorRepository authorRepository, final MessageRepository messageRepository) {
        this.authorRepository = authorRepository;
        this.messageRepository = messageRepository;
    }

    @GetMapping("/messages")
    public Flux<MessageDTO> messages() {
        return messageRepository.messages()
                .flatMap(message -> authorRepository.someone()
                        .map(someone -> newBuilder()
                                .withAuthor(someone)
                                .withMessage(message)
                                .build()
                        ).subscribeOn(Schedulers.parallel())
                )
                .log();
    }

    @GetMapping("/block-messages")
    public List<MessageDTO> messagesBlocking() {
        return messageRepository.messages()
                .flatMap(message -> authorRepository.someone()
                        .map(someone -> newBuilder()
                                .withAuthor(someone)
                                .withMessage(message)
                                .build()
                        ).subscribeOn(Schedulers.parallel())
                )
                .log()
                .toStream()
                .collect(Collectors.toList());
    }
}
