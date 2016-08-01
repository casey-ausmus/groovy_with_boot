package com.caseyausmus.example

import org.junit.Test

class ExampleWebAppTest implements MetaprogrammingTrait {

    @Test
    void testMetaProgramming() {
        String testString = 'abcDEFgHi'

        assert testString.containsIgnoreCase('ABC')
        assert testString.containsIgnoreCase('def')
        assert testString.containsIgnoreCase('GhI')

        assert !testString.containsIgnoreCase('xyz')
        assert !testString.containsIgnoreCase('XYZ')
        assert !testString.containsIgnoreCase('123')
    }
}
