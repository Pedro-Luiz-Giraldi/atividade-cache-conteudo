package school.sptech.atividade_cache_conteudo.dto;

import school.sptech.atividade_cache_conteudo.domain.Conteudo;
import school.sptech.atividade_cache_conteudo.domain.StatusCache;

public class BuscaPorIdResponseDto {
    private Conteudo conteudo;
    private StatusCache statusCache;
    
    public BuscaPorIdResponseDto() {
    }

    public BuscaPorIdResponseDto(Conteudo conteudo, StatusCache statusCache) {
        this.conteudo = conteudo;
        this.statusCache = statusCache;
    }

    public Conteudo getConteudo() {
        return conteudo;
    }
    public void setConteudo(Conteudo conteudo) {
        this.conteudo = conteudo;
    }
    public StatusCache getStatusCache() {
        return statusCache;
    }
    public void setStatusCache(StatusCache statusCache) {
        this.statusCache = statusCache;
    }
}
