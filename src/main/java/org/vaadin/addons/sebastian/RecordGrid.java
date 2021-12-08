package org.vaadin.addons.sebastian;

import com.vaadin.flow.component.grid.Grid;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;
import java.util.*;

public class RecordGrid<T> extends Grid<T> {

    private Class<T> recordType;
    private Set<String> propertySet ;

    public RecordGrid(Class<T> recordType) {
        super(recordType);

        Objects.equals(recordType.isRecord(), Boolean.TRUE);

        this.recordType = recordType;

        propertySet = createGenerateHashMap(recordType);

        propertySet.stream()
                .sorted(String::compareTo)
                .forEach(s -> addColumnOfRecord(s));
    }

    private Set<String> createGenerateHashMap(Class<T> recordType) {
        Set<String> returnSet = new HashSet<>();

        for (RecordComponent recordComponent : recordType.getRecordComponents()) {
            returnSet.add(recordComponent.getName());
        }

        return returnSet;
    }

    @Override
    public Column<T> addColumn(String propertyName) {
        return addColumnOfRecord(propertyName);
    }

    private Column<T> addColumnOfRecord(String propertyName) {
        try {
            Method method = recordType.getMethod(propertyName);
            return addColumn(t -> {
                try {
                    return method.invoke(t);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

                return null;
            }).setHeader(firstCharacterToUpperCase(propertyName));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String firstCharacterToUpperCase(String columnId) {
        return columnId.substring(0, 1).toUpperCase() + columnId.substring(1);
    }

    @Override
    public void setColumns(String... propertyNames) {
        removeAllColumns();

        Arrays.stream(propertyNames).forEach(propertyName -> addColumnOfRecord(propertyName));
    }
}
