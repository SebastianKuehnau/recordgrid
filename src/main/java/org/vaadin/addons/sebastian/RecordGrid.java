package org.vaadin.addons.sebastian;

import com.vaadin.flow.component.grid.Grid;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;

public class RecordGrid<T> extends Grid<T> {

    private Class<T> beanType;

    public RecordGrid(Class<T> beanType) {
        super();
        this.beanType = beanType;

        if (beanType.isRecord()) {
            addColumnsOfRecord();
        }
    }

    @Override
    public Column<T> addColumn(String propertyName) {
        if (!beanType.isRecord())
            return super.addColumn(propertyName);

        return addColumnOfRecord(propertyName);
    }

    private Column<T> addColumnOfRecord(String propertyName) {
        try {
            Method method = beanType.getMethod(propertyName);
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

    private void addColumnsOfRecord(String... propertyNames) {
        for (RecordComponent recordComponent : beanType.getRecordComponents()) {
            if (propertyNames.length == 0 || Arrays.stream(propertyNames).anyMatch(s -> s.equals(recordComponent.getName()))) {
                addColumnOfRecord(recordComponent.getName());
            }
        }
    }

    private String firstCharacterToUpperCase(String columnId) {
        return columnId.substring(0, 1).toUpperCase() + columnId.substring(1);
    }

    @Override
    public void setColumns(String... propertyNames) {
        if (!beanType.isRecord())
            super.setColumns(propertyNames);

        removeAllColumns();
        addColumnsOfRecord(propertyNames);
    }
}
