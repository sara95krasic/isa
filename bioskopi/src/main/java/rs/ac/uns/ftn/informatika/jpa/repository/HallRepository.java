package rs.ac.uns.ftn.informatika.jpa.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.jpa.domain.Hall;
import rs.ac.uns.ftn.informatika.jpa.domain.HallId;
import rs.ac.uns.ftn.informatika.jpa.domain.Projection;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.ProjectionDTO;

public interface HallRepository extends Repository<Hall, HallId> {


	
}
