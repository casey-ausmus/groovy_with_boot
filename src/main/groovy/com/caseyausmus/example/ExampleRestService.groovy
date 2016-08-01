package com.caseyausmus.example

import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/example")
class ExampleRestService {

    @Autowired
    private ResourceLoader resourceLoader;

    @RequestMapping(value="/users", method=RequestMethod.GET)
    def getUsers(@RequestParam(value="name", required=false) String name) {
        JsonSlurper jsonSlurper = new JsonSlurper()
        def userJSON = jsonSlurper.parseText(loadJSON('users.json'))

        return name ? userJSON.users.findAll { user -> user.name.toLowerCase().contains(name.toLowerCase()) } : userJSON;
    }

    private String loadJSON(String filename) {
        return resourceLoader.getResource("classpath:${filename}").file.text
    }
}
