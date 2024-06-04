package br.com.fiap.oceanstyle.controller;

import br.com.fiap.oceanstyle.model.Inspetor;
import br.com.fiap.oceanstyle.dto.inspetor.AtualizacaoInspetorDTO;
import br.com.fiap.oceanstyle.dto.inspetor.CadastroInspetorDTO;
import br.com.fiap.oceanstyle.dto.inspetor.DetalhesInspetorDTO;
import br.com.fiap.oceanstyle.repository.InspetorRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/inspetores")
public class InspetorController {

    @Autowired
    private InspetorRepository inspetorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesInspetorDTO> cadastrar(@RequestBody CadastroInspetorDTO dto,
            UriComponentsBuilder builder) {
        var inspetor = new Inspetor(dto);
        inspetor = inspetorRepository.save(inspetor);
        var uri = builder.path("/Inspetor/{id}").buildAndExpand(inspetor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesInspetorDTO(inspetor));
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesInspetorDTO>> pesquisar(Pageable pageable) {
        var page = inspetorRepository.findAll(pageable).map(DetalhesInspetorDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesInspetorDTO> pesquisar(@PathVariable("id") Long id) {
        var inspetor = new DetalhesInspetorDTO(inspetorRepository.getReferenceById(id));
        return ResponseEntity.ok(inspetor);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesInspetorDTO> atualizar(@PathVariable("id") Long id,
            @RequestBody AtualizacaoInspetorDTO dto) {
        var inspetor = inspetorRepository.getReferenceById(id);
        inspetor.atualizar(dto);
        return ResponseEntity.ok(new DetalhesInspetorDTO(inspetor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
        Inspetor inspetor = inspetorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inspetor não encontrado com ID: " + id));
        inspetorRepository.delete(inspetor);
        return ResponseEntity.noContent().build();
    }

}
