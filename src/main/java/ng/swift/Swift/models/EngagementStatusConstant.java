package ng.swift.Swift.models;

import java.util.*;

public enum EngagementStatusConstant {
    ENGAGED("ENGAGED"),
    DISENGAGED("DISENGAGED");

    private static final long serialVersionUID = -9086148061317024860L;
    private final String enumValue;
    private static Map<String, EngagementStatusConstant> values = new LinkedHashMap(2, 1.0F);
    private static List<String> literals = new ArrayList(2);
    private static List<String> names = new ArrayList(2);
    private static List<EngagementStatusConstant> valueList = new ArrayList(2);

    private EngagementStatusConstant(String value) {
        this.enumValue = value;
    }

    public static EngagementStatusConstant fromString(String name) {
        return valueOf(name);
    }

    public String value() {
        return this.enumValue;
    }

    public static EngagementStatusConstant fromValue(String value) {
        EngagementStatusConstant[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            EngagementStatusConstant enumName = var1[var3];
            if (enumName.getValue().equals(value)) {
                return enumName;
            }
        }

        throw new IllegalArgumentException("EngagementStatusConstant.fromValue(" + value + ')');
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
            values.put(ENGAGED.enumValue, ENGAGED);
            values.put(DISENGAGED.enumValue, DISENGAGED);
        }

        synchronized(valueList) {
            valueList.add(ENGAGED);
            valueList.add(DISENGAGED);
            valueList = Collections.unmodifiableList(valueList);
        }

        synchronized(literals) {
            literals.add(ENGAGED.enumValue);
            literals.add(DISENGAGED.enumValue);
            literals = Collections.unmodifiableList(literals);
        }

        synchronized(names) {
            names.add("ENGAGED");
            names.add("DISENGAGED");
            names = Collections.unmodifiableList(names);
        }
    }
}
