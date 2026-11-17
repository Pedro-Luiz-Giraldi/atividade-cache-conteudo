package school.sptech.atividade_cache_conteudo.mapper;

import java.util.List;

import school.sptech.atividade_cache_conteudo.domain.Conteudo;
import school.sptech.atividade_cache_conteudo.dto.ConteudoRequestDto;
import school.sptech.atividade_cache_conteudo.dto.ConteudoResponseDto;

public final class ConteudoMapper {

    private ConteudoMapper() {
    }

    public static Conteudo toEntity(ConteudoRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Conteudo conteudo = new Conteudo();
        conteudo.setName(dto.getName());
        conteudo.setPwr(dto.getPwr());
        conteudo.setSpd(dto.getSpd());
        conteudo.setRng(dto.getRng());
        conteudo.setPer(dto.getPer());
        conteudo.setPrc(dto.getPrc());
        conteudo.setDev(dto.getDev());
        return conteudo;
    }

    public static ConteudoResponseDto toResponse(Conteudo conteudo) {
        if (conteudo == null) {
            return null;
        }
        ConteudoResponseDto dto = new ConteudoResponseDto();
        dto.setId(conteudo.getId());
        dto.setName(conteudo.getName());
        dto.setPwr(conteudo.getPwr());
        dto.setSpd(conteudo.getSpd());
        dto.setRng(conteudo.getRng());
        dto.setPer(conteudo.getPer());
        dto.setPrc(conteudo.getPrc());
        dto.setDev(conteudo.getDev());
        return dto;
    }

    public static List<ConteudoResponseDto> toResponseList(List<Conteudo> conteudos) {
        return conteudos.stream().map(ConteudoMapper::toResponse).toList();
    }
}
