package com.design.orm;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class utility for read/write a object through reflections.
 */

public class BeanUtils {

    /**
     * Convert a object in a csv line format
     * @param o Object a convert
     * @param persistenceClass Obect's class
     * @param <T> Object's type
     * @return a csv line format
     */
    public static <T> String beanToFormat(T o, Class<T> persistenceClass) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Field[] fields = persistenceClass.getDeclaredFields();
        StringBuffer buffer = new StringBuffer();

        int index = 0;
        for (Field field : fields) {
            field.setAccessible(Boolean.TRUE);
            try {
                String value = null;
                if (Date.class.isAssignableFrom(field.getType())) {
                    value = dateFormat.format(field.get(o));
                } else {
                    value = field.get(o).toString();
                }
                buffer.append(value);
                if (index < fields.length - 1) {
                    buffer.append(",");
                }
                index++;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return buffer.toString();
    }

    /**
     * Convert a csv line to map(key, value)
     * @param line Line to convert
     * @param persistenceClass Object's class
     * @param <T> Object's type
     * @return return a map(key, value)
     */
    public static <T> Map<String, String> makeMap(String line, Class<T> persistenceClass) {
        List<Field> fields = Arrays.asList(persistenceClass.getDeclaredFields());
        String[] values = line.split(",");
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < values.length; i++) {
            Field field = fields.get(i);
            if (field != null) {
                map.put(fields.get(i).getName(), values[i]);
            }
        }

        return map;
    }

    /**
     * Convert a map(key, value) to object's instance
      * @param properties Map
     * @param persistenceClass Object's class
     * @param <T> Object's type
     * @return A new instance for Object's type
     */
    public static <T> T makeBean(Map<String, String> properties, Class<T> persistenceClass) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        T o = null;
        try {
            o = persistenceClass.newInstance();
            for (Map.Entry<String, String> entry : properties.entrySet()) {
                Field field = persistenceClass.getDeclaredField(entry.getKey());
                field.setAccessible(Boolean.TRUE);
                if (Date.class.isAssignableFrom(field.getType())) {
                    field.set(o, dateFormat.parse(entry.getValue()));
                } else if (Enum.class.isAssignableFrom(field.getType())) {
                    field.set(o, Enum.valueOf((Class<Enum>) field.getType(), entry.getValue()));
                } else if (Long.class.isAssignableFrom(field.getType())) {
                    field.set(o, new Long(entry.getValue()));
                } else {
                    field.set(o, entry.getValue());
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return o;
    }
}
