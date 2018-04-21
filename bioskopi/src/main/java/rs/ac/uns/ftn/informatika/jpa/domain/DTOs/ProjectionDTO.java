package rs.ac.uns.ftn.informatika.jpa.domain.DTOs;

public interface ProjectionDTO {
	String getTitle();
	
	String getDescription();
	
	String getDirector();
	
	float getRuntime();
	
	Long getId();
	
	String getPoster();
	
	void setAverageRating(double rating);
	double getAverageRating();
	
}
