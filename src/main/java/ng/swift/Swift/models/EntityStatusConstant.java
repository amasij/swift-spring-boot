package ng.swift.Swift.models;

import java.util.*;

public enum EntityStatusConstant {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    DEACTIVATED("DEACTIVATED");

    private static final long serialVersionUID = -9086148061317024860L;
    private final String enumValue;
    private static Map<String, EntityStatusConstant> values = new LinkedHashMap(3, 1.0F);
    private static List<String> literals = new ArrayList(3);
    private static List<String> names = new ArrayList(3);
    private static List<EntityStatusConstant> valueList = new ArrayList(3);

    private EntityStatusConstant(String value) {
        this.enumValue = value;
    }

    public static EntityStatusConstant fromString(String name) {
        return valueOf(name);
    }

    public String value() {
        return this.enumValue;
    }

    public static EntityStatusConstant fromValue(String value) {
        EntityStatusConstant[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            EntityStatusConstant enumName = var1[var3];
            if (enumName.getValue().equals(value)) {
                return enumName;
            }
        }

        throw new IllegalArgumentException("EntityStatusConstant.fromValue(" + value + ')');
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
            values.put(ACTIVE.enumValue, ACTIVE);
            values.put(INACTIVE.enumValue, INACTIVE);
            values.put(DEACTIVATED.enumValue, DEACTIVATED);
        }

        synchronized(valueList) {
            valueList.add(ACTIVE);
            valueList.add(INACTIVE);
            valueList.add(DEACTIVATED);
            valueList = Collections.unmodifiableList(valueList);
        }

        synchronized(literals) {
            literals.add(ACTIVE.enumValue);
            literals.add(INACTIVE.enumValue);
            literals.add(DEACTIVATED.enumValue);
            literals = Collections.unmodifiableList(literals);
        }

        synchronized(names) {
            names.add("ACTIVE");
            names.add("INACTIVE");
            names.add("DEACTIVATED");
            names = Collections.unmodifiableList(names);
        }
    }
}

