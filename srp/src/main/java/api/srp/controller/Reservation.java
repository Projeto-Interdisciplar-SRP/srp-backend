package api.srp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.srp.dto.response.ReservationResponseDTO;

@RestController
@RequestMapping("/reservation")
public class Reservation {

	@PostMapping(value={"/register", "register"})
	public ReservationResponseDTO register() {
		
		
		
	}
	
}
