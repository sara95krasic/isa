package packages.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import packages.beans.Korisnik;
import packages.beans.Oglas;
import packages.beans.RegistrovaniKorisnik;
import packages.beans.RezervisaniOglas;
import packages.enumerations.OglasStatus;
import packages.security.TokenUtils;
import packages.services.KorisnikService;
import packages.services.OglasService;
import packages.services.RegistrovaniKorisnikService;
import packages.services.RezervisaniOglasService;

@Transactional
@Controller
@RestController
@RequestMapping(value = "app/secured/")
public class RezervisaniOglasController {

	@Autowired
	private OglasService os;	
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private RegistrovaniKorisnikService regKorisnikService;
	
	@Autowired
	private RezervisaniOglasService ros;
	
	
	@RequestMapping(value = "rezervisiOglas/{idOlgasa}", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<RezervisaniOglas> dodajRezOlgas(@PathVariable int idOlgasa,  ServletRequest request) throws IOException{
		
		HttpHeaders httpHeader = new HttpHeaders();
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String token = httpRequest.getHeader("token");
		
		
		if(token == null) {
			return null;
		}
		String email = tokenUtils.getUsernameFromToken(token);

		Korisnik korisnik = korisnikService.getKorisnikByEmail(email);
		
		if(korisnik==null) {
			return null;
		}

		RegistrovaniKorisnik logregKorisnik = regKorisnikService.getRegKorisnikByKorisnikId(korisnik);

		Oglas oglas = os.getOglasById(new Long(idOlgasa));

		if(oglas !=null) {
			
			RezervisaniOglas rezervacija = new RezervisaniOglas(logregKorisnik.getId(), oglas.getId());

			RezervisaniOglas retVal =  ros.addRezOgl(rezervacija);

			if(retVal != null) {
				return new ResponseEntity<RezervisaniOglas>(retVal,HttpStatus.OK);
			}
			
		}
		
		return null;
	
	}
}
