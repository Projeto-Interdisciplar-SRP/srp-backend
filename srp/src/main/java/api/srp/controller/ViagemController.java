package api.srp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import api.srp.dto.response.WrapperResponseDTO;
import api.srp.model.entity.Viagem;
import api.srp.model.repository.ViagemRepository;

import java.util.List;

@RestController
@RequestMapping("/viagem")
public class ViagemController {

    @Autowired
    private ViagemRepository repository;

    @PostMapping("/register")
    public WrapperResponseDTO<Viagem> createViagem(@RequestBody Viagem viagem) {
        Viagem savedViagem = repository.save(viagem);
        return new WrapperResponseDTO<>(true, "Viagem criada com sucesso!", savedViagem);
    }

    @GetMapping("/")
    public WrapperResponseDTO<List<Viagem>> getAllViagens() {
        List<Viagem> viagens = repository.findAll();
        return new WrapperResponseDTO<>(true, "Viagens obtidas com sucesso!", viagens);
    }
}
