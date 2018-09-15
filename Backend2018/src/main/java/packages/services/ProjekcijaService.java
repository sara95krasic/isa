package packages.services;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import packages.beans.PozBio;
import packages.beans.PredFilm;
import packages.beans.Projekcija;
import packages.beans.Sala;
import packages.repositories.ProjekcijaRepository;
import packages.serviceInterfaces.ProjekcijaInterface;

@Service
public class ProjekcijaService implements ProjekcijaInterface{

	@Autowired
	private ProjekcijaRepository pr;
	
	@Override
	public Projekcija getProjekcija(Long id) {
		// TODO Auto-generated method stub
		return pr.findOne(id);
	}

	@Override
	public Projekcija addProjekcija(Projekcija projekcija) {
		// TODO Auto-generated method stub
		return pr.save(projekcija);
	}

	@Override
	public ArrayList<Projekcija> getAllProjekcijas() {
		// TODO Auto-generated method stub
		return (ArrayList<Projekcija>) pr.findAll();
	}

	@Override
	public ArrayList<Projekcija> getProjekcijasByPredFilm(PredFilm predFilm) {
		// TODO Auto-generated method stub
		return pr.findByPredFilm(predFilm);
	}

	@Override
	public ArrayList<Projekcija> getProjekcijasBySala(Sala sala) {
		// TODO Auto-generated method stub
		return pr.findBySala(sala);
	}

	@Override
	public ArrayList<Projekcija> getProjekcijasBetween(PozBio pozBio, Date pocetak, Date kraj) {
		// TODO Auto-generated method stub
		return pr.projekcijasBetween(pozBio, pocetak, kraj);
	}

	@Override
	public Page<Projekcija> getProjekcijasByPredFilmPage(PredFilm predFilm, Pageable pageable) {
		// TODO Auto-generated method stub
		return pr.findByPredFilm(predFilm, pageable);
	}

	@Override
	public Long countByPredFilm(PredFilm predFilm) {
		// TODO Auto-generated method stub
		return pr.countByPredFilm(predFilm);
	}

	@Override
	public void deleteProjekcija(Projekcija projekcija) {
		// TODO Auto-generated method stub
		pr.delete(projekcija);
	}
	

}
