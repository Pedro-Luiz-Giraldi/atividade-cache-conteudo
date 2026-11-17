package school.sptech.atividade_cache_conteudo.service;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import school.sptech.atividade_cache_conteudo.domain.Conteudo;

@Component
public class ConteudoCache {

    private record EntradaCache(
        Conteudo conteudo,
        Instant dataCriacao,
        Instant ultimoAcesso
    ) {}

    private Integer maxSize;
    private Integer ttl;
    private Map<Long, EntradaCache> cache;

    // dados do cache
    private Integer hits;
    private Integer misses;
    private Integer evictions;

    public ConteudoCache(
            // valores configurados no application.yaml
            @Value("${app.cache.max-itens}") Integer maxSize, 
            @Value("${app.cache.ttl-segundos}") Integer ttl
    ) {
        this.cache = new ConcurrentHashMap<Long, EntradaCache>();
        this.maxSize = maxSize;
        this.ttl = ttl;
        this.hits = 0;
        this.misses = 0;
        this.evictions = 0;
    }

    public void salvar(Conteudo conteudo) {
        Instant agora = Instant.now();

        // se é uma entrada nova ou atualização, e não passa do limite
        if (cache.size() < maxSize || cache.containsKey(conteudo.getId())) {
            cache.put(conteudo.getId(), 
                    new EntradaCache(conteudo, agora, agora));
            return;
        }

        // Remoção ao atingir o limite:
        // se a entrada nova passar do limite,
        // encontra a menos recentemente usada, remove ela, e adiciona a nova
        Long idMenosUsado = null;
        Instant ultimoAcessoMaisAntigo = null;
        for (Map.Entry<Long, EntradaCache> entrada : cache.entrySet()) {
            if (ultimoAcessoMaisAntigo == null ||
                    entrada.getValue().ultimoAcesso.isBefore(ultimoAcessoMaisAntigo)) { // politica LRU
                idMenosUsado = entrada.getKey();
                ultimoAcessoMaisAntigo = entrada.getValue().ultimoAcesso;
            }
        }
        cache.remove(idMenosUsado);
        cache.put(conteudo.getId(), 
                new EntradaCache(conteudo, agora, agora));
        evictions++;
    }

    public Conteudo buscar(Long id) {
        Instant agora = Instant.now();
        EntradaCache entrada = cache.get(id);

        if (entrada == null) {
            misses++;
            return null;
        }

        if (entrada.dataCriacao.isBefore(agora.minusSeconds(ttl))) {
            remover(id);
            misses++;
            return null;
        }

        cache.put(id, new EntradaCache(
                    entrada.conteudo, 
                    entrada.dataCriacao, 
                    agora)); // atualiza ultimoAcesso
        hits++;
        return entrada.conteudo;
    }

    public void remover(Long id) {
        cache.remove(id);
    }

    public void limpar() {
        cache.clear();
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public Integer getTtl() {
        return ttl;
    }

    public Map<Long, EntradaCache> getCache() {
        return cache;
    }

    public Integer getHits() {
        return hits;
    }

    public Integer getMisses() {
        return misses;
    }

    public Integer getEvictions() {
        return evictions;
    }

    public Integer getTamanhoAtual() {
        return cache.size();
    }
}
