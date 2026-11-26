package es.safareyes.swapcloset.controller;

import es.safareyes.swapcloset.dto.request.PrendaRequest;
import es.safareyes.swapcloset.dto.response.PrendaResponse;
import es.safareyes.swapcloset.service.PrendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/prendas")
@RequiredArgsConstructor
public class PrendaController {

    private final PrendaService prendaService;

    @GetMapping
    public List<PrendaResponse> listar(@RequestParam(required = false) String titulo) {
        return prendaService.listar(titulo);
    }

    @GetMapping("/{id}")
    public PrendaResponse buscarPorId(@PathVariable Integer id) {
        return prendaService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<PrendaResponse> crear(@Valid @RequestBody PrendaRequest peticion) {
        PrendaResponse creada = prendaService.crear(peticion);

        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creada.id())
                .toUri();

        return ResponseEntity.created(ubicacion).body(creada);
    }

    @PutMapping("/{id}")
    public PrendaResponse actualizar(@PathVariable Integer id,
                                     @Valid @RequestBody PrendaRequest peticion) {
        return prendaService.actualizar(id, peticion);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        prendaService.eliminar(id);
    }
}
