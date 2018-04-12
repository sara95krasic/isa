package rs.ac.uns.ftn.informatika.jpa.domain;

public enum ThematicPropsType {
	
	NEW(0), USED(1);
    private final int id;
    ThematicPropsType(int id) { this.id = id; }
    public int getValue() { return id; }

}
