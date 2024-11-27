package api.srp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import api.srp.dto.response.WrapperResponseDTO;
import api.srp.model.entity.Reserva;
import api.srp.model.repository.ReservaRepository;

import java.util.List;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaRepository repository;

    @PostMapping("/register")
    public WrapperResponseDTO<Reserva> createReserva(@RequestBody Reserva reserva) {
        Reserva savedReserva = repository.save(reserva);
        return new WrapperResponseDTO<>(true, "Reserva criada com sucesso!", savedReserva);
    }

    @GetMapping("/")
    public WrapperResponseDTO<List<Reserva>> getAllReservas() {
        List<Reserva> reservas = repository.findAll();
        return new WrapperResponseDTO<>(true, "Reservas obtidas com sucesso!", reservas);
    }
}
