package com.caseyausmus.example

import org.apache.commons.lang3.StringUtils
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ExampleWebApp {

    static void main(String[] args) {
        initMetaProgramming()
        SpringApplication.run(ExampleWebApp.class, args)
    }

    /**
     * In this method, I'm using the Groovy metaClass to add a "containsIgnoreCase" to the String class.
     * From the point this is called and onward, I'll be able to call this method on String as if it was part
     * of the JDK.
     *
     * This is mainly here as a chance to play with a cool Groovy feature.
     */
    private static void initMetaProgramming() {
        String.metaClass.containsIgnoreCase { other -> StringUtils.containsIgnoreCase(delegate, other) }
    }
}
