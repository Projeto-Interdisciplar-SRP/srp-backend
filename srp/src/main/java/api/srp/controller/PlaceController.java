package api.srp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import api.srp.dto.response.PlaceResponseDTO;
import api.srp.dto.response.WrapperResponseDTO;
import api.srp.model.entity.Local;
import api.srp.model.entity.Place;
import api.srp.model.repository.LocalRepository;
import api.srp.model.repository.PlaceRepository;

@RestController
@RequestMapping("/place")
@CrossOrigin("*")
public class PlaceController {

    @Autowired
    private PlaceRepository repository;
    
    @Autowired
    private LocalRepository localRepository;

    @PostMapping("/register")
    public ResponseEntity<WrapperResponseDTO<PlaceResponseDTO>> createPlace(@RequestBody Place place) {
        // Verifica se o `id_paroquia` existe no repositório `LocalRepository`
        Optional<Local> optionalLocal = localRepository.findById(place.getIdParoquia());

        if (optionalLocal.isEmpty()) {
            return ResponseEntity.status(404).body(
                new WrapperResponseDTO<>(false, "Paróquia não encontrada!", null)
            );
        }

        // Define o Local encontrado no `place`
        Local local = optionalLocal.get();
        place.setIdParoquia(local.getId());

        // Salva o novo `Place`
        Place savedPlace = repository.save(place);

        // Constrói o DTO de resposta
        PlaceResponseDTO responseDTO = new PlaceResponseDTO(
            savedPlace.getId(),
            optionalLocal.get(),
            savedPlace.getIda(),
            savedPlace.getVolta()
        );

        return ResponseEntity.ok(
            new WrapperResponseDTO<>(true, "Viagem criada com sucesso!", responseDTO)
        );
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<WrapperResponseDTO<List<PlaceResponseDTO>>> getAllPlaces() {
        List<Place> places = repository.findAll();

        // Converte a lista de Place para PlaceResponseDTO
        List<PlaceResponseDTO> placeResponseDTOs = places.stream()
                .map(place -> {
                    // Busca o Local correspondente ao idParoquia
                    Optional<Local> localOptional = localRepository.findById(place.getIdParoquia());
                    
                    // Se o Local for encontrado, cria o PlaceResponseDTO com ele
                    Local local = localOptional.orElse(null);
                    
                    return new PlaceResponseDTO(
                            place.getId(),
                            local, // Adiciona o Local completo
                            place.getIda(),
                            place.getVolta()
                    );
                })
                .toList();

        return ResponseEntity.ok(new WrapperResponseDTO<>(true, "Lista de viagens obtida com sucesso!", placeResponseDTOs));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WrapperResponseDTO<PlaceResponseDTO>> getPlaceById(@PathVariable String id) {
        Optional<Place> placeOptional = repository.findById(id);

        if (placeOptional.isPresent()) {
            Place place = placeOptional.get();
            
            // Busca o Local correspondente ao idParoquia
            Optional<Local> localOptional = localRepository.findById(place.getIdParoquia());
            
            // Se o Local for encontrado, cria o PlaceResponseDTO com ele
            Local local = localOptional.orElse(null);

            // Cria o DTO com os detalhes completos do Local
            PlaceResponseDTO placeResponseDTO = new PlaceResponseDTO(
                place.getId(),
                local, // Adiciona o Local completo
                place.getIda(),
                place.getVolta()
            );

            return ResponseEntity.ok(new WrapperResponseDTO<>(true, "Viagem encontrada com sucesso!", placeResponseDTO));
        } else {
            return ResponseEntity.status(404).body(new WrapperResponseDTO<>(false, "Viagem não encontrada!", null));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<WrapperResponseDTO<Place>> updatePlace(@RequestBody Place placeDetails) {
    	
        Optional<Place> placeOptional = repository.findById(placeDetails.getId());

        if (placeOptional.isPresent()) {
            Place place = placeOptional.get();
            place.setIdParoquia(placeDetails.getIdParoquia());
            place.setIda(placeDetails.getIda());
            place.setVolta(placeDetails.getVolta());

            Place updatedPlace = repository.save(place);
            return ResponseEntity.ok(new WrapperResponseDTO<>(true, "Viagem atualizada com sucesso!", updatedPlace));
        } else {
            return ResponseEntity.status(404).body(new WrapperResponseDTO<>(false, "Viagem não encontrada!", null));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<WrapperResponseDTO<Void>> deletePlace(@PathVariable String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok(new WrapperResponseDTO<>(true, "Viagem excluída com sucesso!", null));
        } else {
            return ResponseEntity.status(404).body(new WrapperResponseDTO<>(false, "Viagem não encontrada!", null));
        }
    }
}
