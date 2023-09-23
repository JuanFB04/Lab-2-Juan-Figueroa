import java.util.ArrayList;
import java.util.Scanner;

public class CIT411 {
    public static void main(String[] args) {
        CIT411 salon = new CIT411();
        //Creación de matriz para horarios
        ArrayList<ArrayList<Curso>> horarios = new ArrayList<>();
        String dsemana[]= {"Hora  "," lun  ", " mar  ", " mie  ", " jue  ", " vie  "};
        String horas[]={"7:00  ","8:00  ","9:00  ","10:00 ","11:00 ","12:00 ","13:00 ","14:00 ","15:00 ","16:00 ","17:00 ","18:00 ","19:00 ","20:00 ","21:00 "};
        int opcion=0;
        
        horarios.add(new ArrayList<>());
        for(int i=0;i<=5;i++){
            horarios.get(0).add(new Curso(dsemana[i]));
        }

        for(int i=1;i<horas.length;i++){
            horarios.add(new ArrayList<>());
            horarios.get(i).add(new Curso(horas[i-1]));
            horarios.get(i).add(new Curso("[    ]"));
            horarios.get(i).add(new Curso("[    ]"));
            horarios.get(i).add(new Curso("[    ]"));
            horarios.get(i).add(new Curso("[    ]"));
            horarios.get(i).add(new Curso("[    ]"));
        }

        //Bucle para elegir función del programa
        Scanner scanner = new Scanner(System.in);
        Laboratorio laboratorio = new Laboratorio(10);
        System.out.println("Bienvenido al salón CIT411");
        do{
            System.out.println("\nElija un número de opción\n1. Asignar un curso");
            opcion=scanner.nextInt();
            switch (opcion) {
                case 1:
                    //Ingreso de datos del curso
                    scanner.nextLine();
                    System.out.println("Ingrese código de 6 dígitos del curso: (ejemplo: CC5010)");
                    String code = scanner.nextLine();
                    System.out.println("Ingrese nombre de la clase");
                    String nom = scanner.nextLine();
                    System.out.println("Ingrese cantidad de alumnos inscritos en el curso");
                    int alum = scanner.nextInt();
                    scanner.nextLine();
                    if(alum>laboratorio.getCapMax()){
                        System.out.println("Advertencia: La cantidad de alumnos inscritos es mayor a la del laboratorio");
                        scanner.nextLine();
                    }

                    //Ingreso de datos del profesor
                    System.out.println("\nDatos del profesor:");
                    System.out.println("Ingrese nombre del profesor:");
                    String nomprof = scanner.nextLine();
                    System.out.println("Ingrese el número de carnet del profesor: (ejemplo: 23045)");
                    int carnet = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Ingrese el correro electrónico del profesor");
                    String mail = scanner.nextLine();
                    System.out.println("Ingrese el número de teléfono del profesor");
                    String tel = scanner.nextLine();
                    Profesor profesor = new Profesor(nomprof,carnet,mail,tel);

                    //Ingreso de datos del espacio de la clase
                    ArrayList<Integer> datosHorario = salon.ingresarDatosHorario(horarios,laboratorio,scanner);
                    boolean check = laboratorio.chequearDisponibilidad(horarios,datosHorario.get(0),datosHorario.get(1),datosHorario.get(2),alum);
                    
                    //Manda a igresar los datos otra vez, en caso el espacio no esté libre
                    while(check==false){
                        System.out.println("\nLa clase no puede ser asignada en este horario debido a que al menos uno de los períodos esta ocupado por otra clase");
                        System.out.println("Ingrese un nuevo horario y asegúrese de que el espacio este libre");
                        scanner.nextLine();
                        datosHorario = salon.ingresarDatosHorario(horarios,laboratorio,scanner);
                        check = laboratorio.chequearDisponibilidad(horarios,datosHorario.get(0),datosHorario.get(1),datosHorario.get(2),alum);
                    }
                    System.out.println("Clase agregado exitosamente");
                    //Se agrega el curso al horario finalmente
                    Curso curso = new Curso(code, nom,datosHorario.get(2),alum,profesor);
                    for(int i=0;i<datosHorario.get(2);i++){
                        laboratorio.asignarCurso(horarios,datosHorario.get(0)+i,datosHorario.get(1),curso);
                    }

                    //Se agregan más clases en otro día si se solicita
                    System.out.println("\nPresione 1 si desea agregar más clases del MISMO curso, presione 0 si ya no desea agregar más");
                    int masCursos = scanner.nextInt();
                    scanner.nextLine();
                    while(masCursos!=0 && masCursos!=1){
                        System.out.println("Opción inválida, ingrese 1 o 0 porfavor");
                        masCursos = scanner.nextInt();
                        scanner.nextLine();
                    }
                    while(masCursos==1){
                        datosHorario = salon.ingresarDatosHorario(horarios,laboratorio,scanner);
                        check = laboratorio.chequearDisponibilidad(horarios,datosHorario.get(0),datosHorario.get(1),datosHorario.get(2),alum);
                    
                        while(check==false){
                            System.out.println("\nLa clase no puede ser asignada en este horario debido a que al menos uno de los períodos esta ocupado por otra clase");
                            System.out.println("Ingrese un nuevo horario y asegúrese de que el espacio este libre");
                            scanner.nextLine();
                            datosHorario = salon.ingresarDatosHorario(horarios,laboratorio,scanner);
                            check = laboratorio.chequearDisponibilidad(horarios,datosHorario.get(0),datosHorario.get(1),datosHorario.get(2),alum);
                        }
                        System.out.println("Clase agregado exitosamente");
                        for(int i=0;i<datosHorario.get(2);i++){
                            laboratorio.asignarCurso(horarios,datosHorario.get(0)+i,datosHorario.get(1),curso);
                        }
                        curso.setPeriodos(curso.getPeriodos()+datosHorario.get(2));

                        System.out.println("Presione 1 si desea agregar más clases del MISMO curso, presione 0 si ya no desea agregar más");
                        masCursos = scanner.nextInt();
                    }
                    break;

                case 2:
                    laboratorio.display(horarios);
                    System.out.println("Basandose en la disponibilidad del horario, elija el día en que desea agregar la clase");
                    System.out.println("Ingrese el día como aparece en el horario: (ejemplo: mie)");
                    String dia = scanner.nextLine();
                default:
                    System.out.println("Opción no válida, elija otra vez");
                    break;
            }
        }while(opcion!=6);


        laboratorio.display(horarios);

        scanner.close();
    }
    
