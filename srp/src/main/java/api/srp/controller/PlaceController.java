package api.srp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import api.srp.dto.response.WrapperResponseDTO;
import api.srp.model.entity.Place;
import api.srp.model.repository.PlaceRepository;

@RestController
@RequestMapping("/place")
@CrossOrigin("*")
public class PlaceController {

    @Autowired
    private PlaceRepository repository;

    @PostMapping("/register")
    public WrapperResponseDTO<Place> createPlace(@RequestBody Place place) {
        Place savedPlace = repository.save(place);
        return new WrapperResponseDTO<>(true, "Viajem criado com sucesso!", savedPlace);
    }

    @GetMapping(value = {"/", ""})
    public WrapperResponseDTO<List<Place>> getAllPlaces() {
        List<Place> places = repository.findAll();
        return new WrapperResponseDTO<>(true, "Lista de lugares obtida com sucesso!", places);
    }

    @GetMapping("/{id}")
    public WrapperResponseDTO<Place> getPlaceById(@PathVariable String id) {
        Optional<Place> place = repository.findById(id);
        if (place.isPresent()) {
            return new WrapperResponseDTO<>(true, "Viajem encontrado com sucesso!", place.get());
        } else {
            return new WrapperResponseDTO<>(false, "Viajem não encontrado!", null);
        }
    }

    @PutMapping("/update")
    public WrapperResponseDTO<Place> updatePlace(@RequestBody Place placeDetails) {
        Optional<Place> placeOptional = repository.findById(placeDetails.getId());
        if (placeOptional.isPresent()) {
            Place place = placeOptional.get();
            place.setDestino(placeDetails.getDestino());
            place.setIda(placeDetails.getIda());
            place.setVolta(placeDetails.getVolta());

            Place updatedPlace = repository.save(place);
            return new WrapperResponseDTO<>(true, "Viajem atualizado com sucesso!", updatedPlace);
        } else {
            return new WrapperResponseDTO<>(false, "Viajem não encontrado!", null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public WrapperResponseDTO<Void> deletePlace(@PathVariable String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return new WrapperResponseDTO<>(true, "Viajem excluído com sucesso!", null);
        } else {
            return new WrapperResponseDTO<>(false, "Viajem não encontrado!", null);
        }
    }
}
