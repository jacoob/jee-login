package mahdi.learning.jee.loginwebprofile.framwork;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.*;

public class MyScanner {
    public static List<Class<?>> getClasses(String packageName) throws IOException, ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace(".","/");
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();

        while (resources.hasMoreElements()){
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }

        for(File directory :dirs){
            classes.addAll(findClasses(directory,packageName));
        }

        return classes;
    }

    private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if(!directory.exists()){
            return classes;
        }

        File[] files = directory.listFiles();
        for(File file:files){
            if(file.isDirectory()){
                classes.addAll(findClasses(file,packageName+"."+file.getName()));
            }else if(file.getName().endsWith(".class")){
                String className = packageName+"."+file.getName().substring(0,file.getName().length()-6);
                classes.add(Class.forName(className));
            }
        }
        return classes;
    }

    public static boolean checkForCustomAnnotation(Class<?> clazz){
        Annotation[] annotations = clazz.getAnnotations();
        for(Annotation annotation : annotations){
            if(annotation instanceof MyController){
                ((MyController) annotation).value();
                return true;
            }
        }
        return false;
    }

    public static Map<String, Class<?>> getMapClassPath(List<Class<?>> classes){
        Map<String,Class<?>> map = new HashMap<>();
        for (Class<?> clazz :classes){
            map.put(getPath(clazz),clazz);
        }
        return map;
    }

    public static String getPath(Class<?> clazz){
        Annotation[] annotations = clazz.getAnnotations();
        for(Annotation annotation:annotations){
            if(annotation instanceof MyController)
                return ((MyController) annotation).value();
        }
        return "";
    }
}