    public ArrayList<Integer> ingresarDatosHorario(ArrayList<ArrayList<Curso>> horarios, Laboratorio laboratorio, Scanner scanner){
         laboratorio.display(horarios);
                    System.out.println("Basandose en la disponibilidad del horario, elija el día en que desea agregar la clase");
                    System.out.println("Ingrese el día como aparece en el horario: (ejemplo: mie)");
                    String dia = scanner.nextLine();
                    while(!"lun".equals(dia) && !"mar".equals(dia) && !"mie".equals(dia) && !"jue".equals(dia) && !"vie".equals(dia)){
                        System.out.println("Por favor ingresar un día válido");
                        dia = scanner.nextLine();
                    }
                    int indexdia=0;
                    indexdia=laboratorio.asignarDia(dia,indexdia);
                    System.out.println("Ingrese la hora, solo ingresando el primer número (ejemplo: 12)");
                    int indexhora = scanner.nextInt()-6;
                    System.out.println("Ingrese la cantidad de períodos de una hora que dura la clase:");
                    int periodos = scanner.nextInt();
                    scanner.nextLine();
                    ArrayList<Integer> datosHorario = new ArrayList<>();
                    datosHorario.add(indexhora);
                    datosHorario.add(indexdia);
                    datosHorario.add(periodos);

                    return datosHorario;
    }

}
