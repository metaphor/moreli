package com.moreli.interfaces;

import com.moreli.interfaces.facade.MessageServiceFacade;
import com.moreli.interfaces.facade.dto.MessageDTO;
import com.moreli.interfaces.facade.dto.ReversedMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class MessageResource {

    private final MessageServiceFacade messageServiceFacade;

    @Autowired
    public MessageResource(final MessageServiceFacade messageServiceFacade) {
        this.messageServiceFacade = messageServiceFacade;
    }

    @PostMapping(value = "/messages")
    public Flux<ReversedMessageDTO> reverse(@RequestBody Flux<MessageDTO> messages) {
        return messageServiceFacade.reverse(messages);
    }
}
