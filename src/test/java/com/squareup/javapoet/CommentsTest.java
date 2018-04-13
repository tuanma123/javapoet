package com.squareup.javapoet;

import org.junit.Test;
import sun.reflect.annotation.AnnotationType;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class CommentsTest {
    private MethodSpec computeRange(String name, int from, int to, String op) {
        return MethodSpec.methodBuilder(name)
                .addJavadoc("Multiplication in a loop")
                .returns(int.class)
                .addComment("For loop comment")
                .addAnnotation(Override.class)
                .addStatement("int result = 0")
                .beginControlFlow("for (int i = " + from + "; i < " + to + "; i++)")
                .addStatement("result = result " + op + " i")
                .endControlFlow()
                .addStatement("return result")
                .addNamedCode("What", new HashMap<>())
                .build();
    }

    @Test
    public void generateForLoopTest() {
        CodeBlock.builder().add("I ate $L $L", 3, "tacos");
        MethodSpec test = computeRange("multiply10to20", 10, 20, "*");
        System.out.println(test);
        String expected = "/**\n" +
                " * Multiplication in a loop */\n" +
                "@java.lang.Override\n" +
                "int multiply10to20() {\n" +
                "  // For loop comment\n" +
                "  int result = 0;\n" +
                "  for (int i = 10; i < 20; i++) {\n" +
                "    result = result * i;\n" +
                "  }\n" +
                "  return result;\n" +
                "}\n";
        assertEquals(test.toString(), expected);
    }

    /**
     * Multiplication in a loop
     * @return
     */
    int multiply10to20() {
        // For loop comment
        int result = 0;
        for (int i = 10; i < 20; i++) {
            result = result * i;
        }
        return result;
    }
}
