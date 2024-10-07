package api.srp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	@GetMapping("/")
	public String welcome() {
		
		return "Essa é a API do SRP (Sistema de Reservas para Paróquias)";
		
	}
	
}