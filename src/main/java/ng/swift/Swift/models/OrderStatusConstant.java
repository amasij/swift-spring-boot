package ng.swift.Swift.models;

import java.util.*;

public enum OrderStatusConstant {
    NOT_STARTED("NOT_STARTED"),
    IN_PROGRESS("IN_PROGRESS"),
    FINISHED("FINISHED");

    private static final long serialVersionUID = -9086148061317024860L;
    private final String enumValue;
    private static Map<String, OrderStatusConstant> values = new LinkedHashMap(3, 1.0F);
    private static List<String> literals = new ArrayList(3);
    private static List<String> names = new ArrayList(3);
    private static List<OrderStatusConstant> valueList = new ArrayList(3);

    private OrderStatusConstant(String value) {
        this.enumValue = value;
    }

    public static OrderStatusConstant fromString(String name) {
        return valueOf(name);
    }

    public String value() {
        return this.enumValue;
    }

    public static OrderStatusConstant fromValue(String value) {
        OrderStatusConstant[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            OrderStatusConstant enumName = var1[var3];
            if (enumName.getValue().equals(value)) {
                return enumName;
            }
        }

        throw new IllegalArgumentException("OrderStatusConstant.fromValue(" + value + ')');
    }

    public String getValue() {
        return this.enumValue;
    }

    public static List<String> literals() {
        return literals;
    }

    public static List<String> names() {
        return names;
    }

    static {
        synchronized(values) {
            values.put(NOT_STARTED.enumValue, NOT_STARTED);
            values.put(IN_PROGRESS.enumValue, IN_PROGRESS);
            values.put(FINISHED.enumValue, FINISHED);
        }

        synchronized(valueList) {
            valueList.add(NOT_STARTED);
            valueList.add(IN_PROGRESS);
            valueList.add(FINISHED);
            valueList = Collections.unmodifiableList(valueList);
        }

        synchronized(literals) {
            literals.add(NOT_STARTED.enumValue);
            literals.add(IN_PROGRESS.enumValue);
            literals.add(FINISHED.enumValue);
            literals = Collections.unmodifiableList(literals);
        }

        synchronized(names) {
            names.add("NOT_STARTED");
            names.add("IN_PROGRESS");
            names.add("FINISHED");
            names = Collections.unmodifiableList(names);
        }
    }
}
