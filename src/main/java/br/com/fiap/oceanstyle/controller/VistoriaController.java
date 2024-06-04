package br.com.fiap.oceanstyle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.oceanstyle.dto.inspetor.DetalhesInspetorDTO;
import br.com.fiap.oceanstyle.dto.vistoria.AtualizacaoVistoriaDTO;
import br.com.fiap.oceanstyle.dto.vistoria.CadastroVistoriaDTO;
import br.com.fiap.oceanstyle.dto.vistoria.DetalhesVistoriaDTO;
import br.com.fiap.oceanstyle.repository.VeiculoRepository;
import br.com.fiap.oceanstyle.repository.InspetorRepository;
import br.com.fiap.oceanstyle.repository.VistoriaRepository;
import br.com.fiap.oceanstyle.model.Vistoria;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/vistorias")
public class VistoriaController {

    @Autowired
    private VistoriaRepository vistoriaRepository;

    @Autowired
    private InspetorRepository inspetorRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @DeleteMapping("/{idVistoria}/inspetores")
    @Transactional
    public ResponseEntity<Void> removerInspetors(@PathVariable("idVistoria") Long idVistoria) {
        var vistoria = vistoriaRepository.getReferenceById(idVistoria);
        if (vistoria.getInspetores().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        vistoria.getInspetores().clear();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idVistoria}/inspetores/{idInspetores}")
    @Transactional
    public ResponseEntity<Void> removerInspetor(@PathVariable("idVistoria") Long idVistoria,
            @PathVariable("idInspetores") Long idInspetores) {
        var vistoria = vistoriaRepository.getReferenceById(idVistoria);
        var inspetor = inspetorRepository.getReferenceById(idInspetores);
        if (!vistoria.getInspetores().contains(inspetor)) {
            return ResponseEntity.notFound().build();
        }
        vistoria.getInspetores().remove(inspetor);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idVistoria}/inspetores/{idInspetores}")
    @Transactional
    public ResponseEntity<Void> adicionarInspetor(@PathVariable("idVistoria") Long idVistoria,
            @PathVariable("idInspetores") Long idInspetores) {
        var vistoria = vistoriaRepository.getReferenceById(idVistoria);
        var inspetor = inspetorRepository.getReferenceById(idInspetores);
        vistoria.getInspetores().add(inspetor);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idVistoria}/inspetores")
    @Transactional
    public ResponseEntity<List<DetalhesInspetorDTO>> getInspetors(@PathVariable("idVistoria") Long idVistoria) {
        var vistoria = vistoriaRepository.getReferenceById(idVistoria);
        var lista = vistoria.getInspetores().stream().map(DetalhesInspetorDTO::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping
    @Transactional
    public ResponseEntity<List<DetalhesVistoriaDTO>> get(Pageable pageable) {
        var lista = vistoriaRepository.findAll(pageable).stream().map(DetalhesVistoriaDTO::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesVistoriaDTO> get(@PathVariable("id") Long id) {
        var vistoria = new DetalhesVistoriaDTO(vistoriaRepository.getReferenceById(id));
        return ResponseEntity.ok(vistoria);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesVistoriaDTO> create(@RequestBody CadastroVistoriaDTO dto,
            UriComponentsBuilder uriBuilder) {
        var vistoria = new Vistoria(dto);
        var veiculo = veiculoRepository.getReferenceById(dto.veiculoId());
        vistoria.setVeiculo(veiculo);
        vistoriaRepository.save(vistoria);
        var uri = uriBuilder.path("/vistorias/{id}").buildAndExpand(vistoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesVistoriaDTO(vistoria));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesVistoriaDTO> update(@PathVariable("id") Long id,
            @RequestBody AtualizacaoVistoriaDTO dto) {
        var vistoria = vistoriaRepository.getReferenceById(id);
        vistoria.atualizar(dto);
        return ResponseEntity.ok(new DetalhesVistoriaDTO(vistoria));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Vistoria vistoria = vistoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vistoria não encontrada com ID: " + id));
        vistoriaRepository.delete(vistoria);
        return ResponseEntity.noContent().build();
    }

}
