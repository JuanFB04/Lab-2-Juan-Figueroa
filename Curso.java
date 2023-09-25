/**
 * Universidad del Valle de Guatemala
 * Programación orientada a objetos
 * @author Juan Ignacio Figueroa, 23092
 * @fechaCreación: 17/09/23
 * @fechaModificación: 24/09/23
 */
public class Curso {
    private String code;
    private String nom;
    private int periodos;
    private int alum;
    private Profesor profesor;

    public Curso(String code, String nom, int periodos, int alum, Profesor profesor){
        this.code=code;
        this.nom=nom;
        this.periodos=periodos;
        this.alum=alum;
        this.profesor=profesor;
    }
    public Curso(String rotulador, Profesor profesor){
        this.code=rotulador;
        this.profesor=profesor;
    }

    public String getCode(){return code;}
    public void setCode(String code){this.code=code;}
    public String getNom(){return nom;}
    public void setNom(String nom){this.nom=nom;}
    public int getPeriodos(){return periodos;}
    public void setPeriodos(int periodos){this.periodos=periodos;}
    public int getAlum(){return alum;}
    public void setAlum(int alum){this.alum=alum;}
    public String getProfesor(){return profesor.getNom();}
   

}
