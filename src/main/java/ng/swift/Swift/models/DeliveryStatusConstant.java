package ng.swift.Swift.models;

import java.util.*;

public enum DeliveryStatusConstant {
    AWAITING_PICKUP("AWAITING_PICKUP"),
    ON_ROUTE("ON_ROUTE"),
    DELIVERED("DELIVERED");

    private final String enumValue;
    private static Map<String, DeliveryStatusConstant> values = new LinkedHashMap(3, 1.0F);
    private static List<String> literals = new ArrayList(3);
    private static List<String> names = new ArrayList(3);
    private static List<DeliveryStatusConstant> valueList = new ArrayList(2);

    private DeliveryStatusConstant(String value) {
        this.enumValue = value;
    }

    public static DeliveryStatusConstant fromString(String name) {
        return valueOf(name);
    }

    public String value() {
        return this.enumValue;
    }

    public static DeliveryStatusConstant fromValue(String value) {
        DeliveryStatusConstant[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            DeliveryStatusConstant enumName = var1[var3];
            if (enumName.getValue().equals(value)) {
                return enumName;
            }
        }

        throw new IllegalArgumentException("DeliveryStatusConstant.fromValue(" + value + ')');
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
            values.put(AWAITING_PICKUP.enumValue, AWAITING_PICKUP);
            values.put(ON_ROUTE.enumValue, ON_ROUTE);
            values.put(DELIVERED.enumValue, DELIVERED);

        }

        synchronized(valueList) {
            valueList.add(AWAITING_PICKUP);
            valueList.add(ON_ROUTE);
            valueList.add(DELIVERED);
            valueList = Collections.unmodifiableList(valueList);
        }

        synchronized(literals) {
            literals.add(AWAITING_PICKUP.enumValue);
            literals.add(ON_ROUTE.enumValue);
            literals.add(DELIVERED.enumValue);
            literals = Collections.unmodifiableList(literals);
        }

        synchronized(names) {
            names.add("AWAITING_PICKUP");
            names.add("ON_ROUTE");
            names.add("DELIVERED");
            names = Collections.unmodifiableList(names);
        }
    }
}
