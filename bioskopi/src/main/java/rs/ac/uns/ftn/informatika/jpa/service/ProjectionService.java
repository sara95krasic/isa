package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import rs.ac.uns.ftn.informatika.jpa.domain.Projection;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.ProjectionDTO;

public interface ProjectionService {
	Projection addNewProjection(Projection p);

	Page<ProjectionDTO> getAllProjections(Pageable pageable);

	boolean setPosterImage(Long projection_id, byte[] image);
}
