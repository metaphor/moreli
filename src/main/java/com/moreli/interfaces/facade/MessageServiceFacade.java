package com.moreli.interfaces.facade;

import com.moreli.interfaces.facade.dto.MessageDTO;
import com.moreli.interfaces.facade.dto.ReversedMessageDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class MessageServiceFacade {

    public Flux<ReversedMessageDTO> reverse(Flux<MessageDTO> messages) {
        return messages
                .map(MessageDTO::getContent)
                .map(StringBuffer::new)
                .map(StringBuffer::reverse)
                .map(StringBuffer::toString)
                .map(this::reversedMessageDTO);
    }

    private ReversedMessageDTO reversedMessageDTO(final String content) {
        ReversedMessageDTO reversedMessageDTO = new ReversedMessageDTO();
        reversedMessageDTO.setContent(content);
        return reversedMessageDTO;
    }
}
