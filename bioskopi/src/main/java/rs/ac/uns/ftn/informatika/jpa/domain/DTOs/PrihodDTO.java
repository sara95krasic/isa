package packages.dto;

public class PrihodDTO {
	
	private int idPozBio;
	private String datumOd;
	private String datumDo;
	private int mode;
	
	private PrihodDTO() {
		
	}
	
	private PrihodDTO(int idPozBio, String datumOd, String datumDo, int mode) {
		this.idPozBio = idPozBio;
		this.datumOd = datumOd;
		this.datumDo = datumDo;
		this.mode = mode;
	}

	public int getIdPozBio() {
		return idPozBio;
	}

	public void setIdPozBio(int idPozBio) {
		this.idPozBio = idPozBio;
	}

	public String getDatumOd() {
		return datumOd;
	}

	public void setDatumOd(String datumOd) {
		this.datumOd = datumOd;
	}

	public String getDatumDo() {
		return datumDo;
	}

	public void setDatumDo(String datumDo) {
		this.datumDo = datumDo;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	@Override
	public String toString() {
		return "PrihodDTO [idPozBio=" + idPozBio + ", datumOd=" + datumOd + ", datumDo=" + datumDo + ", mode=" + mode
				+ "]";
	}


		
	@RequestMapping(value = "set_role/{user_id}/{role}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean setRole(@PathVariable Long user_id, @PathVariable Role role) {
		if (user_id == SessionService.getCurrentlyLoggedUser().getId())
			return false; //ne mozes sam sebi menjati role
		return this.userService.setRole(user_id, role);
	}

		@Autowired
	private ReservationService reservationService;
	
	@RequestMapping(value = "add_new_reservation",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean addNewReservation(@RequestBody Reservation reservation) {
		return this.reservationService.makeNewReservation(reservation);
	}
	
	@RequestMapping(value = "refuse_invitation/{reservation_id}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean addNewHallForTheater(@PathVariable Long reservation_id) {
		return this.reservationService.cancelReservedSeat(reservation_id);
	}
		
}
