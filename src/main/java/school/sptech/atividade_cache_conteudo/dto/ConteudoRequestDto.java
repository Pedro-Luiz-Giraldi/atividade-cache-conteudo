package school.sptech.atividade_cache_conteudo.dto;

public class ConteudoRequestDto {
    private String name;
    private String pwr;
    private String spd;
    private String rng;
    private String per;
    private String prc;
    private String dev;

    public ConteudoRequestDto() {
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
