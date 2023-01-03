package listeners.enums;

public enum TestRailStatus {
    PASSED(1),
    BLOCKED(2),
    UNTESTED(3),
    SKIPPED(4),
    FAILED(5);

    final Integer id;

    TestRailStatus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
