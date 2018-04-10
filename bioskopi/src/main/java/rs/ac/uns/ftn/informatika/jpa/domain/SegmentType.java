package rs.ac.uns.ftn.informatika.jpa.domain;

public enum SegmentType {
	STANDARD(0), BALCONY(1), VIP(2);
    private final int id;
    SegmentType(int id) { this.id = id; }
    public int getValue() { return id; }
}
