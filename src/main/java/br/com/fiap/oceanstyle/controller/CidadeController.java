package br.com.fiap.oceanstyle.controller;

import br.com.fiap.oceanstyle.model.Cidade;
import br.com.fiap.oceanstyle.dto.cidade.AtualizacaoCidadeDTO;
import br.com.fiap.oceanstyle.dto.cidade.CadastroCidadeDTO;
import br.com.fiap.oceanstyle.dto.cidade.DetalhesCidadeDTO;
import br.com.fiap.oceanstyle.repository.CidadeRepository;
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
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesCidadeDTO> cadastrar(@RequestBody CadastroCidadeDTO dto,
            UriComponentsBuilder builder) {
        var cidade = new Cidade(dto);
        var estado = estadoRepository.getReferenceById(dto.estadoId());
        cidade.setEstado(estado);
        cidade = cidadeRepository.save(cidade);
        var uri = builder.path("/cidades/{id}").buildAndExpand(cidade.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesCidadeDTO(cidade));
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesCidadeDTO>> pesquisar(Pageable pageable) {
        var page = cidadeRepository.findAll(pageable).map(DetalhesCidadeDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesCidadeDTO> pesquisar(@PathVariable("id") Long id) {
        var cidade = new DetalhesCidadeDTO(cidadeRepository.getReferenceById(id));
        return ResponseEntity.ok(cidade);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesCidadeDTO> atualizar(@PathVariable("id") Long id,
            @RequestBody AtualizacaoCidadeDTO dto) {
        var cidade = cidadeRepository.getReferenceById(id);
        cidade.atualizar(dto);
        return ResponseEntity.ok(new DetalhesCidadeDTO(cidade));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
        Cidade estado = cidadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cidade não encontrada com ID: " + id));
        cidadeRepository.delete(estado);
        return ResponseEntity.noContent().build();
    }

}
