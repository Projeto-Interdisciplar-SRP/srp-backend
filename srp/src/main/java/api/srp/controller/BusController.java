package api.srp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import api.srp.dto.response.WrapperResponseDTO;
import api.srp.model.entity.Bus;
import api.srp.model.repository.BusRepository;


@RestController
@RequestMapping("/bus")
@CrossOrigin("*")
public class BusController {

    @Autowired
    private BusRepository repository;

    @PostMapping("/register")
    public WrapperResponseDTO<Bus> createBus(@RequestBody Bus bus) {
        Bus savedBus = repository.save(bus);
        return new WrapperResponseDTO<Bus>(true, "Ônibus criado com sucesso!", savedBus);
    }

    @GetMapping(value = {"/", ""})
    public WrapperResponseDTO<List<Bus>> getAllBuses() {
        List<Bus> buses = repository.findAll();
        return new WrapperResponseDTO<List<Bus>>(true, "Lista de ônibus obtida com sucesso!", buses);
    }

    @GetMapping("/{id}")
    public WrapperResponseDTO<Bus> getBusById(@PathVariable String id) {
        Optional<Bus> bus = repository.findById(id);
        
        if (bus.isPresent()) {
            return new WrapperResponseDTO<Bus>(true, "Ônibus encontrado com sucesso!", bus.get());
        } else {
            return new WrapperResponseDTO<>(false, "Ônibus não encontrado!", null);
        }
    }

    @PutMapping("/update")
    public WrapperResponseDTO<Bus> updateBus(@RequestBody Bus busDetails) {

        Optional<Bus> busOptional = repository.findById(busDetails.getId());

        if (busOptional.isPresent()) {
            Bus bus = busOptional.get();
            bus.setNumero(busDetails.getNumero());
            bus.setPlaca_onibus(busDetails.getPlaca_onibus());

            Bus updatedBus = repository.save(bus);
            return new WrapperResponseDTO<Bus>(true, "Ônibus atualizado com sucesso!", updatedBus);
        } else {
            return new WrapperResponseDTO<>(false, "Ônibus não encontrado!", null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public WrapperResponseDTO<Void> deleteBus(@PathVariable String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return new WrapperResponseDTO<>(true, "Ônibus excluído com sucesso!", null);
        } else {
            return new WrapperResponseDTO<>(false, "Ônibus não encontrado!", null);
        }
    }
}