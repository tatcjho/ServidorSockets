/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor1;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author tatyj
 */
public class Servidor1 {

    /**
     * Puerto
     */
    private final static int PORT = 5000;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            //Socket de servidor para esperar peticiones de la red
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor> Servidor iniciado");
            System.out.println("Servidor> En espera de cliente...");
            //Socket de cliente
            Socket clientSocket;
            while (true) {
                //en espera de conexion, si existe la acepta
                clientSocket = serverSocket.accept();
                //Para leer lo que envie el cliente
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                //para imprimir datos de salida                
                PrintStream output = new PrintStream(clientSocket.getOutputStream());
                //se lee peticion del cliente
                String request = input.readLine();
                System.out.println("Cliente> petición [" + request + "]");
                //se procesa la peticion y se espera resultado
                String strOutput = process(request);
                //Se imprime en consola "servidor"
                System.out.println("Servidor> Resultado de petición");
                System.out.println("Servidor> \"" + strOutput + "\"");
                //se imprime en cliente
                output.flush();//vacia contenido
                output.println(strOutput);
                //cierra conexion
                clientSocket.close();
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * procesa peticion del cliente y retorna resultado
     *
     * @param request peticion del cliente
     * @return String
     */
    public static String process(String request) {
        //computadoras
        String[] compu = {
            "Asus Rog Strix Scar",
            "Razer Blade Stealth",
            "Acer Predator Helios 300",
            "Alienware 15",
            "HP Curved Envy AIO 34",
            "Apple iMac 27-inch",
            "Dell XPS 27",
            "HP Spectre x360 13 pulgadas (2017)"};
        ArrayList<String> compuList = new ArrayList<>();
        Collections.addAll(compuList, compu);
        //frases 

        //Universidades
        String[] universidades = {
            "Universidad Politecnica Salesiana",
            "Universidad del Azuay",
            "Universidad Estatal de Cuenca",
            "Universidad Espiritu Santo",
            "Universidad San Francisco de Quito",
            "UTPL ",
            "UPCE",
            "UNAE"};
        ArrayList<String> universidadesList = new ArrayList<>();
        Collections.addAll(universidadesList, universidades);
        String result = "";
        //frases
        String[] phrases = {
            "La tecnología se alimenta a si misma. La tecnología hace posible más tecnología.-Alvin Toffler.",
            "La tecnología es sólo una herramienta. En términos de llevar a los niños a trabajar juntos y motivarlos, el profesor es el más importante.-Bill Gates.",
            "La máquina tecnológicamente más eficiente que el hombre ha inventado es el libro.-Northrop Frye.",
            "Ya no hacen más los bugs como bunny - Olav Mjelde",
            "Un lenguaje de programación es de bajo nivel cuando requiere que prestes atencion a lo irrelevante.-Alan J. Perlis.",
            "Hablar es barato. Enséñame el código.-Linus Torvalds ",
            "No me importa si funciona en su máquina! No me envían su máquina!.-Vidiu Platon",
            "Siempre codifica como si la persona que finalmente mantendrá tu código fuera un psicópata violento que sabe dónde vives.-Martin Golding"};
        ArrayList<String> phrasesList = new ArrayList<>();
        Collections.addAll(phrasesList, phrases);
        //libros
        String[] books = {
            "Divina Comedia - Dante Alighieri",
            "Don Quijote de la Mancha - Miguel de Cervantes",
            "Cien años de soledad - Gabriel García Márquez",
            "Moby Dick - Herman Melville",
            "Ana Karenina - Lev Tolstói",
            "Eneida - Virgilio",
            "Otelo - William Shakespeare",
            "El viejo y el mar - Ernest Hemingway",
            "Orgullo y prejuicio - Jane Austen"};
        ArrayList<String> booksList = new ArrayList<>();
        Collections.addAll(booksList, books);

        if (request != null) {
            switch (request) {
                case "frase":
                    Collections.shuffle(phrasesList);
                    result = phrasesList.get(0);
                    break;
                case "libro":
                    Collections.shuffle(booksList);
                    result = booksList.get(0);
                    break;
                case "universidad":
                    Collections.shuffle(universidadesList);
                    result = universidadesList.get(0);
                    break;
                case "computadora":
                    Collections.shuffle(compuList);
                    result = compuList.get(0);
                    break;
                case "exit":
                    result = "Cerrando servidor, bye!!";
                    break;
                default:
                    result = "La peticion no se puede resolver.";
                    break;
            }
        }
        return result;
    }
    
}
