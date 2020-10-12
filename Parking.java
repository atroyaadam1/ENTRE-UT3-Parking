/**
 * La clase representa a un parking de una ciudad europea
 * que dispone de dos tarifas de aparcamiento para los clientes
 * que lo usen: la tarifa regular (que incluye una tarifa plana para
 * entradas "tempranas") y la tarifa comercial para clientes que trabajan
 * cerca del parking, aparcan un nº elevado de horas y se benefician de esta 
 * tarifa más económica
 * (leer enunciado)
 * 
 */
public class Parking
{
    private final char Regular = 'R';
    private final char Comercial = 'C';
    private final double PRECIO_BASE_REGULAR = 2.0;
    private final double PRECIO_MEDIA_REGULAR_HASTA11 = 3.0;
    private final double PRECIO_MEDIA_REGULAR_DESPUES11 = 5.0;
    private final int HORA_INICIO_ENTRADA_TEMPRANA = 6 * 60;
    private final int HORA_FIN_ENTRADA_TEMPRANA = 8 * 60 + 30;
    private final int HORA_INICIO_SALIDA_TEMPRANA = 18 * 60;
    private final double PRECIO_TARIFA_PLANA_REGULAR = 15.0;
    private final double PRECIO_PRIMERAS3_COMERCIAL = 5.00;
    private final double PRECIO_MEDIA_COMERCIAL = 3.00;
    private String nombre;
    private int cliente;
    private int importeTotal;
    private int regular;
    private int comercial;
    private int clientesLunes;
    private int clientesSabado;
    private int clientesDomingo;
    private int clienteMaximoComercial;
    private int importeMaximoComercial;

    /**
     * Inicializa el parking con el nombre indicada por el parámetro.
     * El resto de atributos se inicializan a 0 
     */
    public Parking(String queNombre) { 
        nombre = queNombre;
        cliente = 0;
        importeTotal = 0;
        regular = 0;
        comercial = 0;
        clientesLunes = 0;
        clientesSabado = 0;
        clientesDomingo = 0;
        clienteMaximoComercial = 0;
        importeMaximoComercial = 0;
    }

    /**s =
     * accesor para el nombre del parking
     *  
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * mutador para el nombre del parking
     *  
     */
    public void setNombre (String queCNombre) {
        nombre = queCNombre;
    }

    /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *    tipoTarifa - un carácter 'R' o 'C'
     *    entrada - hora de entrada al parking
     *    salida – hora de salida del parking
     *    dia – nº de día de la semana (un valor entre 1 y 7)
     *    
     *    A partir de estos parámetros el método debe calcular el importe
     *    a pagar por el cliente y mostrarlo en pantalla 
     *    y  actualizará adecuadamente el resto de atributos
     *    del parking para poder mostrar posteriormente (en otro método) 
     *    las estadísticas
     *   
     *    Por simplicidad consideraremos que un cliente entra y 
     *    sale en un mismo día
     *    
     *    (leer enunciado del ejercicio)
     */
    public void facturarCliente(char tipoTarifa, int entrada, 
    int salida, int dia) {
        cliente++;

        int horaEntrada = entrada / 100;
        int minutosDeEntrada = entrada % 100;
        int horasDeSalida = salida / 100;
        int minutosSalida = salida % 100;

        String Varible2 = horaEntrada + ":" + minutosDeEntrada;
        String Varible3 = horasDeSalida + ":" + minutosSalida;

        int temp = 15;
        int reg = (2 + (((salida - entrada ) / 50) * 5));
        int tresprimeras = 5;
        int cadMedia =(5+ ((((salida - entrada) - 300) / 50) * 3));

        double impor;

        /** se considera entrada/salida temprana entre 6:00 y las 8:30 y sale entre las 15:00
         * 18:00 tarifa plana de 15€ */

        /** importe = (salida - entrada) * 2 * Precio_primeras3_comercial**/

        switch (tipoTarifa){
            case 'R':  if (entrada >= 600  && entrada < 830 &&  salida >= 1500 && salida < 1800){ 
                System.out.println( "*****************************" );
                System.out.println( "Cliente nº : " + cliente + ".");
                System.out.println("Hora de la entrada: " + Varible2 + "."); 
                System.out.println( "Hora de la salida: " + Varible3 + ".");
                System.out.println( "Tarifa a aplicar: " + tipoTarifa + "." );
                System.out.println( "Importe a pagar"   + temp +"€" );
                System.out.println( "*****************************" ) ;
            }

            else if(entrada < 600 || entrada >= 830 && salida < 1500 || salida >= 1800) {
                System.out.println( "*****************************" );
                System.out.println( "Cliente nº : " + cliente );
                System.out.println("Hora de la entrada: " + Varible2); 
                System.out.println( "Hora de la salida: " + Varible3 );
                System.out.println( "Tarifa a aplicar: " + reg + "." );
                System.out.println( "Importe a pagar" + reg  + "€" );
                System.out.println( "*****************************" ) ;
            }

            break;
            case 'C': if ((salida - entrada) <= 300) {
                System.out.println( "*****************************" );
                System.out.println( "Cliente nº : " + cliente );
                System.out.println("Hora de la entrada: " + Varible2); 
                System.out.println( "Hora de la salida: " + Varible3 );
                System.out.println( "Tarifa a aplicar: " +  tipoTarifa);
                System.out.println( "Importe a pagar"  + tresprimeras + "€"  );
                System.out.println( "*****************************" ) ;
            }

            else if ((salida - entrada) > 300){
                System.out.println( "*****************************" );
                System.out.println( "Cliente nº : " + cliente );
                System.out.println("Hora de la entrada: " + Varible2); 
                System.out.println( "Hora de la salida: " + Varible3 );
                System.out.println( "Tarifa a aplicar: " + tipoTarifa );
                System.out.println( "Importe a pagar" + cadMedia + "€"  );
                System.out.println( "*****************************" ) ;
            }  
            break;
        }

    }

    /**
     * Muestra en pantalla las estadísticcas sobre el parking  
     *   
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        importeTotal = ;
        System.out.println("*********************************************************");
        System.out.println("Importe total entre todos los clientes facturados" + importeTotal);
        System.out.println("Número de clientes en la tarifa regular: " + regular);
        System.out.println("Número de clientes de la tarifa comercial: " + comercial);
        System.out.println("Cliente tarifa Comercial con factura" + clienteMaximoComercial + "pagó" + importeMaximoComercial);
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que más clientes han utilizado el parking - "SÁBADO"   "DOMINGO" o  "LUNES"
     */
    public String diaMayorNumeroClientes() {
        if (clientesSabado > clientesLunes && clientesSabado > clientesLunes){
            return "SABADO";}
        else if (clientesDomingo > clientesLunes){
            return "DOMINGO";
        }
        else {
            return "LUNES";
        }
    }
}

