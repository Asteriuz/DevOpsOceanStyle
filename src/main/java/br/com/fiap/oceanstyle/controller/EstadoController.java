package br.com.fiap.oceanstyle.controller;

import br.com.fiap.oceanstyle.model.Estado;
import br.com.fiap.oceanstyle.dto.estado.AtualizacaoEstadoDTO;
import br.com.fiap.oceanstyle.dto.estado.CadastroEstadoDTO;
import br.com.fiap.oceanstyle.dto.estado.DetalhesEstadoDTO;
import br.com.fiap.oceanstyle.repository.EstadoRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesEstadoDTO> cadastrar(@RequestBody CadastroEstadoDTO dto,
            UriComponentsBuilder builder) {
        var estado = new Estado(dto);
        estado = estadoRepository.save(estado);
        var uri = builder.path("/estados/{id}").buildAndExpand(estado.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesEstadoDTO(estado));
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesEstadoDTO>> pesquisar(Pageable pageable) {
        var page = estadoRepository.findAll(pageable).map(DetalhesEstadoDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesEstadoDTO> pesquisar(@PathVariable("id") Long id) {
        var estado = new DetalhesEstadoDTO(estadoRepository.getReferenceById(id));
        return ResponseEntity.ok(estado);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesEstadoDTO> atualizar(@PathVariable("id") Long id,
            @RequestBody AtualizacaoEstadoDTO dto) {
        var estado = estadoRepository.getReferenceById(id);
        estado.atualizar(dto);
        return ResponseEntity.ok(new DetalhesEstadoDTO(estado));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
        Estado estado = estadoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estado não encontrado com ID: " + id));
        estadoRepository.delete(estado);
        return ResponseEntity.noContent().build();
    }

}
