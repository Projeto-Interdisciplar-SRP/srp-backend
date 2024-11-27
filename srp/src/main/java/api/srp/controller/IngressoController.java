package api.srp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import api.srp.dto.response.WrapperResponseDTO;
import api.srp.model.entity.Ingresso;
import api.srp.model.repository.IngressoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ingresso")
public class IngressoController {

    @Autowired
    private IngressoRepository repository;

    @PostMapping("/register")
    public WrapperResponseDTO<Ingresso> createIngresso(@RequestBody Ingresso ingresso) {
        Ingresso savedIngresso = repository.save(ingresso);
        return new WrapperResponseDTO<>(true, "Ingresso criado com sucesso!", savedIngresso);
    }

    @GetMapping("/")
    public WrapperResponseDTO<List<Ingresso>> getAllIngressos() {
        List<Ingresso> ingressos = repository.findAll();
        return new WrapperResponseDTO<>(true, "Ingressos obtidos com sucesso!", ingressos);
    }
}
