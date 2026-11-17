package school.sptech.atividade_cache_conteudo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import school.sptech.atividade_cache_conteudo.domain.Conteudo;
import school.sptech.atividade_cache_conteudo.dto.BuscaPorIdResponseDto;
import school.sptech.atividade_cache_conteudo.dto.ConteudoRequestDto;
import school.sptech.atividade_cache_conteudo.dto.ConteudoResponseDto;
import school.sptech.atividade_cache_conteudo.mapper.ConteudoMapper;
import school.sptech.atividade_cache_conteudo.service.ConteudoService;

@RestController
@RequestMapping("/conteudos")
public class ConteudoController {

    private final ConteudoService service;

    public ConteudoController(ConteudoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ConteudoResponseDto>> listar() {
        List<Conteudo> conteudos = service.listarTodos();
        return ResponseEntity.ok(ConteudoMapper.toResponseList(conteudos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConteudoResponseDto> buscarPorId(@PathVariable Long id) {
        BuscaPorIdResponseDto buscaResponse = service.buscarPorId(id);
        return ResponseEntity.ok()
                .header("X-Cache", buscaResponse.getStatusCache().toString())
                .body(ConteudoMapper.toResponse(buscaResponse.getConteudo()));
    }

    @PostMapping
    public ResponseEntity<ConteudoResponseDto> criar(@RequestBody ConteudoRequestDto dto) {
        Conteudo conteudo = ConteudoMapper.toEntity(dto);
        Conteudo criado = service.cadastrar(conteudo);
        return ResponseEntity.status(HttpStatus.CREATED).body(ConteudoMapper.toResponse(criado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConteudoResponseDto> atualizar(@PathVariable Long id, @RequestBody ConteudoRequestDto dto) {
        Conteudo conteudo = ConteudoMapper.toEntity(dto);
        Conteudo atualizado = service.atualizar(id, conteudo);
        return ResponseEntity.ok(ConteudoMapper.toResponse(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
