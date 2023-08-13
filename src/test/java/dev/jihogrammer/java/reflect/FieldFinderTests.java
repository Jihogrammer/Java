package dev.jihogrammer.java.reflect;

import dev.jihogrammer.java.util.logging.console.TestConsoleLogger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FieldFinderTests {
    static class TestClass {
        public int publicModifier;
        float defaultModifier;
        protected double protectedModifier;
        private String privateModifier;
        static TestClass staticModifier;
    }

    static class ProxyFieldFinder {
        private static final Logger log = new TestConsoleLogger(ProxyFieldFinder.class);

        private final FieldFinder delegate;

        ProxyFieldFinder(Class<?> clazz) {
            log.info("finding target: '{}'", clazz.getSimpleName());
            delegate = new FieldFinder(clazz);
        }

        boolean hasField(Class<?> type) {
            boolean hasField = delegate.hasField(type);
            log.info(">> found '{}' type field: {}", type.getSimpleName(), hasField);
            return hasField;
        }

        boolean hasField(String fieldName) {
            boolean hasField = delegate.hasField(fieldName);
            log.info(">> found '{}' named field: {}", fieldName, hasField);
            return hasField;
        }
    }

    static ProxyFieldFinder fieldFinder;

    @BeforeAll
    static void setUpClass() {
        fieldFinder = new ProxyFieldFinder(TestClass.class);
    }

    @Test
    void testFindByTypePublicField() {
        // given
        Class<Integer> integerType = int.class;
        // when
        boolean condition = fieldFinder.hasField(integerType);
        // then
        assertTrue(condition);
    }

    @Test
    void testFindByTypeDefaultField() {
        // given
        Class<Float> floatType = float.class;
        // when
        boolean condition = fieldFinder.hasField(floatType);
        // then
        assertTrue(condition);
    }

    @Test
    void testFindByTypeProtectedField() {
        // given
        Class<Double> doubleType = double.class;
        // when
        boolean condition = fieldFinder.hasField(doubleType);
        // then
        assertTrue(condition);
    }

    @Test
    void testFindByTypePrivateField() {
        // given
        Class<String> stringType = String.class;
        // when
        boolean condition = fieldFinder.hasField(stringType);
        // then
        assertTrue(condition);
    }

    @Test
    void testFindByTypeStaticField() {
        // given
        Class<TestClass> testClassType = TestClass.class;
        // when
        boolean condition = fieldFinder.hasField(testClassType);
        // then
        assertTrue(condition);
    }

    @Test
    void testFindByNamePublicField() {
        // given
        String publicModifier = "publicModifier";
        // when
        boolean condition = fieldFinder.hasField(publicModifier);
        // then
        assertTrue(condition);
    }

    @Test
    void testFindByNameDefaultField() {
        // given
        String defaultModifier = "defaultModifier";
        // when
        boolean condition = fieldFinder.hasField(defaultModifier);
        // then
        assertTrue(condition);
    }

    @Test
    void testFindByNameProtectedField() {
        // given
        String protectedModifier = "protectedModifier";
        // when
        boolean condition = fieldFinder.hasField(protectedModifier);
        // then
        assertTrue(condition);
    }

    @Test
    void testFindByNamePrivateField() {
        // given
        String privateModifier = "privateModifier";
        // when
        boolean condition = fieldFinder.hasField(privateModifier);
        // then
        assertTrue(condition);
    }

    @Test
    void testFindByNameStaticField() {
        // given
        String staticModifier = "staticModifier";
        // when
        boolean condition = fieldFinder.hasField(staticModifier);
        // then
        assertTrue(condition);
    }
}
