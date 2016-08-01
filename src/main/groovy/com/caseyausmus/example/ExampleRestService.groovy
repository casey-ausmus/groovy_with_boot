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
    private ResourceLoader resourceLoader

    @RequestMapping(value="/users", method=RequestMethod.GET)
    def getUsers(@RequestParam(value="name", required=false) String name) {
        JsonSlurper jsonSlurper = new JsonSlurper()
        def userJSON = jsonSlurper.parse(loadJSON('users.json'))

        // Note: the containsIgnoreCase method was added via metaprogramming in ExampleWebApp.groovy
        userJSON.findResult { it.name.containsIgnoreCase(name) }
        return name ? userJSON.findAll { it.name.containsIgnoreCase(name) } : userJSON
    }

    private File loadJSON(String filename) {
        return resourceLoader.getResource("classpath:${filename}").file
    }
}
