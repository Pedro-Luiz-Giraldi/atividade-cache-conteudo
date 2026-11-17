package school.sptech.atividade_cache_conteudo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import school.sptech.atividade_cache_conteudo.domain.Conteudo;
import school.sptech.atividade_cache_conteudo.domain.StatusCache;
import school.sptech.atividade_cache_conteudo.dto.BuscaPorIdResponseDto;
import school.sptech.atividade_cache_conteudo.exception.EntidadeNaoEncontradaException;
import school.sptech.atividade_cache_conteudo.repository.ConteudoRepository;

@Service
public class ConteudoService {

   private static final Logger logger = LoggerFactory.getLogger(ConteudoService.class);

    private final ConteudoRepository repository;
    private final ConteudoCache cache;

    public ConteudoService(ConteudoRepository repository, ConteudoCache cache) {
        this.repository = repository;
        this.cache = cache;
    }

    public List<Conteudo> listarTodos() {
        return repository.findAll();
    }

    public BuscaPorIdResponseDto buscarPorId(Long id) {

        long inicio = System.nanoTime();
        Conteudo conteudoCache = cache.buscar(id);

        if (conteudoCache != null) {
            logger.info("Cache HIT - id={} - {} ms", id, (System.nanoTime() - inicio)/1000000);
            return new BuscaPorIdResponseDto(conteudoCache, StatusCache.HIT);
        }

        sleep(); // simular lentidão
        Conteudo conteudo = repository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Conteudo não encontrado"));
        cache.salvar(conteudo);

        logger.info("Cache MISS - id={} - {} ms", id, (System.nanoTime() - inicio)/1000000);

        return new BuscaPorIdResponseDto(conteudo, StatusCache.MISS);
    }

    public Conteudo cadastrar(Conteudo conteudo) {
        Conteudo salvo = repository.save(conteudo);
        return salvo;
    }

    public Conteudo atualizar(Long id, Conteudo conteudo) {
        Conteudo existente = repository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Conteudo não encontrado"));
        existente.setName(conteudo.getName());
        existente.setPwr(conteudo.getPwr());
        existente.setSpd(conteudo.getSpd());
        existente.setRng(conteudo.getRng());
        existente.setPer(conteudo.getPer());
        existente.setPrc(conteudo.getPrc());
        existente.setDev(conteudo.getDev());
        Conteudo salvo = repository.save(existente);

        cache.remover(id);
        return salvo;
    }

    public void excluir(Long id) {
        Conteudo existente = repository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Conteudo não encontrado"));
        repository.delete(existente);
        cache.remover(id);
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
