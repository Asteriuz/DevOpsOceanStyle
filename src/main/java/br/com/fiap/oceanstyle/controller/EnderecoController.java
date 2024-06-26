package br.com.fiap.oceanstyle.controller;

import br.com.fiap.oceanstyle.dto.endereco.DetalhesEnderecoDTO;
import br.com.fiap.oceanstyle.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping
    public ResponseEntity<Page<DetalhesEnderecoDTO>> pesquisar(Pageable pageable) {
        var page = enderecoRepository.findAll(pageable).map(DetalhesEnderecoDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesEnderecoDTO> pesquisar(@PathVariable("id") Long id) {
        var endereco = new DetalhesEnderecoDTO(enderecoRepository.getReferenceById(id));
        return ResponseEntity.ok(endereco);
    }
}
