package com.heinzelman.pdfrepoappjava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloApplicationTest {

    @Test
    void myFirstMethod() {
    HelloApplication myApp = new HelloApplication();
        myApp.loadPropertiesFromFile();
    }


}