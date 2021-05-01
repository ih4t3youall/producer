package ar.com.sourcesistemas.producer;

public class Data {

    public long numero;
    public String time;
    public long id;

    public Data(long id, String numero, String time){
        this.id = id;
        this.numero = Long.valueOf(numero);
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
