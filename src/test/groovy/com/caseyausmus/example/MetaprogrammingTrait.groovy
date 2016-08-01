package com.caseyausmus.example

import org.junit.Before

trait MetaprogrammingTrait {

    @Before
    //Adds in metaprogramming methods that the app will use at runtime
    void initMetaProgramming() {
        ExampleWebApp.initMetaProgramming()
    }
}