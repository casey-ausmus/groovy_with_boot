package com.caseyausmus.example

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/example")
class ExampleRestService {

    @Autowired
    private ResourceLoader resourceLoader;

    @RequestMapping(value="/ping", method=RequestMethod.GET)
    String ping() {
        return loadJSON('ping.json')
    }

    @RequestMapping(value="/users", method=RequestMethod.GET)
    String getUsers() {
        return loadJSON('users.json')
    }

    private String loadJSON(String filename) {
        return resourceLoader.getResource("classpath:${filename}").file.text
    }
}
