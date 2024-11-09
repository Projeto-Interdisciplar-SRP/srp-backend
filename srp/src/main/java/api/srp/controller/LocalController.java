package api.srp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import api.srp.dto.response.WrapperResponseDTO;
import api.srp.model.entity.Local;
import api.srp.model.repository.LocalRepository;

@RestController
@RequestMapping("/local")
@CrossOrigin("*")
public class LocalController {

    @Autowired
    private LocalRepository repository;

    @PostMapping("/register")
    public WrapperResponseDTO<Local> createLocal(@RequestBody Local local) {
        Local savedLocal = repository.save(local);
        return new WrapperResponseDTO<Local>(true, "Local criado com sucesso!", savedLocal);
    }

    @GetMapping(value = {"/", ""})
    public WrapperResponseDTO<List<Local>> getAllLocais() {
        List<Local> locais = repository.findAll();
        return new WrapperResponseDTO<List<Local>>(true, "Lista de locais obtida com sucesso!", locais);
    }

    @GetMapping("/{id}")
    public WrapperResponseDTO<Local> getLocalById(@PathVariable String id) {
        Optional<Local> local = repository.findById(id);
        
        if (local.isPresent()) {
            return new WrapperResponseDTO<Local>(true, "Local encontrado com sucesso!", local.get());
        } else {
            return new WrapperResponseDTO<>(false, "Local não encontrado!", null);
        }
    }

    @PutMapping("/update")
    public WrapperResponseDTO<Local> updateLocal(@RequestBody Local localDetails) {

        Optional<Local> localOptional = repository.findById(localDetails.getId());

        if (localOptional.isPresent()) {
            Local local = localOptional.get();
            local.setNome(localDetails.getNome());
            local.setRua(localDetails.getRua());
            local.setBairro(localDetails.getBairro());
            local.setCidade(localDetails.getCidade());

            Local updatedLocal = repository.save(local);
            return new WrapperResponseDTO<Local>(true, "Local atualizado com sucesso!", updatedLocal);
        } else {
            return new WrapperResponseDTO<>(false, "Local não encontrado!", null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public WrapperResponseDTO<Void> deleteLocal(@PathVariable String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return new WrapperResponseDTO<>(true, "Local excluído com sucesso!", null);
        } else {
            return new WrapperResponseDTO<>(false, "Local não encontrado!", null);
        }
    }
}
