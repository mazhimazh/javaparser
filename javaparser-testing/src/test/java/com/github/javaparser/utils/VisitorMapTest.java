package com.github.javaparser.utils;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.ObjectIdentityEqualsVisitor;
import com.github.javaparser.ast.visitor.ObjectIdentityHashCodeVisitor;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class VisitorMapTest {
    @Test
    public void normalEqualsDoesDeepCompare() {
        CompilationUnit x1 = JavaParser.parse("class X{}");
        CompilationUnit x2 = JavaParser.parse("class X{}");

        Map<CompilationUnit, Integer> normalMap = new HashMap<>();
        normalMap.put(x1, 1);
        normalMap.put(x2, 2);
        assertEquals(1, normalMap.size());
    }

    @Test
    public void objectIdentityEqualsDoesShallowCompare() {
        CompilationUnit x1 = JavaParser.parse("class X{}");
        CompilationUnit x2 = JavaParser.parse("class X{}");

        Map<CompilationUnit, Integer> normalMap = new VisitorMap<>(new HashMap<>(), new ObjectIdentityHashCodeVisitor(), new ObjectIdentityEqualsVisitor());
        normalMap.put(x1, 1);
        normalMap.put(x2, 2);
        assertEquals(2, normalMap.size());
    }
}