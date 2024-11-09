package api.srp.dto.request;

import api.srp.model.entity.Ticket;
import api.srp.model.entity.Travel;

public class ReservationRequestDTO {
    private Ticket ticket;
    private Travel travel;
    
	public ReservationRequestDTO(Ticket ticket, Travel travel) {
		super();
		this.ticket = ticket;
		this.travel = travel;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Travel getTravel() {
		return travel;
	}

	public void setTravel(Travel travel) {
		this.travel = travel;
	}

    
    
    
}