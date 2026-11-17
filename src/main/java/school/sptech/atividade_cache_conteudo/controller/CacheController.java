package school.sptech.atividade_cache_conteudo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import school.sptech.atividade_cache_conteudo.dto.CacheEstatisticasResponseDto;
import school.sptech.atividade_cache_conteudo.service.CacheService;
import school.sptech.atividade_cache_conteudo.service.ConteudoCache;

@RestController
@RequestMapping("/cache")
public class CacheController {

    private final CacheService cacheService;
    private final ConteudoCache cache;

    public CacheController(CacheService cacheService, ConteudoCache cache) {
        this.cacheService = cacheService;
        this.cache = cache;
    }

    @GetMapping("/estatisticas")
    public ResponseEntity<CacheEstatisticasResponseDto> estatisticas() {
        CacheEstatisticasResponseDto dto = cacheService.estatisticas();
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    public ResponseEntity<Void> limpar() {
        cache.limpar();
        return ResponseEntity.noContent().build();
    }
}
