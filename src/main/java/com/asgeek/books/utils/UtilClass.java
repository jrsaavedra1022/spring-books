package com.asgeek.books.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * Clase de utilidades para el manejo e instacia de objetos
 * @author Jrsaavedra
 *
 */
public class UtilClass {

    private static UtilClass utilClass = null;
    /**
     * constructor
     */
    private UtilClass() {}

    /**
     * singleton
     */
    public static UtilClass getInstance() {
        if(utilClass == null)
            utilClass = new UtilClass();

        return utilClass;
    }

    /**
     * Método para instanciar un objeto a partir de un String
     * @param className: class name
     * @return Object
     * @throws UtilException: ex
     */
    public Object getObject(String className) throws UtilException{
        try {
            Class<?> nclass = Class.forName(className);
            Constructor<?> con = nclass.getConstructor();
            return con.newInstance();
        }catch(Exception ex){
            throw new UtilException("<<<<< Error al instanciar el objeto de la clase: " + className, "99999");
        }
    }

    /**
     * Método para retornar el valor de un objeto GET
     * @param obj: obj to getter
     * @param attribute: attribute name
     * @return Object
     */
    public Object getAttributeValue(Object obj, String attribute, boolean isBoolean) {
        try {
            String methodName = "get" + attribute;

            if(isBoolean)
                methodName = "is" + attribute;

            // return field
            Method method = obj.getClass().getMethod(methodName);
            return method.invoke(obj);
        }catch(Exception ex) {
            return null;
        }
    }


    /**
     * Método para setter el valor de un objeto
     * @param obj: obj to setter info
     * @param infoObj: obj with info
     * @param attribute: atttibute name
     * @return Object
     */
    public Object setAttributeValue(Object obj, Object infoObj, String attribute) {
        try {
            String attributeName = attribute.substring(0,1).toLowerCase() + attribute.substring(1);

            // return field with info
            Field declaredField = obj.getClass().getDeclaredField(attributeName);
            declaredField.setAccessible(true);
            declaredField.set(obj, infoObj);
            return obj;
        }catch(Exception ex) {
            return obj;
        }
    }
}