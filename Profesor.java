public class Profesor {
    private String nom;
    private int carnet;
    private String mail;
    private String tel;

    public Profesor(String nom, int carnet, String mail, String tel){
        this.nom=nom;
        this.carnet=carnet;
        this.mail=mail;
        this.tel=tel;
    }

    public String getNom(){return nom;}
    public void setNom(String nom){this.nom=nom;}
    public int getCarnet(){return carnet;}
    public void setCarnet(int carnet){this.carnet=carnet;}
    public String getMail(){return mail;}
    public void setMail(String mail){this.mail=mail;}
    public String getTel(){return tel;}
    public void setTel(String tel){this.tel=tel;}

}
