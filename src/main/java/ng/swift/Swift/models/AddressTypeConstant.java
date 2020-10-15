package ng.swift.Swift.models;

import java.util.*;

public enum AddressTypeConstant {
    DELIVERY("DELIVERY"),
    CREATION("CREATION");

    private static final long serialVersionUID = -9086148061317024860L;
    private final String enumValue;
    private static Map<String, AddressTypeConstant> values = new LinkedHashMap(2, 1.0F);
    private static List<String> literals = new ArrayList(2);
    private static List<String> names = new ArrayList(2);
    private static List<AddressTypeConstant> valueList = new ArrayList(2);

    private AddressTypeConstant(String value) {
        this.enumValue = value;
    }

    public static AddressTypeConstant fromString(String name) {
        return valueOf(name);
    }

    public String value() {
        return this.enumValue;
    }

    public static AddressTypeConstant fromValue(String value) {
        AddressTypeConstant[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            AddressTypeConstant enumName = var1[var3];
            if (enumName.getValue().equals(value)) {
                return enumName;
            }
        }

        throw new IllegalArgumentException("AddressTypeConstant.fromValue(" + value + ')');
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
            values.put(DELIVERY.enumValue, DELIVERY);
            values.put(CREATION.enumValue, CREATION);
        }

        synchronized(valueList) {
            valueList.add(DELIVERY);
            valueList.add(CREATION);
            valueList = Collections.unmodifiableList(valueList);
        }

        synchronized(literals) {
            literals.add(DELIVERY.enumValue);
            literals.add(CREATION.enumValue);
            literals = Collections.unmodifiableList(literals);
        }

        synchronized(names) {
            names.add("DELIVERY");
            names.add("CREATION");
            names = Collections.unmodifiableList(names);
        }
    }
}
