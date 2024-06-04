package br.com.fiap.oceanstyle.controller;

import br.com.fiap.oceanstyle.model.Veiculo;
import br.com.fiap.oceanstyle.dto.veiculo.AtualizacaoVeiculoDTO;
import br.com.fiap.oceanstyle.dto.veiculo.CadastroVeiculoDTO;
import br.com.fiap.oceanstyle.dto.veiculo.DetalhesVeiculoDTO;
import br.com.fiap.oceanstyle.repository.VeiculoRepository;
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
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesVeiculoDTO> cadastrar(@RequestBody CadastroVeiculoDTO dto,
            UriComponentsBuilder builder) {
        var veiculo = new Veiculo(dto);
        var empresa = empresaRepository.getReferenceById(dto.empresaId());
        veiculo.setEmpresa(empresa);
        veiculo = veiculoRepository.save(veiculo);
        var uri = builder.path("/veiculos/{id}").buildAndExpand(veiculo.getTie()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesVeiculoDTO(veiculo));
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesVeiculoDTO>> pesquisar(Pageable pageable) {
        var page = veiculoRepository.findAll(pageable).map(DetalhesVeiculoDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesVeiculoDTO> pesquisar(@PathVariable("id") Long id) {
        var veiculo = new DetalhesVeiculoDTO(veiculoRepository.getReferenceById(id));
        return ResponseEntity.ok(veiculo);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesVeiculoDTO> atualizar(@PathVariable("id") Long id,
            @RequestBody AtualizacaoVeiculoDTO dto) {
        var veiculo = veiculoRepository.getReferenceById(id);
        veiculo.atualizar(dto);
        return ResponseEntity.ok(new DetalhesVeiculoDTO(veiculo));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
        Veiculo empresa = veiculoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Veiculo não encontrada com ID: " + id));
        veiculoRepository.delete(empresa);
        return ResponseEntity.noContent().build();
    }

}
