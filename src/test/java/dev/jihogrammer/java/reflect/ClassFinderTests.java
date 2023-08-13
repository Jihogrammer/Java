package dev.jihogrammer.java.reflect;

import dev.jihogrammer.java.util.logging.console.TestConsoleLogger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClassFinderTests {

    static class ProxyClassFinder {
        private static final Logger log = new TestConsoleLogger(ProxyClassFinder.class);

        private final ClassFinder delegate = new ClassFinder();

        List<Class<?>> findClasses(String packageName) {
            List<Class<?>> classes = delegate.findClasses(packageName);
            log.info("findClasses('{}')", packageName);
            classes.forEach(clazz -> log.info(">> found a '{}'", clazz));
            return classes;
        }

        List<Class<?>> findAllClasses() {
            List<Class<?>> allClasses = delegate.findAllClasses();
            log.info("findAllClasses()");
            allClasses.forEach(clazz -> log.info(">> found a '{}'", clazz));
            return allClasses;
        }
    }

    static ProxyClassFinder classFinder;

    @BeforeAll
    static void setUpClass() {
        classFinder = new ProxyClassFinder();
    }

    @Test
    void testFindClasses() {
        // given
        String packageName = "dev.jihogrammer.java.reflect";
        List<Class<?>> expected = Arrays.asList(ClassFinder.class, FieldFinder.class);
        // when
        List<Class<?>> actual = classFinder.findClasses(packageName);
        // then
        expected.forEach(clazz -> assertTrue(actual.contains(clazz)));
    }

    @Test
    void testFindAllClasses() {
        // given
        List<Class<?>> expected = Arrays.asList(ClassFinderTests.class, ProxyClassFinder.class, ClassFinder.class);
        // when
        List<Class<?>> actual = classFinder.findAllClasses();
        // then
        expected.forEach(clazz -> assertTrue(actual.contains(clazz)));
    }
}