import java.util.ArrayList;
public class Laboratorio {
    private int capmax;
    
    public Laboratorio(int capmax){
        this.capmax=capmax;
    }
    public int getCapMax(){return capmax;}
    public void setCapMax(int capmax){this.capmax=capmax;}


    /**
     * @param horarios
     */
    public void display(ArrayList<ArrayList<Curso>> horarios){
        for(ArrayList<Curso>fila:horarios){
            for(Curso curso: fila){
                System.out.print(curso.getCode()+"   ");
            }
            System.out.println(" ");
        }
    }

    /**
     * @param horarios
     * @param dia
     * @param hora
     * @param curso
     * @return
     */
    public ArrayList<ArrayList<Curso>> asignarCurso(ArrayList<ArrayList<Curso>> horarios, int hora, int dia, Curso curso){
        horarios.get(hora).set(dia,curso);
        return horarios;
    }

    public int asignarDia(String dia,int indexdia){
        switch (dia) {
            case "lun":
                indexdia=1;
                break;
            case "mar":
                indexdia=2;
                break;
            case "mie":
                indexdia=3;
                break;
            case "jue":
                indexdia=4;
                break;
            case "vie":
                indexdia=5;
                break;
            default:
                break;
        }
        return indexdia;
    }

    public boolean chequearDisponibilidad(ArrayList<ArrayList<Curso>> horarios,int hora, int dia, int periodos, int alum){
        boolean check = true;
        if(alum>20){check=false;}
        for(int i=0;i<periodos;i++){
            if(horarios.get(hora+i).get(dia).getCode() != "[    ]"){check=false;}
        }
        return(check);
    }
    
}
