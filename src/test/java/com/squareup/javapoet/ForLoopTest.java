package com.squareup.javapoet;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ForLoopTest {
    private MethodSpec computeRange(String name, int from, int to, String op) {
        return MethodSpec.methodBuilder(name)
                .returns(int.class)
                .addStatement("int result = 0")
                .beginControlFlow("for (int i = " + from + "; i < " + to + "; i++)")
                .addStatement("result = result " + op + " i")
                .endControlFlow()
                .addStatement("return result")
                .build();
    }

    @Test
    public void generateForLoopTest() {
        MethodSpec test = computeRange("multiply10to20", 10, 20, "*");
        System.out.println(test);
        String expected = "int multiply10to20() {\n" +
                "  int result = 0;\n" +
                "  for (int i = 10; i < 20; i++) {\n" +
                "    result = result * i;\n" +
                "  }\n" +
                "  return result;\n" +
                "}\n";
        assertEquals(test.toString(), expected);
    }
}
