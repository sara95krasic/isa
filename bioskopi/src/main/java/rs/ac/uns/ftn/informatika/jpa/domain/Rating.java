package rs.ac.uns.ftn.informatika.jpa.domain;

public enum Rating {
	TERRIBLE(1), POOR(2), AVERAGE(3), GOOD(4), EXCELLENT(5);
    private final int id;
    Rating(int id) { this.id = id; }
    public int getValue() { return id; }
}
