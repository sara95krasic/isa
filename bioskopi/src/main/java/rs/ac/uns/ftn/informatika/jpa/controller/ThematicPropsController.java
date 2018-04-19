package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.jpa.domain.ThematicProps;
import rs.ac.uns.ftn.informatika.jpa.domain.ThematicPropsType;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.DTOs.ThematicPropsDTO;
import rs.ac.uns.ftn.informatika.jpa.service.ThematicPropsService;

@RestController
@RequestMapping("/thematic_props")
public class ThematicPropsController {

	@Autowired
	private ThematicPropsService thematicPropsService;
	
	
	@RequestMapping(value ="/save_thematic_props",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ThematicPropsDTO> save(@RequestBody ThematicProps thematicProps){
		ThematicPropsDTO thematicPropsDTO = new ThematicPropsDTO(thematicPropsService.save(thematicProps));
		return new ResponseEntity<ThematicPropsDTO>(thematicPropsDTO,HttpStatus.CREATED);
	}
	
	@RequestMapping(value ="/get_all_thematic_props", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<List<ThematicPropsDTO>> getAllThematicProps(){
		List<ThematicProps> thematicPropsList = thematicPropsService.findAll();
		List<ThematicPropsDTO> thematicPropsDTO = new ArrayList<ThematicPropsDTO>();
		for(ThematicProps thematicProps : thematicPropsList){
			thematicPropsDTO.add(new ThematicPropsDTO(thematicProps));
		}
		return new ResponseEntity<List<ThematicPropsDTO>>(thematicPropsDTO,HttpStatus.OK);
	}
	
	@RequestMapping( value = "/{theaterId}/{tptype}",method = RequestMethod.GET)
	public ResponseEntity<List<ThematicPropsDTO>> getAllThematicPropsByCV(@PathVariable Long theaterId, @PathVariable ThematicPropsType tptype, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("loggedUser");
		List<ThematicPropsDTO> thematicPropsListDTO = new ArrayList<ThematicPropsDTO>();
		if(tptype.name().equals("USED")) {
			System.out.println("usao u polovne");
			List<ThematicProps> thematicPropsList = thematicPropsService.findByTheaterIdAndTptypeAndCreatedByNot(theaterId, tptype, user.getId());
			for(ThematicProps thematicProps : thematicPropsList) {
				thematicPropsListDTO.add(new ThematicPropsDTO(thematicProps));
			}
		} else {
			System.out.println("usao u nove");
			List<ThematicProps> thematicPropsList = thematicPropsService.findByTheaterIdAndTptype(theaterId, tptype);
			for(ThematicProps thematicProps : thematicPropsList) {
				thematicPropsListDTO.add(new ThematicPropsDTO(thematicProps));
			}
		}
		return new ResponseEntity<List<ThematicPropsDTO>>(thematicPropsListDTO,HttpStatus.OK);		 
	}
	
	@RequestMapping(value="/my/{theaterId}/{tptype}", method = RequestMethod.GET)
	public ResponseEntity<List<ThematicPropsDTO>> getMyThematicProps(@PathVariable Long theaterId, @PathVariable ThematicPropsType tptype, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("loggedUser");
		List<ThematicPropsDTO> thematicPropsListDTO = new ArrayList<ThematicPropsDTO>();
		List<ThematicProps> thematicPropsList = thematicPropsService.findByTheaterIdAndTptypeAndCreatedBy(theaterId, tptype, user.getId());
		for(ThematicProps thematicProps : thematicPropsList) {
			thematicPropsListDTO.add(new ThematicPropsDTO(thematicProps));
		}
		return new ResponseEntity<List<ThematicPropsDTO>>(thematicPropsListDTO,HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public ResponseEntity<ThematicPropsDTO> deleteThematicProps(@PathVariable Long id) {
		ThematicProps thematicProps = thematicPropsService.deleteThematicProps(id);
		ThematicPropsDTO thematicPropsDTO = new ThematicPropsDTO(thematicProps);	
		return new ResponseEntity<ThematicPropsDTO>(thematicPropsDTO,HttpStatus.OK);
	}
	
	@RequestMapping(value="/modify/{id}",method = RequestMethod.PUT)
	public ResponseEntity<ThematicPropsDTO> modifyThematicProps(@RequestBody ThematicProps thematicProps, @PathVariable Long id){
		ThematicProps modified = thematicPropsService.modifyThematicProps(thematicProps, id);
		return new ResponseEntity<ThematicPropsDTO>(new ThematicPropsDTO(modified),HttpStatus.OK);
	}
	
	@RequestMapping(value="/approve/{id}", method = RequestMethod.GET)
	public ResponseEntity<ThematicPropsDTO> approveProp(@PathVariable Long id) {
		ThematicProps thematicProps = thematicPropsService.findById(id);
		thematicProps.setApproved(true);
		ThematicPropsDTO thematicPropsDTO = new ThematicPropsDTO(thematicPropsService.save(thematicProps));
		return new ResponseEntity<ThematicPropsDTO>(thematicPropsDTO,HttpStatus.ACCEPTED);
	}
}
