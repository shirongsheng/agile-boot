package com.shirs.agileboot.enums;

public enum OperationType {
    /**
     * 操作类型
     */
    UNKNOWN("unknown"),
    IMPORT("import"),
    EXPORT("export"),
    DELETE("delete"),
    SELECT("select"),
    UPDATE("update"),
    INSERT("insert");

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    OperationType(String s) {
        this.value = s;
    }
}
