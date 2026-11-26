package es.safareyes.swapcloset.controller;

import es.safareyes.swapcloset.dto.request.CategoriaRequest;
import es.safareyes.swapcloset.dto.response.CategoriaResponse;
import es.safareyes.swapcloset.service.impl.CategoriaService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaResponse> listar() {
        return categoriaService.listar();
    }

    @GetMapping("/{id}")
    public CategoriaResponse buscarPorId(@PathVariable Integer id) {
        return categoriaService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> crear(@Valid @RequestBody CategoriaRequest peticion) {
        CategoriaResponse creada = categoriaService.crear(peticion);

        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creada.id())
                .toUri();

        return ResponseEntity.created(ubicacion).body(creada);
    }

    @PutMapping("/{id}")
    public CategoriaResponse actualizar(@PathVariable Integer id,
                                        @Valid @RequestBody CategoriaRequest peticion) {
        return categoriaService.actualizar(id, peticion);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        categoriaService.eliminar(id);
    }
}