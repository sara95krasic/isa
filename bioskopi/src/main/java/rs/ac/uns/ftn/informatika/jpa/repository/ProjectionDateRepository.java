package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.jpa.domain.ProjectionDate;

public interface ProjectionDateRepository extends Repository<ProjectionDate, Long> {
	
	ProjectionDate findOne(Long id);

}
