package school.sptech.atividade_cache_conteudo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stand")
public class Conteudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String pwr;
    private String spd;
    private String rng;
    private String per;
    private String prc;
    private String dev;

    public Conteudo(Long id, String name, String pwr, String spd, String rng, String per, String prc, String dev) {
        this.id = id;
        this.name = name;
        this.pwr = pwr;
        this.spd = spd;
        this.rng = rng;
        this.per = per;
        this.prc = prc;
        this.dev = dev;
    }

    public Conteudo() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPwr() {
        return pwr;
    }
    public void setPwr(String pwr) {
        this.pwr = pwr;
    }
    public String getSpd() {
        return spd;
    }
    public void setSpd(String spd) {
        this.spd = spd;
    }
    public String getRng() {
        return rng;
    }
    public void setRng(String rng) {
        this.rng = rng;
    }
    public String getPer() {
        return per;
    }
    public void setPer(String per) {
        this.per = per;
    }
    public String getPrc() {
        return prc;
    }
    public void setPrc(String prc) {
        this.prc = prc;
    }
    public String getDev() {
        return dev;
    }
    public void setDev(String dev) {
        this.dev = dev;
    }
}
