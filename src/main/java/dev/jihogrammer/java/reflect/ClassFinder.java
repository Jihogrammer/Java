package dev.jihogrammer.java.reflect;

import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public final class ClassFinder {
    public List<Class<?>> findAllClasses() {
        return findClasses(".");
    }
    public List<Class<?>> findClasses(String packageName) {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String path = packageName.equals(".") ? "." : packageName.replace('.', '/');
            Enumeration<URL> resources = classLoader.getResources(path);

            List<File> dirs = new ArrayList<>();

            if (resources != null) {
                while (resources.hasMoreElements()) {
                    URL resource = resources.nextElement();
                    dirs.add(new File(resource.getFile()));
                }
            }

            ArrayList<Class<?>> classes = new ArrayList<>();
            for (File directory : dirs) {
                classes.addAll(findClasses(directory, packageName));
            }

            return classes;
        } catch (Throwable ignored) {}

        return new ArrayList<>();
    }

    private List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException, IndexOutOfBoundsException {
        List<Class<?>> classes = new ArrayList<>();

        if (directory.exists()) {
            File[] files = Objects.requireNonNull(directory.listFiles());
            for (File file : files) {
                if (file.isDirectory()) {
                    if (!file.getName().contains(".")) {
                        String subPackageName = packageName + '.' + file.getName();
                        classes.addAll(findClasses(file, subPackageName));
                    }
                } else if (file.getName().endsWith(".class")) {
                    String simpleClassName = file.getName().substring(0, file.getName().length() - 6);
                    if (packageName.startsWith(".")) {
                        String refinedPackageName = Arrays.stream(packageName.split("\\."))
                                .filter(p -> !p.isBlank())
                                .collect(Collectors.joining("."));
                        classes.add(Class.forName(refinedPackageName + '.' + simpleClassName));
                    } else {
                        classes.add(Class.forName(packageName + '.' + simpleClassName));
                    }
                }
            }
        }

        return classes;
    }
}
