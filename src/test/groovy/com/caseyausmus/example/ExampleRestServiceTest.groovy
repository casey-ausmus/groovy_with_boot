package com.caseyausmus.example

import groovy.json.JsonSlurper
import groovy.mock.interceptor.StubFor
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.core.io.ResourceLoader
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner)
@SpringApplicationConfiguration(classes = ExampleWebApp)
class ExampleRestServiceTest implements MetaprogrammingTrait {

    @Autowired
    ExampleRestService exampleRestService

    @Autowired
    ResourceLoader resourceLoader

    def stub

    @Before
    void initStub() {
        stub = getJsonSlurperStub()
    }

    @Test
    void testGetUsers() {
        stub.use {
            def userJson = exampleRestService.getUsers(null);
            assert userJson*.name == ['Cookie Monster', 'Rick Sanchez', 'Morty Smith']
        }
    }

    @Test
    void testGetSingleUserWithFilter() {
        stub.use {
            def userJson = exampleRestService.getUsers('morty');
            assert userJson*.name == ['Morty Smith']
        }
    }

    @Test
    void testGetMultipleUsersWithFilter() {
        stub.use {
            def userJson = exampleRestService.getUsers('mo');
            assert userJson*.name == ['Cookie Monster', 'Morty Smith']
        }
    }

    /**
     * @return JsonSlurper stub that will always return the JSON from testUsers.json in test/resources
     */
    def getJsonSlurperStub() {

        JsonSlurper slurper = new JsonSlurper()
        def testUserJson = slurper.parse(resourceLoader.getResource("classpath:testUsers.json").file);

        // When parse is called, return the test JSON
        def stub = new StubFor(JsonSlurper)
        stub.demand.parse { testUserJson }

        return stub
    }
}
