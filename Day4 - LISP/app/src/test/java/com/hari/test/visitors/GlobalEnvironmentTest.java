package com.hari.test.visitors;

import com.hari.visitors.GlobalEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GlobalEnvironmentTest {

    GlobalEnvironment env;

    @BeforeEach
    public void setUp(){
        env = GlobalEnvironment.getInstance();
    }



    @ParameterizedTest
    @CsvSource({
            "x,42",
            "y,100",
    })
    void testDefineAndGet(String input,String expected) {
        env.addVariable(input,expected);
        assertEquals(expected, env.getVariable(input),"Mapped variable "+input+" to "+expected);
    }

    @Test
    void testMissingKey() {
        assertNull(env.getVariable("a"),"Key is missing");
    }

}
