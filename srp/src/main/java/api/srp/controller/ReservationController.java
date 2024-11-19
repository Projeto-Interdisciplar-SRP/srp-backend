package api.srp.controller;

import java.util.ArrayList;
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
import api.srp.model.entity.Ticket;
import api.srp.model.entity.Travel;
import api.srp.model.entity.User;
import api.srp.model.repository.BusRepository;
import api.srp.model.repository.LocalRepository;
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

    @PostMapping("/register")
    public WrapperResponseDTO<Object> createReservation(@RequestBody ReservationRequestDTO reservationRequest) {

        Ticket ticket = reservationRequest.getTicket();
        Travel travel = reservationRequest.getTravel();

        // Verificar existência de `User` relacionado ao `id_usuario` do `Ticket`
        Optional<User> user = userRepository.findById(ticket.getId_usuario());
        if (user.isEmpty()) {
            return new WrapperResponseDTO<>(false, "Usuário com ID " + ticket.getId_usuario() + " não encontrado!", null);
        }
        
        // Verificar existência de `Local` relacionado ao `id_paroquia` do `Travel`
        Optional<Local> local = localRepository.findById(travel.getId_paroquia());
        if (local.isEmpty()) {
            return new WrapperResponseDTO<>(false, "Paróquia com ID " + travel.getId_paroquia() + " não encontrada!", null);
        }

        // Verificar existência de `Bus` relacionado ao `id_onibus` do `Travel`
        Optional<Bus> bus = busRepository.findById(travel.getId_onibus());
        if (bus.isEmpty()) {
            return new WrapperResponseDTO<>(false, "Ônibus com ID " + travel.getId_onibus() + " não encontrado!", null);
        }

        // Todos os dados relacionados existem, então podemos salvar
        Ticket savedTicket = ticketRepository.save(ticket);
        
        travel.setId_ingresso(savedTicket.getId());
        
        travelRepository.save(travel);

        return new WrapperResponseDTO<>(true, "Reserva criada com sucesso!", null);
    }



    @GetMapping(value = {"/", ""})
    public WrapperResponseDTO<List<ReservationResponseDTO>> getAllReservations() {
        List<Travel> travels = travelRepository.findAll();
        List<ReservationResponseDTO> reservationResponses = new ArrayList<>();

        for (Travel travel : travels) {
            // Fetch related entities with null checks
            Optional<Ticket> ticketOptional = ticketRepository.findById(travel.getId_ingresso());
            if (ticketOptional.isEmpty()) {
                continue; // Skip this travel if ticket not found
            }

            Optional<User> userOptional = userRepository.findById(ticketOptional.get().getId_usuario());
            if (userOptional.isEmpty()) {
                continue; // Skip this travel if user not found
            }

            Optional<Local> localOptional = localRepository.findById(travel.getId_paroquia());
            if (localOptional.isEmpty()) {
                continue; // Skip this travel if local not found
            }

            Optional<Bus> busOptional = busRepository.findById(travel.getId_onibus());
            if (busOptional.isEmpty()) {
                continue; // Skip this travel if bus not found
            }

            // Construct the DTO with valid data
            ReservationResponseDTO reservationResponseDTO = new ReservationResponseDTO(
                travel.getId(),
                userOptional.get().getId(),
                userOptional.get().getNome(),
                userOptional.get().getEmail(),
                userOptional.get().getRua(),
                userOptional.get().getBairro(),
                userOptional.get().getCidade(),
                userOptional.get().getCpf(),
                userOptional.get().getRg(),
                userOptional.get().getTelefone(),
                userOptional.get().getAdm(),
                userOptional.get().getIdParoquia(),
                localOptional.get().getId(),
                localOptional.get().getNome(),
                localOptional.get().getRua(),
                localOptional.get().getBairro(),
                localOptional.get().getCidade(),
                busOptional.get().getId(),
                busOptional.get().getNumero(),
                busOptional.get().getPlaca_onibus(),
                ticketOptional.get().getQuantidade(),
                ticketOptional.get().getPreco(),
                travel.getData_partida(),
                ticketOptional.get().getStatus()
            );

            reservationResponses.add(reservationResponseDTO);
        }

        return new WrapperResponseDTO<>(true, "Lista de reservas obtida com sucesso!", reservationResponses);
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
        Optional<Ticket> ticketOptional = ticketRepository.findById(travel.getId_ingresso());
        if (!ticketOptional.isPresent()) {
            return new WrapperResponseDTO<>(false, "Ticket não encontrado para a reserva.", null);
        }

        Ticket ticket = ticketOptional.get();

        // Buscar o User relacionado ao Ticket
        Optional<User> userOptional = userRepository.findById(ticket.getId_usuario());
        if (!userOptional.isPresent()) {
            return new WrapperResponseDTO<>(false, "Usuário não encontrado para o Ticket.", null);
        }

        User user = userOptional.get();

        // Buscar o Local relacionado ao Travel
        Optional<Local> localOptional = localRepository.findById(travel.getId_paroquia());
        if (!localOptional.isPresent()) {
            return new WrapperResponseDTO<>(false, "Local não encontrado para a reserva.", null);
        }

        Local local = localOptional.get();

        // Buscar o Bus relacionado ao Travel
        Optional<Bus> busOptional = busRepository.findById(travel.getId_onibus());
        if (!busOptional.isPresent()) {
            return new WrapperResponseDTO<>(false, "Ônibus não encontrado para a reserva.", null);
        }

        Bus bus = busOptional.get();

        // Criar o DTO de resposta com as informações encontradas
        ReservationResponseDTO response = new ReservationResponseDTO(
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
	        ticket.getQuantidade(),
	        ticket.getPreco(),
	        travel.getData_partida(),
	        ticket.getStatus()
            
        );

        // Retornar a resposta de sucesso com o DTO preenchido
        return new WrapperResponseDTO<>(true, "Reserva encontrada!", response);
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
        Optional<Ticket> existingTicketOpt = ticketRepository.findById(existingTravel.getId_ingresso());
        if (existingTicketOpt.isEmpty()) {
            return new WrapperResponseDTO<>(false, "Ticket com ID " + existingTravel.getId() + " não encontrado!", null);
        }

        Ticket existingTicket = existingTicketOpt.get();

        // Atualizar os dados de Travel
        existingTravel.setId_onibus(reservationRequest.getId_onibus());
        existingTravel.setId_paroquia(reservationRequest.getId_paroquia());
        existingTravel.setData_partida(reservationRequest.getData_partida());
        
        // Atualizar os dados de Ticket
        existingTicket.setId_usuario(reservationRequest.getId_usuario());
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
        Optional<Ticket> ticketOptional = ticketRepository.findById(travel.getId_ingresso());
        if (!ticketOptional.isPresent()) {
            return new WrapperResponseDTO<>(false, "Ticket não encontrado para a reserva.", null);
        }

        Ticket ticket = ticketOptional.get();

        // Buscar o User relacionado ao Ticket
        Optional<User> userOptional = userRepository.findById(ticket.getId_usuario());
        if (!userOptional.isPresent()) {
            return new WrapperResponseDTO<>(false, "Usuário não encontrado para o Ticket.", null);
        }

        User user = userOptional.get();

        // Buscar o Local relacionado ao Travel
        Optional<Local> localOptional = localRepository.findById(travel.getId_paroquia());
        if (!localOptional.isPresent()) {
            return new WrapperResponseDTO<>(false, "Local não encontrado para a reserva.", null);
        }

        Local local = localOptional.get();

        // Buscar o Bus relacionado ao Travel
        Optional<Bus> busOptional = busRepository.findById(travel.getId_onibus());
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
    
}