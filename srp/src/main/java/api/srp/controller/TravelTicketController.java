package api.srp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import api.srp.dto.response.WrapperResponseDTO;
import api.srp.model.entity.Travel;
import api.srp.model.entity.Ticket;
import api.srp.model.repository.TravelRepository;
import api.srp.model.repository.TicketRepository;

@RestController
@RequestMapping("/travel-ticket")
public class TravelTicketController {

    @Autowired
    private TravelRepository travelRepository;

    @Autowired
    private TicketRepository ticketRepository;

    // Travel endpoints

    @PostMapping("/travel/register")
    public WrapperResponseDTO<Travel> createTravel(@RequestBody Travel travel) {
        Travel savedTravel = travelRepository.save(travel);
        return new WrapperResponseDTO<>(true, "Viagem criada com sucesso!", savedTravel);
    }

    @GetMapping("/travel")
    public WrapperResponseDTO<List<Travel>> getAllTravels() {
        List<Travel> travels = travelRepository.findAll();
        return new WrapperResponseDTO<>(true, "Lista de viagens obtida com sucesso!", travels);
    }

    @GetMapping("/travel/{id}")
    public WrapperResponseDTO<Travel> getTravelById(@PathVariable String id) {
        Optional<Travel> travel = travelRepository.findById(id);

        if (travel.isPresent()) {
            return new WrapperResponseDTO<>(true, "Viagem encontrada com sucesso!", travel.get());
        } else {
            return new WrapperResponseDTO<>(false, "Viagem não encontrada!", null);
        }
    }

    @PutMapping("/travel/update")
    public WrapperResponseDTO<Travel> updateTravel(@RequestBody Travel travelDetails) {
        Optional<Travel> travelOptional = travelRepository.findById(travelDetails.getId());

        if (travelOptional.isPresent()) {
            Travel travel = travelOptional.get();
            travel.setId_ingresso(travelDetails.getId_ingresso());
            travel.setId_paroquia(travelDetails.getId_paroquia());
            travel.setData_partida(travelDetails.getData_partida());
            travel.setId_onibus(travelDetails.getId_onibus());

            Travel updatedTravel = travelRepository.save(travel);
            return new WrapperResponseDTO<>(true, "Viagem atualizada com sucesso!", updatedTravel);
        } else {
            return new WrapperResponseDTO<>(false, "Viagem não encontrada!", null);
        }
    }

    @DeleteMapping("/travel/delete/{id}")
    public WrapperResponseDTO<Void> deleteTravel(@PathVariable String id) {
        if (travelRepository.existsById(id)) {
            travelRepository.deleteById(id);
            return new WrapperResponseDTO<>(true, "Viagem excluída com sucesso!", null);
        } else {
            return new WrapperResponseDTO<>(false, "Viagem não encontrada!", null);
        }
    }

    // Ticket endpoints

    @PostMapping("/ticket/register")
    public WrapperResponseDTO<Ticket> createTicket(@RequestBody Ticket ticket) {
        Ticket savedTicket = ticketRepository.save(ticket);
        return new WrapperResponseDTO<>(true, "Ingresso criado com sucesso!", savedTicket);
    }

    @GetMapping("/ticket")
    public WrapperResponseDTO<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return new WrapperResponseDTO<>(true, "Lista de ingressos obtida com sucesso!", tickets);
    }

    @GetMapping("/ticket/{id}")
    public WrapperResponseDTO<Ticket> getTicketById(@PathVariable String id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);

        if (ticket.isPresent()) {
            return new WrapperResponseDTO<>(true, "Ingresso encontrado com sucesso!", ticket.get());
        } else {
            return new WrapperResponseDTO<>(false, "Ingresso não encontrado!", null);
        }
    }

    @PutMapping("/ticket/update")
    public WrapperResponseDTO<Ticket> updateTicket(@RequestBody Ticket ticketDetails) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketDetails.getId());

        if (ticketOptional.isPresent()) {
            Ticket ticket = ticketOptional.get();
            ticket.setId_usuario(ticketDetails.getId_usuario());
            ticket.setQuantidade(ticketDetails.getQuantidade());
            ticket.setPreco(ticketDetails.getPreco());

            Ticket updatedTicket = ticketRepository.save(ticket);
            return new WrapperResponseDTO<>(true, "Ingresso atualizado com sucesso!", updatedTicket);
        } else {
            return new WrapperResponseDTO<>(false, "Ingresso não encontrado!", null);
        }
    }

    @DeleteMapping("/ticket/delete/{id}")
    public WrapperResponseDTO<Void> deleteTicket(@PathVariable String id) {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);
            return new WrapperResponseDTO<>(true, "Ingresso excluído com sucesso!", null);
        } else {
            return new WrapperResponseDTO<>(false, "Ingresso não encontrado!", null);
        }
    }
}
