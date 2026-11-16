package ec.edu.epn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //HashMap para productos en inventario: la clave es el codigo,
        // el valor son los objetos de tipo producto
        //Esto permite que se puedan guardar productos perecibles y electronicos.

        HashMap<String,Producto> inventario = new HashMap<>();

        //Producto que salieron del inventario
        ArrayList<Producto> bajas = new ArrayList<>();

        //Contador unico para formar los codigos de lo productos(PER-1,ELE-2)
        int contador = 1;
        int opcion = 0;

        //Menu que se repita hasta que el usuario elija salir

        while(opcion != 4) {
            System.out.println();
            System.out.println("1. Agregar producto");
            System.out.println("2. Retirar productos");
            System.out.println("3. Listar bajas");
            System.out.println("4. Salir");
            System.out.print("Ingrese su opcion:");

            //Todo lo que ingresa el usuario se lea como texto nextLine()
            //Coloquialmente dentro del casting, a la conversion de tipos a string con parse
            //pero formalmente eso es parsing
            //Uso parsing, solo para demostrarles que se usarlo
            opcion = Integer.parseInt(sc.nextLine());

            if (opcion == 1) {
                System.out.println("Tipo: (1 perecible, 2 electronico): ");
                int tipo = Integer.parseInt(sc.nextLine());
                System.out.println("Nombre: ");
                String nombre = sc.nextLine();
                System.out.println("Precio: ");
                double precio = Double.parseDouble(sc.nextLine());
                System.out.println("Stock: ");
                int stock = Integer.parseInt(sc.nextLine());


                //Variable del tipo padre: puede apuntar a cualquier de los hijos
                //Las variables locales, siempre se inicializan en java
                //Los objetos con null;
                Producto p = null;

                if (tipo == 1) {
                    System.out.println("Dias para vencer: ");
                    int dias = Integer.parseInt(sc.nextLine());
                    //Generar el codigo que se forma segun el tipo
                    p = new Perecible("PER-"+String.valueOf(contador), nombre, precio, stock, dias);
                }else if (tipo == 2){
                    System.out.println("Meses en bodega: ");
                    int meses = Integer.parseInt(sc.nextLine());
                    p = new Electronico("ELE-"+String.valueOf(contador),nombre, precio, stock, meses);
                }else{
                    System.out.println("Tipo invalido");
                }

                //Solo se guarda y avanza el contador su el producto quedo creado

                if(p != null){
                    //Hasmap<String, Producto>
                    inventario.put(p.getCodigo(),p);
                    contador++;
                    System.out.println("Codigo: "+ p.getPrecio()+ " |Valor: "+p.calcularValor());
                }


            }else if(opcion == 2){
                //Paso 1 : copiar a la lista de bajas los que deben retirarse
                for(Producto p : inventario.values()){
                    if(p.debeRetirarse()){
                        //agrego al arraylist de bajas
                        bajas.add(p);
                    }
                }
                //Paso 2: ya fuera del recorrido del HashMap, se borra por su codigo
                //Si un codigo ya no esta en el mapa , remove no hace nada
                for(Producto p: bajas){
                    inventario.remove(p.getCodigo());
                }
                System.out.println("Quedan: "+ inventario.size()+ "  productos en inventario");
            }else if (opcion == 3){
                //recorre las bajas y suma su valor
                //Cada producto calcula y se escribe según su propia clase(polimorfismo)
                double perdida = 0;
                for(Producto p : bajas){
                    System.out.println(p.describir()+ " |valor: "+p.calcularValor());
                    perdida = perdida+p.calcularValor();
                }
                System.out.println("Perdida total: "+ perdida);

            }else if(opcion != 4){
                System.out.println("Opcion invalida");
            }

        }

    }
}