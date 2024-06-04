package br.com.fiap.oceanstyle.controller;

import br.com.fiap.oceanstyle.model.Empresa;
import br.com.fiap.oceanstyle.dto.empresa.AtualizacaoEmpresaDTO;
import br.com.fiap.oceanstyle.dto.empresa.CadastroEmpresaDTO;
import br.com.fiap.oceanstyle.dto.empresa.DetalhesEmpresaDTO;
import br.com.fiap.oceanstyle.repository.EmpresaRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesEmpresaDTO> cadastrar(@RequestBody CadastroEmpresaDTO dto,
            UriComponentsBuilder builder) {
        var empresa = new Empresa(dto);
        empresa = empresaRepository.save(empresa);
        var uri = builder.path("/Empresa/{id}").buildAndExpand(empresa.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesEmpresaDTO(empresa));
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesEmpresaDTO>> pesquisar(Pageable pageable) {
        var page = empresaRepository.findAll(pageable).map(DetalhesEmpresaDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesEmpresaDTO> pesquisar(@PathVariable("id") Long id) {
        var empresa = new DetalhesEmpresaDTO(empresaRepository.getReferenceById(id));
        return ResponseEntity.ok(empresa);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesEmpresaDTO> atualizar(@PathVariable("id") Long id,
            @RequestBody AtualizacaoEmpresaDTO dto) {
        var empresa = empresaRepository.getReferenceById(id);
        empresa.atualizar(dto);
        return ResponseEntity.ok(new DetalhesEmpresaDTO(empresa));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrado com ID: " + id));
        empresaRepository.delete(empresa);
        return ResponseEntity.noContent().build();
    }

}
