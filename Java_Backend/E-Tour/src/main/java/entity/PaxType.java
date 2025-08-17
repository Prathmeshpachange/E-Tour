package entity;

public enum PaxType {
    TWIN_SHARING("Twin Sharing"),
    SINGLE_PERSON("Single Person"),
    EXTRA_PERSON("Extra Person"),
    CHILD_WITH_BED("Child With Bed"),
    CHILD_WITHOUT_BED("Child Without Bed");

    private final String displayName;

    PaxType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
