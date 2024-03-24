package model.entity;

public class Adresse {
    private int id;
    private int code;
    private int numero;
    private String rue;
    private String departement;

    public Adresse() {
    }

    public Adresse(int id, int code, int numero, String rue, String departement) {
        this.setId(id);
        this.setCode(code);
        this.setNumero(numero);
        this.setRue(rue);
        this.setDepartement(departement);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }


    @Override
    public String toString() {
        return "Adresse{" +
                "id=" + id +
                ", code=" + code +
                ", numero=" + numero +
                ", rue='" + rue + '\'' +
                ", departement='" + departement + '\'' +
                '}';
    }
}
