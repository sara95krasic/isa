package rs.ac.uns.ftn.informatika.jpa.domain;

public enum TheaterType {
	MOVIE_THEATER(0), PLAY_THEATER(1);
    private final int id;
    TheaterType(int id) { this.id = id; }
    public int getValue() { return id; }
}
