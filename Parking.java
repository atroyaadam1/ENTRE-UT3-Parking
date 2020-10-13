/**
 *  
 * La clase representa a un parking de una ciudad europea
 * que dispone de dos tarifas de aparcamiento para los clientes
 * que lo usen: la tarifa regular (que incluye una tarifa plana para
 * entradas "tempranas") y la tarifa comercial para clientes que trabajan
 * cerca del parking, aparcan un nº elevado de horas y se benefician de esta 
 * tarifa más económica
 * (leer enunciado)
 * author : ANTHONNY TROYA 
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
    private double importeMaximoComercial;

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

    /** 
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
    public void setNombre (String queNomb) {
        nombre = queNomb;
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
        /** variables locales para horas y minutos de entrada y salida */

        int horaEntrada = entrada / 100;
        int minutosDeEntrada = entrada % 100;
        int horasDeSalida = salida / 100;
        int minutosDeSalida = salida % 100;
        int entradaMinutos = horaEntrada * 60 + minutosDeEntrada;
        int salidaMinutos = horasDeSalida * 60 + minutosDeSalida;

        /** variables locales con el valor sumatorio de entrada y salida */

        String Varible2 = horaEntrada + ":" + minutosDeEntrada;
        String Varible3 = horasDeSalida + ":" + minutosDeSalida;
        String minutosDeEntradaX;
        String minutosDeSalidaX;

        double imp = 0;
        String tar = "";
        if (minutosDeEntrada < 10) {
            minutosDeEntradaX = "0" + minutosDeEntrada;
        }
        else {
            minutosDeEntradaX = "" + minutosDeEntrada;
        }
        if (minutosDeSalida < 10) {
            minutosDeSalidaX ="0" + minutosDeSalida;
        }
        else {
            minutosDeSalidaX= "" + minutosDeSalida; 
        }

        switch (tipoTarifa) {
            case 'R':  if (entrada >= 600  && entrada < 830 &&  salida >= 1500 && salida < 1800){ 
                imp =  PRECIO_TARIFA_PLANA_REGULAR ;
                tar = "REGULAR y TEMPRANA";
            }
            else {
                if ( entrada > 1100 && salida > 1100){ 
                    imp += PRECIO_BASE_REGULAR + ((salidaMinutos - entradaMinutos) / 30) * PRECIO_MEDIA_REGULAR_DESPUES11;
                }
                else if (entrada < 1100 && salida < 1100) {
                    imp += PRECIO_BASE_REGULAR + ((salidaMinutos - entradaMinutos) / 30) * PRECIO_MEDIA_REGULAR_HASTA11;
                }
                else {
                    imp += PRECIO_BASE_REGULAR + PRECIO_MEDIA_REGULAR_HASTA11 * ( 11 * 60 - entradaMinutos ) /30 +  
                    (salidaMinutos - 11 * 60) / 30 *  PRECIO_MEDIA_REGULAR_DESPUES11; 
                } 
                tar = "REGULAR";
                regular ++;
                break;
            }
            case 'C': if ((salida - entrada) <= 300) {
                imp = PRECIO_PRIMERAS3_COMERCIAL; 
            }
            else { 
                imp = ((salidaMinutos - entradaMinutos - 180) / 30) *  PRECIO_MEDIA_COMERCIAL +  PRECIO_PRIMERAS3_COMERCIAL; 
            }
            tar ="COMERCIAL";
            comercial++;
            break;
        }
        importeTotal += imp;
        System.out.println( "*****************************" );
        System.out.println( "Cliente nº : " + cliente + ".");
        System.out.println("Hora de la entrada : " + Varible2 + "."); 
        System.out.println( "Hora de la salida : " + Varible3 + ".");
        System.out.println( "Tarifa a aplicar: " + tar + " . " );
        System.out.println( " Importe a pagar "   + imp + " € " );
        System.out.println( "*****************************" ) ;

        if (importeMaximoComercial < imp) {
            clienteMaximoComercial = cliente;
            importeMaximoComercial = imp;
        }

        switch (dia) {
            case 1: clientesLunes++; break;
            case 6: clientesSabado++; break;
            case 7: clientesDomingo++; break;
        }

    }

    /**
     * Muestra en pantalla las estadísticcas sobre el parking  
     *   
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        System.out.println("*********************************************************");
        System.out.println("Importe total entre todos los clientes facturados" + importeTotal + "€");
        System.out.println("Número de clientes en la tarifa regular: " + regular);
        System.out.println("Número de clientes de la tarifa comercial: " + comercial);
        System.out.println("Cliente tarifa Comercial con factura máxima fue el númer : " + clienteMaximoComercial);
        System.out.println("y págo" + importeMaximoComercial + "€");
        System.out.println("Cliente tarifa Comercial con factura" + clienteMaximoComercial + "pagó" + importeMaximoComercial);
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que más clientes han utilizado el parking - "SÁBADO"   "DOMINGO" o  "LUNES"
     */
    public String diaMayorNumeroClientes() {
        if (clientesSabado > clientesLunes && clientesSabado > clientesLunes){
            return "SABADO";
        }
        else if (clientesDomingo > clientesLunes){
            return "DOMINGO";
        }
        else {
            return "LUNES";
        }
    }
}

