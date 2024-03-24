package model.entity;

public class Client extends Personne{
    private int numeroFidelite;
    private int codePostal;

    public Client(){
        super();
    }

    public Client(int id, String nom, String prenom, String email, int numeroFidelite, int codePostal) {
        super(id, nom, prenom, email);
        this.setNumeroFidelite(numeroFidelite);
        this.setCodePostal(codePostal);
    }

    public int getNumeroFidelite() {
        return numeroFidelite;
    }

    public void setNumeroFidelite(int numeroFidelite) {
        this.numeroFidelite = numeroFidelite;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    @Override
    public String toString() {
        return "Client{" +
                "numeroFidelite=" + numeroFidelite +
                ", codePostal=" + codePostal +
                ", id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
