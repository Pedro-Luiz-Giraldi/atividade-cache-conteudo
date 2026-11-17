package school.sptech.atividade_cache_conteudo.service;

import org.springframework.stereotype.Service;

import io.swagger.v3.oas.models.security.SecurityScheme.In;
import school.sptech.atividade_cache_conteudo.dto.CacheEstatisticasResponseDto;

@Service
public class CacheService {

    private final ConteudoCache cache;

    public CacheService(ConteudoCache cache) {
        this.cache = cache;
    }

    public CacheEstatisticasResponseDto estatisticas() {
        CacheEstatisticasResponseDto estatisticas = new CacheEstatisticasResponseDto();
        estatisticas.setTamanhoAtual(cache.getTamanhoAtual());
        estatisticas.setLimite(cache.getMaxSize());
        estatisticas.setHits(cache.getHits());
        estatisticas.setMisses(cache.getMisses());
        estatisticas.setEvictions(cache.getEvictions());
        Double hitRatio = 0.0;
        Integer total = cache.getHits() + cache.getMisses();
        if (total > 0) {
            hitRatio = (double) cache.getHits() / total;
        }
        estatisticas.setHitRatio(hitRatio);
        return estatisticas;
    }
}
