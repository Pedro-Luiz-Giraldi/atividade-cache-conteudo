package school.sptech.atividade_cache_conteudo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.atividade_cache_conteudo.domain.Conteudo;

public interface ConteudoRepository extends JpaRepository<Conteudo, Long> {
}
