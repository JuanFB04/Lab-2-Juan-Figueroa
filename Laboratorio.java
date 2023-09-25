/**
 * Universidad del Valle de Guatemala
 * Programación orientada a objetos
 * @author Juan Ignacio Figueroa, 23092
 * @fechaCreación: 17/09/23
 * @fechaModificación: 24/09/23
 */
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

    /**
     * @param horarios
     * @param hora
     * @param dia
     * @return
     */
    public ArrayList<ArrayList<Curso>> eliminarCurso(ArrayList<ArrayList<Curso>> horarios, int hora, int dia){
        horarios.get(hora).set(dia,new Curso("[    ]",new Profesor("")));
        return horarios;
    }


    /**
     * @param dia
     * @param indexdia
     * @return
     */
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

    
    /**
     * @param horarios
     * @param hora
     * @param dia
     * @param periodos
     * @param alum
     * @return
     */
    public boolean chequearDisponibilidad(ArrayList<ArrayList<Curso>> horarios,int hora, int dia, int periodos, int alum){
        boolean check = true;
        if(alum>20){check=false;}
        for(int i=0;i<periodos;i++){
            if(horarios.get(hora+i).get(dia).getCode() != "[    ]"){check=false;}
        }
        return(check);
    }

    /**
     * @param horarios
     * @param nomprof
     * @param dsemana
     */
    public void mostrarCursosProfesor(ArrayList<ArrayList<Curso>> horarios, String nomprof, String[] dsemana){
        ArrayList<Curso> cursosprof = new ArrayList<>();
        ArrayList<Integer> diasprof = new ArrayList<>();

        for(ArrayList<Curso> fila:horarios){
            int indexdia=0;
            for(Curso cursox: fila){
                if(nomprof.equals(cursox.getProfesor()) && cursosprof.contains(cursox)==false){
                    cursosprof.add(cursox);
                }
                if(nomprof.equals(cursox.getProfesor()) && diasprof.contains(indexdia)==false){
                    diasprof.add(indexdia);
                }
                indexdia++;
            }
        }
        System.out.println("\nDías en que el profesor está al frente del laboratorio:");
        for(int dia: diasprof){
            System.out.println(dsemana[dia]);
        }
        System.out.println("\nCursos que el profesor reparte en la semana:");
        for(Curso cursox: cursosprof){
            System.out.println(cursox.getCode()+" "+cursox.getNom()+": "+cursox.getPeriodos()+" períodos a la semana");
        }
    }


    /**
     * @param horarios
     * @param nomprof
     * @param totalperiodos
     */
    public void mostrarResponsabilidad(ArrayList<ArrayList<Curso>> horarios, String nomprof, int totalperiodos){
        int periodosresp=0;
        for(ArrayList<Curso> fila:horarios){
            for(Curso cursox: fila){
                if(nomprof.equals(cursox.getProfesor())){
                    periodosresp++;
                }
            }
        }
        System.out.printf("\nEl profesor está al frente del laboratorio %d veces",periodosresp);
        System.out.println("\nPorcentaje de responsabilidad = "+(periodosresp/totalperiodos)*100+ "%");
    }
}
