package api.srp.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import api.srp.dto.request.ReservationRequestDTO;
import api.srp.dto.request.ReservationUpdateRequestDTO;
import api.srp.dto.response.ReservationResponseDTO;
import api.srp.dto.response.WrapperResponseDTO;
import api.srp.model.entity.Bus;
import api.srp.model.entity.Local;
import api.srp.model.entity.Place;
import api.srp.model.entity.Ticket;
import api.srp.model.entity.Travel;
import api.srp.model.entity.User;
import api.srp.model.repository.BusRepository;
import api.srp.model.repository.LocalRepository;
import api.srp.model.repository.PlaceRepository;
import api.srp.model.repository.TicketRepository;
import api.srp.model.repository.TravelRepository;
import api.srp.model.repository.UserRepository;

@RestController
@RequestMapping("/reservation")
@CrossOrigin("*")
public class ReservationController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TravelRepository travelRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private BusRepository busRepository;
    
    @Autowired
    private PlaceRepository placeRepository;
    
    @PostMapping("/register")
    public WrapperResponseDTO<Object> createReservation(@RequestBody ReservationRequestDTO reservationRequest) {

        Ticket ticket = reservationRequest.getTicket();
        Travel travel = reservationRequest.getTravel();

        // Verificar existência de `User` relacionado ao `id_usuario` do `Ticket`
        Optional<User> user = userRepository.findById(ticket.getIdUsuario());
        if (user.isEmpty()) {
            return new WrapperResponseDTO<>(false, "Usuário com ID " + ticket.getIdUsuario() + " não encontrado!", null);
        }
        
        // Verificar existência de `Local` relacionado ao `id_paroquia` do `Travel`
        Optional<Local> local = localRepository.findById(travel.getIdParoquia());
        if (local.isEmpty()) {
            return new WrapperResponseDTO<>(false, "Paróquia com ID " + travel.getIdParoquia() + " não encontrada!", null);
        }

        // Verificar existência de `Bus` relacionado ao `id_onibus` do `Travel`
        Optional<Bus> bus = busRepository.findById(travel.getIdOnibus());
        if (bus.isEmpty()) {
            return new WrapperResponseDTO<>(false, "Ônibus com ID " + travel.getIdOnibus() + " não encontrado!", null);
        }
        
        Optional<Place> place = placeRepository.findById(travel.getIdPlace());
        if (place.isEmpty()) {
            return new WrapperResponseDTO<>(false, "Viajem com ID " + travel.getIdPlace() + " não encontrado!", null);
        }
        
        // Todos os dados relacionados existem, então podemos salvar
        Ticket savedTicket = ticketRepository.save(ticket);
        
        travel.setIdIngresso(savedTicket.getId());
        
        travelRepository.save(travel);
        
        // Construct the DTO with valid data
        ReservationResponseDTO reservationResponseDTO = new ReservationResponseDTO(
            travel.getId(),
            
            user.get().getId(),
            user.get().getNome(),
            user.get().getEmail(),
            user.get().getRua(),
            user.get().getBairro(),
            user.get().getCidade(),
            user.get().getCpf(),
            user.get().getRg(),
            user.get().getTelefone(),
            user.get().getAdm(),
            user.get().getIdParoquia(),
            
            local.get().getId(),
            local.get().getNome(),
            local.get().getRua(),
            local.get().getBairro(),
            local.get().getCidade(),
            
            bus.get().getId(),
            bus.get().getNumero(),
            bus.get().getPlaca_onibus(),
            
            place.get().getDestino(),
            place.get().getPreco_unitario(),
            place.get().getIda(),
            place.get().getVolta(),
            
            ticket.getQuantidade(),
            ticket.getPreco(),
            travel.getDataPartida(),
            ticket.getStatus(),
            ticket.getType()
        );

        return new WrapperResponseDTO<>(true, "Reserva criada com sucesso!", reservationResponseDTO);
    }

    @GetMapping("/{id}")
    public WrapperResponseDTO<ReservationResponseDTO> getReservation(@PathVariable String id) {
        // Buscar o Travel relacionado ao id fornecido
        Optional<Travel> travelOptional = travelRepository.findById(id);
        
        // Se o Travel não for encontrado, retornar resposta de erro
        if (!travelOptional.isPresent()) {
            return new WrapperResponseDTO<>(false, "Reserva não encontrada.", null);
        }

        Travel travel = travelOptional.get();

        // Buscar o Ticket relacionado ao Travel
        Optional<Ticket> ticketOptional = ticketRepository.findById(travel.getIdIngresso());
        if (!ticketOptional.isPresent()) {
            return new WrapperResponseDTO<>(false, "Ticket não encontrado para a reserva.", null);
        }

        Ticket ticket = ticketOptional.get();

        // Buscar o User relacionado ao Ticket
        Optional<User> userOptional = userRepository.findById(ticket.getIdUsuario());
        if (!userOptional.isPresent()) {
            return new WrapperResponseDTO<>(false, "Usuário não encontrado para o Ticket.", null);
        }

        User user = userOptional.get();

        // Buscar o Local relacionado ao Travel
        Optional<Local> localOptional = localRepository.findById(travel.getIdParoquia());
        if (!localOptional.isPresent()) {
            return new WrapperResponseDTO<>(false, "Local não encontrado para a reserva.", null);
        }

        Local local = localOptional.get();

        // Buscar o Bus relacionado ao Travel
        Optional<Bus> busOptional = busRepository.findById(travel.getIdOnibus());
        if (!busOptional.isPresent()) {
            return new WrapperResponseDTO<>(false, "Ônibus não encontrado para a reserva.", null);
        }

        Bus bus = busOptional.get();

        Optional<Place> placeOptional = placeRepository.findById(travel.getIdPlace());
        if (placeOptional.isEmpty()) {
            return new WrapperResponseDTO<>(false, "Viajem com ID " + travel.getIdPlace() + " não encontrado!", null);
        }

        // Construct the DTO with valid data
        ReservationResponseDTO reservationResponseDTO = new ReservationResponseDTO(
            travel.getId(),
            user.getId(),
            user.getNome(),
            user.getEmail(),
            user.getRua(),
            user.getBairro(),
            user.getCidade(),
            user.getCpf(),
            user.getRg(),
            user.getTelefone(),
            user.getAdm(),
            user.getIdParoquia(),
            local.getId(),
            local.getNome(),
            local.getRua(),
            local.getBairro(),
            local.getCidade(),
            bus.getId(),
            bus.getNumero(),
            bus.getPlaca_onibus(),
            
            placeOptional.get().getDestino(),
            placeOptional.get().getPreco_unitario(),
            placeOptional.get().getIda(),
            placeOptional.get().getVolta(),
            
            ticketOptional.get().getQuantidade(),
            ticketOptional.get().getPreco(),
            travel.getDataPartida(),
            ticketOptional.get().getStatus(),
            ticketOptional.get().getType()
        );

        // Retornar a resposta de sucesso com o DTO preenchido
        return new WrapperResponseDTO<>(true, "Reserva encontrada!", reservationResponseDTO);
    }

    @PutMapping("/edit/{id}")
    public WrapperResponseDTO<ReservationResponseDTO> updateReservation(@PathVariable String id, @RequestBody ReservationUpdateRequestDTO reservationRequest) {

        // Verificar se a 'Travel' com o ID fornecido existe
        Optional<Travel> existingTravelOpt = travelRepository.findById(id);
        if (existingTravelOpt.isEmpty()) {
            return new WrapperResponseDTO<>(false, "Viagem com ID " + id + " não encontrada!", null);
        }
        
        Travel existingTravel = existingTravelOpt.get();

        // Verificar existência de `User` relacionado ao `id_usuario` do `Ticket`
        Optional<User> user = userRepository.findById(reservationRequest.getId_usuario());
        if (user.isEmpty()) {
            return new WrapperResponseDTO<>(false, "Usuário com ID " + reservationRequest.getId_usuario() + " não encontrado!", null);
        }

        // Verificar existência de `Local` relacionado ao `id_paroquia` do `Travel`
        Optional<Local> local = localRepository.findById(reservationRequest.getId_paroquia());
        if (local.isEmpty()) {
            return new WrapperResponseDTO<>(false, "Paróquia com ID " + reservationRequest.getId_paroquia() + " não encontrada!", null);
        }

        // Verificar existência de `Bus` relacionado ao `id_onibus` do `Travel`
        Optional<Bus> bus = busRepository.findById(reservationRequest.getId_onibus());
        if (bus.isEmpty()) {
            return new WrapperResponseDTO<>(false, "Ônibus com ID " + reservationRequest.getId_onibus() + " não encontrado!", null);
        }

        // Verificar se o Ticket existe antes de atualizá-lo
        Optional<Ticket> existingTicketOpt = ticketRepository.findById(existingTravel.getIdIngresso());
        if (existingTicketOpt.isEmpty()) {
            return new WrapperResponseDTO<>(false, "Ticket com ID " + existingTravel.getId() + " não encontrado!", null);
        }
        
        Optional<Place> place = placeRepository.findById(existingTravel.getIdPlace());
        if (place.isEmpty()) {
            return new WrapperResponseDTO<>(false, "Viajem com ID " + existingTravel.getIdPlace() + " não encontrado!", null);
        }

        Ticket existingTicket = existingTicketOpt.get();

        // Atualizar os dados de Travel
        existingTravel.setIdOnibus(reservationRequest.getId_onibus());
        existingTravel.setIdParoquia(reservationRequest.getId_paroquia());
        existingTravel.setDataPartida(reservationRequest.getData_partida());
        
        // Atualizar os dados de Ticket
        existingTicket.setIdUsuario(reservationRequest.getId_usuario());
        existingTicket.setPreco(reservationRequest.getPreco());
        existingTicket.setQuantidade(reservationRequest.getQuantidade());
        
        travelRepository.save(existingTravel);
        ticketRepository.save(existingTicket);

        // Retornar resposta com dados atualizados
        return new WrapperResponseDTO<>(true, "Reserva atualizada com sucesso!", null);
    }

    @DeleteMapping("/delete/{id}")
    public WrapperResponseDTO<String> deleteReservation(@PathVariable String id) {
        // Buscar o Travel relacionado ao id fornecido
        Optional<Travel> travelOptional = travelRepository.findById(id);
        
        // Se o Travel não for encontrado, retornar resposta de erro
        if (!travelOptional.isPresent()) {
            return new WrapperResponseDTO<>(false, "Reserva não encontrada.", null);
        }

        Travel travel = travelOptional.get();

        // Buscar o Ticket relacionado ao Travel
        Optional<Ticket> ticketOptional = ticketRepository.findById(travel.getIdIngresso());
        if (!ticketOptional.isPresent()) {
            return new WrapperResponseDTO<>(false, "Ticket não encontrado para a reserva.", null);
        }

        Ticket ticket = ticketOptional.get();

        // Buscar o User relacionado ao Ticket
        Optional<User> userOptional = userRepository.findById(ticket.getIdUsuario());
        if (!userOptional.isPresent()) {
            return new WrapperResponseDTO<>(false, "Usuário não encontrado para o Ticket.", null);
        }

        // Buscar o Local relacionado ao Travel
        Optional<Local> localOptional = localRepository.findById(travel.getIdParoquia());
        if (!localOptional.isPresent()) {
            return new WrapperResponseDTO<>(false, "Local não encontrado para a reserva.", null);
        }

        // Buscar o Bus relacionado ao Travel
        Optional<Bus> busOptional = busRepository.findById(travel.getIdOnibus());
        if (!busOptional.isPresent()) {
            return new WrapperResponseDTO<>(false, "Ônibus não encontrado para a reserva.", null);
        }

        Bus bus = busOptional.get();

        // Agora, vamos excluir as entidades associadas, conforme necessário (dependendo da lógica do seu sistema)
        // Aqui, estou assumindo que as entidades podem ser excluídas diretamente ou que precisam ser removidas de outras tabelas primeiro

        // Excluir o Ticket
        ticketRepository.delete(ticket);

        // Excluir o Travel
        travelRepository.delete(travel);

        // Aqui você pode adicionar lógica para excluir ou manipular outras entidades relacionadas, se necessário

        // Retornar resposta de sucesso
        return new WrapperResponseDTO<>(true, "Reserva deletada com sucesso!", "Reserva excluída com sucesso.");
    }
    
    @GetMapping("/user/{idUsuario}")
    public WrapperResponseDTO<List<ReservationResponseDTO>> getTravelsByUser(@PathVariable String idUsuario) {
        List<ReservationResponseDTO> reservations = new ArrayList<>();
        
        List<Ticket> tickets = ticketRepository.findByIdUsuario(idUsuario);
        
        for (Ticket ticket : tickets) {
        	
            travelRepository.findByIdIngresso(ticket.getId()).forEach(travel -> {
            	
                // Assumindo que os métodos para obter `user`, `local`, `bus`, e `placeOptional` já estão implementados
                User user = userRepository.findById(ticket.getIdUsuario()).orElseThrow();
                Local local = localRepository.findById(travel.getIdParoquia()).orElseThrow();
                Bus bus = busRepository.findById(travel.getIdOnibus()).orElseThrow();
                Optional<Place> placeOptional = placeRepository.findById(travel.getIdPlace());
                Optional<Ticket> ticketOptional = ticketRepository.findById(travel.getIdIngresso());

                ReservationResponseDTO reservationResponseDTO = new ReservationResponseDTO(
                    travel.getId(),
                    user.getId(),
                    user.getNome(),
                    user.getEmail(),
                    user.getRua(),
                    user.getBairro(),
                    user.getCidade(),
                    user.getCpf(),
                    user.getRg(),
                    user.getTelefone(),
                    user.getAdm(),
                    user.getIdParoquia(),
                    local.getId(),
                    local.getNome(),
                    local.getRua(),
                    local.getBairro(),
                    local.getCidade(),
                    bus.getId(),
                    bus.getNumero(),
                    bus.getPlaca_onibus(),
                    placeOptional.get().getDestino(),
                    placeOptional.get().getPreco_unitario(),
                    placeOptional.get().getIda(),
                    placeOptional.get().getVolta(),
                    ticketOptional.get().getQuantidade(),
                    ticketOptional.get().getPreco(),
                    travel.getDataPartida(),
                    ticketOptional.get().getStatus(),
                    ticketOptional.get().getType()
                );

                reservations.add(reservationResponseDTO);
            });
        }

        return new WrapperResponseDTO<>(true, "Reservas encontradas com sucesso", reservations);
    }
    
}