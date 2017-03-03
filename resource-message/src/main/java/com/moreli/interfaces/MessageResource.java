package com.moreli.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MessageResource {

    @GetMapping("/messages")
    public List<String> messages() {
        return Arrays.asList("a", "b", "c");
    }
}
