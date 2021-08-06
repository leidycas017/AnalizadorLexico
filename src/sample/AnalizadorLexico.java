/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;


/****************************************************************************
                         ANALIZADOR LÉXICO
 Fecha: 16/04/2021
 Autores:
 Leidy Castaño
 Omar Torres
 Carlos Pinto

 Asignatura: Teroría de lenguajes
 ****************************************************************************/

public class AnalizadorLexico {

    static ArrayList<String> linea = new ArrayList<String>();
    static LinkedList<String> lista = new LinkedList<String>();
    static StringBuilder textoBuilder = new StringBuilder();
    static StringBuilder contenidoLista = new StringBuilder();

    static char dato;
    static int codigoAscii;
    static int n = 0;
    static int aux1;
    static int longitud = 0;
    static String lineaA;
    static boolean palabraReservada = false;

    public AnalizadorLexico() {
        showWindows();
    }

    public static void showWindows() {
        String[] opciones = {"Archivo", "Lista de tokens", "Salir"};
        int opcion = 0;
        while (opcion == 0) {
            opcion = JOptionPane.showOptionDialog(null, textoBuilder.toString(),
                    "Analizador léxico", 0, JOptionPane.QUESTION_MESSAGE, null, opciones, "");
            if (opcion == 0) {
                loadDate();
                for (aux1 = 0; aux1 < linea.size(); aux1++) {
                    lineaA = linea.get(aux1);
                    evaluarCadena();
                    n = 0;
                }
                textoBuilder.append("\n");
            }

            while (opcion == 1) {
                opcion = JOptionPane.showOptionDialog(null, contenidoLista.toString(),
                        "Analizador Léxico", 0, JOptionPane.QUESTION_MESSAGE, null, opciones, "");
            }
        }
    }

    public static void inicializar() {
        n = 0;
        aux1 = -1;
        longitud = 0;
        linea.clear();
        lista.clear();
        textoBuilder.setLength(0);
        contenidoLista.setLength(0);
    }

    public static void loadDate() {
        String path = null;
        inicializar();
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            path = chooser.getSelectedFile().getAbsolutePath();
        }
        try {

            BufferedReader bf;
            bf = new BufferedReader(new FileReader(path));
            String bfRead;

            int i = 0;
            while ((bfRead = bf.readLine()) != null) {
                linea.add(bfRead);
                textoBuilder.append(i).append(". ").append(bfRead).append("\n");
                i++;

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se encontro archivo");
        }
    }

    public static String obtenerPalabra(String lineaA) {
        String nombre = "";
        if (dato == ' ') {
            n++;
            dato = lineaA.charAt(n);
        }

        if (dato != ' ') {

            while (!esSimbolo() && n < lineaA.length()) {

                dato = lineaA.charAt(n);
                nombre = nombre + dato;
                n++;
                if (n < lineaA.length()) {
                    dato = lineaA.charAt(n);
                }
            }
        }
        return nombre;
    }

    public static boolean esSimbolo(){
        if ((dato >= 32 && dato <= 35)
                ||(dato >= 37 && dato <= 47)
                || (dato >= 58 && dato <= 64)
                || (dato >= 91 && dato <= 94)
                || (dato == 96)
                || (dato >= 123 && dato <= 126)) {
              return true;
        }
        return false;
    }

    private static boolean evaluarOperador() {
        if (dato == ' ') {
            n++;
            dato = lineaA.charAt(n);
        }

        if (dato == '=') {

            if (n == lineaA.length() - 1) {
                lista.add("|Operador|=|---");
                n = lineaA.length();
            }else if (lineaA.charAt(n + 1) == ' ') {
                lista.add("|Operador|=|---");
                n++;
            }else if (lineaA.charAt(n + 1) == '+') {
                lista.add("|Operador|=+|---");
                n=n+2;
            }else if (lineaA.charAt(n + 1) == '-') {
                lista.add("|Operador|=-|---");
                n=n+2;
            } else {
                lista.add("|Operador|=|---");
                n++;
            }
            return true;
        }else

        if (dato == '+') {

            if (n == lineaA.length() - 1) {
                lista.add("|Operador|+|---");
                n = lineaA.length();
            }else if (lineaA.charAt(n + 1) == ' ') {
                lista.add("|Operador|+|---");
                n++;
            }else if (lineaA.charAt(n + 1) == '=') {
                lista.add("|Operador|+=|---");
                n=n+2;
            } else {
                lista.add("|Operador|+|---");
                n++;
            }
            return true;
        }else

        if (dato == '-') {
            if (n == lineaA.length() - 1) {
                lista.add("|Operador|-|---");
                n = lineaA.length();
            }else if (lineaA.charAt(n + 1) == ' ') {
                lista.add("|Operador|-|---");
                n++;
            }else if (lineaA.charAt(n + 1) == '=') {
                lista.add("|Operador|-=|---");
                n=n+2;
            } else {
                lista.add("|Operador|-|---");
                n++;
            }
            return true;
        }else
        if (dato == '*') {

            if (n == lineaA.length() - 1) {
                lista.add("|Operador|*|---");
                n = lineaA.length();
            }else if (lineaA.charAt(n + 1) == ' ') {
                lista.add("|Operador|*|---");
                n++;
            }else if (lineaA.charAt(n + 1) == '=') {
                lista.add("|Operador|*=|---");
                n=n+2;
            } else {
                lista.add("|Operador|*|---");
                n++;
            }
            return true;
        }else
        if (dato == '/') {

            if (n == lineaA.length() - 1) {
                lista.add("|Operador|/|---");
                n = lineaA.length();
            }else if (lineaA.charAt(n + 1) == ' ') {
                lista.add("|Operador|/|---");
                n++;
            }else if (lineaA.charAt(n + 1) == '=') {
                lista.add("|Operador|/=|---");
                n=n+2;
            } else {
                lista.add("|Operador|/|---");
                n++;
            }
            return true;
        }else

        if (dato == '%') {
            if (n == lineaA.length() - 1) {
                lista.add("|Operador|%|---");
                n = lineaA.length();
            }else if (lineaA.charAt(n + 1) == ' ') {
                lista.add("|Operador|%|---");
                n++;
            }else if (lineaA.charAt(n + 1) == '=') {
                lista.add("|Operador|%=|---");
                n=n+2;
            } else {
                lista.add("|Operador|%|---");
                n++;
            }
            return true;
        }
        return false;
    }

    private static boolean evaluarVarios() {

        if (dato == ' ') {
            n++;
            dato = lineaA.charAt(n);
        }
        // reconocer numero:
        if ((codigoAscii >= 48 && codigoAscii <= 57)) {
            String nombre = "";
            while ((codigoAscii >= 48 && codigoAscii <= 57)) {
                dato = lineaA.charAt(n);
                nombre = nombre + dato;
                n++;

                if (n < lineaA.length()) {
                    dato = lineaA.charAt(n);
                    codigoAscii = dato;
                }else{
                    break;
                }
            }
            lista.add("|Constante|" + nombre + "|---");

            return true;
        }else


        if ((dato == '(') || (dato == ')')) {
            lista.add("|Separador|" + dato+"|---");
            n++;
            return true;
        }else

        if ((dato == '{') || (dato == '}')) {
            lista.add("|Separador|" + dato +"|---");
            n++;
            return true;
        } else

        if (dato == ',') {
            lista.add("|Separador|,|---");
            n++;
            return true;
        }else

        if (dato == ';') {
            lista.add("|Separador|;|---");
            n++;
            return true;
        }else

        if (dato == '&') {
                if (n == lineaA.length() - 1) {
                    lista.add("|Error|&|---");
                    n = lineaA.length();
                }else if (lineaA.charAt(n + 1) == '&') {
                    lista.add("|Operador|&&|---");
                    n=n+2;
                } else {
                    lista.add("|Error|&|---");
                    n++;
                }
            return true;
        }else

        if (dato == 39) {
            if(lineaA.length() >= n+3){
                if (n == lineaA.length() - 1) {
                    lista.add("|ErrorChar|'|---");
                    n = lineaA.length();
                }else if (lineaA.charAt(n + 2) == 39) {
                    lista.add("|char|'"+ lineaA.charAt(n+1) +"'|---");
                    n=n+3;
                }
            }else{
                lista.add("|ErrorChar|'|---");
                n++;
            }

            return true;
        }else

        if (dato == '|') {
            if (n == lineaA.length() - 1) {
                lista.add("|Error|||---");
                n = lineaA.length();
            }else if (lineaA.charAt(n + 1) == '|') {
                lista.add("|Operador||||---");
                n=n+2;
            } else {
                lista.add("|Error|||---");
                n++;
            }
            return true;
        }else

        if (dato == '<') {

            if (n == lineaA.length() - 1) {
                lista.add("|Operador|<|---");
                n = lineaA.length();
            }else if (lineaA.charAt(n + 1) == '=') {
                lista.add("|Operador|<=|---");

                n=n+2;
            } else {
                lista.add("|Operador|<|---");
                n++;
            }
            return true;
        }else

        if (dato == '>') {
            if (n == lineaA.length() - 1) {
                lista.add("|Operador|>|---");
                n = lineaA.length();
            }else if (lineaA.charAt(n + 1) == '=') {
                lista.add("|Operador|>=|---");
                n=n+2;
            } else {
                lista.add("|Operador|>|---");
                n++;
            }
            return true;
        }
        return false;
    }


    private static void evaluarCadena() {
        String nombre = "";
        while (n < lineaA.length()) {
            if (dato == ' ') {
                n++;
            }
            dato = lineaA.charAt(n);
            codigoAscii = dato;

            if (evaluarVarios() || evaluarOperador()) {
            } else {
                nombre = obtenerPalabra(lineaA);
                if (nombre != "") {
                    if (analizarTipoVariable(nombre)) {
                        lista.add("|Tipo de variable|" + nombre +"|---");
                    } else if (analizarVariable(nombre)) {
                        lista.add("|variable|" + nombre + "|---");
                    }else if(palabraReservada) {
                    }else {
                            lista.add("|error|" + nombre + "|---");
                    }
                }else{
                    lista.add("|Simbolo no identificado|"+ dato +"|---");
                    n++;
                }
            }
            palabraReservada = false;
        }
        n++;


        contenidoLista.append("\n");
        for (int i = longitud; i < lista.size(); i++) {
            contenidoLista.append(lista.get(i)).append(" ");
        }
        longitud = lista.size();
    }

    public static boolean analizarTipoVariable(String nombre) {

        boolean def = true;

        int estado = 0;
        for (int i = 0; i < nombre.length(); i++) {

            char simbolo = nombre.charAt(i);
            switch (estado) {
                case 0:
                    if (simbolo == 'c') {
                        estado = 1;
                    } else if (simbolo == 'd') {
                        estado = 5;
                    } else if (simbolo == 'b') {
                        estado = 14;
                    } else if (simbolo == 'i') {
                        estado = 11;
                    } else {
                        estado = 21;
                        def = false;
                        i = nombre.length() + 1;
                    }//fin caso 0
                    break;
                case 1:
                    if (simbolo == 'h') {
                        estado = 2;
                    } else {
                        estado = 21;
                    }//fin caso 1
                    break;
                case 2:
                    if (simbolo == 'a') {
                        estado = 3;
                    } else {
                        estado = 21;
                    }//fin caso 2
                    break;
                case 3:
                    if (simbolo == 'r') {
                        estado = 22;
                        //i--;
                    } else {
                        estado = 21;
                        //i--;
                    }//fin caso 3
                    break;
                /*case 4:
                    if (simbolo == ' ') {
                        estado = 22;
                        i--;
                    } else {
                        estado = 21;
                        i--;
                    }//fin caso 4
                    break;
                 */
                case 5:
                    if (simbolo == 'o') {
                        estado = 6;
                    } else {
                        estado = 21;
                    }//fin  caso 5
                    break;
                case 6:
                    if (simbolo == 'u') {
                        estado = 7;
                    } else {
                        estado = 21;
                    }//fin caso 6
                    break;
                case 7:
                    if (simbolo == 'b') {
                        estado = 8;
                    } else {
                        estado = 21;
                    }//fin caso 7
                    break;
                case 8:
                    if (simbolo == 'l') {
                        estado = 9;
                    } else {
                        estado = 21;
                    }//fin caso 8
                    break;
                case 9:
                    if (simbolo == 'e') {
                        estado = 22;
                        //i--;
                    } else {
                        estado = 21;
                        //i--;
                    }//fin caso 9
                    break;
                /*case 10:
                    if (simbolo == ' ') {
                        estado = 22;
                        i--;
                    } else {
                        estado = 21;
                        i--;
                    }//fin caso 10
                    break;
                 */
                case 11:
                    if (simbolo == 'n') {
                        estado = 12;
                    } else {
                        estado = 21;
                    }//fin  caso 11
                    break;
                case 12:
                    if (simbolo == 't') {
                        estado = 22;
                        //i--;
                    } else {
                        estado = 21;
                        //i--;
                    }//fin caso 12
                    break;
                /*case 13:
                    if (simbolo == ' ') {
                        estado = 22;
                        i--;
                    } else {
                        estado = 21;
                        i--;
                    }//fin caso 13
                    break;
                 */
                case 14:
                    if (simbolo == 'o') {
                        estado = 15;
                    } else {
                        estado = 21;
                    }//fin caso 14
                    break;
                case 15:
                    if (simbolo == 'o') {
                        estado = 16;
                    } else {
                        estado = 21;
                    }//fin caso 15
                    break;
                case 16:
                    if (simbolo == 'l') {
                        estado = 17;
                    } else {
                        estado = 21;
                    }//fin caso 16
                    break;
                case 17:
                    if (simbolo == 'e') {
                        estado = 18;
                    } else {
                        estado = 21;
                    }//fin  caso 17
                    break;
                case 18:
                    if (simbolo == 'a') {
                        estado = 19;
                    } else {
                        estado = 21;
                    }//fin caso 18
                    break;
                case 19:
                    if (simbolo == 'n') {
                        estado = 22;
                        //i--;
                    } else {
                        estado = 21;
                        //i--;
                    }//fin caso 19
                    break;
                /*case 20:
                    if (simbolo == ' ') {
                        estado = 22;
                        i--;
                    } else {
                        estado = 21;
                        i--;
                    }//fin caso 20
                    break;
                 */
                case 21:
                    def = false;
                    //System.out.println("Tipo de variable incorrecto");
                    i = nombre.length() + 1;
                    //estado = 0;
                    break;
                case 22:  //tipo de variable aceptable
                    //System.out.println("Tipo de variable aceptable");

                    estado = 21;
                    i = nombre.length() + 1;

                    break;

            } //fin switch estado tipo de variable
        }//fin for  
        if (estado != 22) {
            def = false;
        }
        return def;

    }

    public static boolean analizarVariable(String nombre) {
        boolean def = true;

        int estado = 0;
        for (int i = 0; i < nombre.length(); i++) {

            char simbolo = nombre.charAt(i);
            int codigoAscii = simbolo;
            switch (estado) {
                case 0:
                    if (simbolo == 'c') {
                        estado = 1;
                    } else if (simbolo == 'd') {
                        estado = 5;
                    } else if (simbolo == 'b') {
                        estado = 14;
                    } else if (simbolo == 'i') {
                        estado = 11;
                    } else if (simbolo == 't') {
                        estado = 26;
                    } else if (simbolo == 'w') {
                        estado = 21;
                    } else if (simbolo == ' ') {
                        estado = 0;
                    } else if (codigoAscii >= 48 && codigoAscii <= 57) {
                        estado = 30;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                    }
                    //fin caso 0
                    break;
                case 1:
                    if (simbolo == 'h') {
                        estado = 2;
                    } else if (simbolo == ' ') {
                        estado = 33;
                        i--;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                    }
                    break;
                case 2:
                    if (simbolo == 'a') {
                        estado = 3;
                    } else if (simbolo == ' ') {
                        estado = 33;
                        i--;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                    }
                    break;
                case 3:
                    if (simbolo == 'r') {
                        estado = 33;
                    } else if (simbolo == ' ') {
                        estado = 33;
                        //i--;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                    }
                    break;
                case 4:
                    if (simbolo == ' ') {
                        estado = 32;
                        //System.out.println("Error palabra reservada char");
                        def = false;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                        //System.out.println("Error falta espacio o ingresó simbolo incorrecto");
                        def = false;
                    }
                    break;
                case 5:
                    if (simbolo == 'o') {
                        estado = 6;
                    } else if (simbolo == ' ') {
                        estado = 33;
                        i--;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                    }
                    break;
                case 6:
                    if (simbolo == 'u') {
                        estado = 7;
                    } else if (simbolo == ' ') {
                        estado = 33;
                        i--;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                    }
                    break;
                case 7:
                    if (simbolo == 'b') {
                        estado = 8;
                    } else if (simbolo == ' ') {
                        estado = 33;
                        i--;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                    }
                    break;
                case 8:
                    if (simbolo == 'l') {
                        estado = 9;
                    } else if (simbolo == ' ') {
                        estado = 33;
                        i--;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                    }
                    break;
                case 9:
                    if (simbolo == 'e') {
                        estado = 33;
                    } else if (simbolo == ' ') {
                        estado = 33;
                        i--;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                    }
                    break;
                case 10:
                    if (simbolo == ' ') {
                        estado = 32;
                        def = false;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                        def = false;
                    }
                    break;
                case 11:
                    if (simbolo == 'n') {
                        estado = 12;
                    } else if (simbolo == ' ') {
                        estado = 33;
                        i--;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                    }
                    break;
                case 12:
                    if (simbolo == 't') {
                        estado = 33;
                    } else if (simbolo == ' ') {
                        estado = 33;
                        i--;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                    }
                    break;
                case 13:
                    if (simbolo == ' ') {
                        estado = 32;
                        //System.out.println("Error palabra reservada int");
                        def = false;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                        //System.out.println("Error falta espacio o ingresó simbolo incorrecto");
                        def = false;
                    }
                    break;
                case 14:
                    if (simbolo == 'o') {
                        estado = 15;
                    } else if (simbolo == ' ') {
                        estado = 33;
                        i--;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                    }
                    break;
                case 15:
                    if (simbolo == 'o') {
                        estado = 16;
                    } else if (simbolo == ' ') {
                        estado = 33;
                        i--;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                    }
                    break;
                case 16:
                    if (simbolo == 'l') {
                        estado = 17;
                    } else if (simbolo == ' ') {
                        estado = 33;
                        i--;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                    }
                    break;
                case 17:
                    if (simbolo == 'e') {
                        estado = 18;
                    } else if (simbolo == ' ') {
                        estado = 33;
                        i--;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                    }
                    break;
                case 18:
                    if (simbolo == 'a') {
                        estado = 19;
                    } else if (simbolo == ' ') {
                        estado = 33;
                        i--;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                    }
                    break;
                case 19:
                    if (simbolo == 'n') {
                        estado = 33;
                    } else if (simbolo == ' ') {
                        estado = 33;
                        i--;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                    }
                    break;
                case 20:
                    if (simbolo == ' ') {
                        estado = 32;
                        //System.out.println("Error palabra reservada boolean");
                        def = false;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                        //System.out.println("Error falta espacio o ingresó simbolo incorrecto");
                        def = false;
                    }
                    break;
                case 21:
                    if (simbolo == 'h') {
                        estado = 22;
                    } else if (simbolo == ' ') {
                        estado = 33;
                        i--;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                    }
                    break;
                case 22:
                    if (simbolo == 'i') {
                        estado = 23;
                    } else if (simbolo == ' ') {
                        estado = 33;
                        i--;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                    }
                    break;
                case 23:
                    if (simbolo == 'l') {
                        estado = 24;
                    } else if (simbolo == ' ') {
                        estado = 33;
                        i--;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                    }
                    break;
                case 24:
                    if (simbolo == 'e') {
                        if(i==nombre.length()-1){
                            lista.add("|Palabra reservada|while|---");
                            palabraReservada = true;
                        }
                        estado = 33;
                    } else if (simbolo == ' ') {
                        estado = 33;
                        i--;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                    }
                    break;
                case 25:
                    if (simbolo == ' ') {
                        estado = 32;
                        //System.out.println("Error palabra reservada while");
                        def = false;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                        def = false;
                    }
                    break;
                case 26:
                    if (simbolo == 'r') {
                        estado = 27;
                    } else if (simbolo == ' ') {
                        estado = 33;
                        i--;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                    }
                    break;
                case 27:
                    if (simbolo == 'u') {
                        estado = 28;
                    } else if (simbolo == ' ') {
                        estado = 33;
                        i--;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                    }
                    break;
                case 28:
                    if (simbolo == 'e') {
                        if(i==nombre.length()-1){
                            lista.add("|Palabra reservada|true|---");
                            palabraReservada = true;
                        }
                        estado = 33;
                    } else if (simbolo == ' ') {
                        estado = 33;
                        i--;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                    }
                    break;
                case 29:
                    if (simbolo == ' ') {
                        estado = 32;
                        def = false;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                        def = false;
                    }
                    break;
                case 30:
                    def = false;
                    i = nombre.length() + 1;
                    estado = 0;
                    break;
                case 31:
                    if (simbolo == ' ') {
                        estado = 33;
                        i--;
                    } else if ((codigoAscii >= 65 && codigoAscii <= 90)
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (simbolo == '$' || simbolo == '_')) {
                        estado = 31;
                    } else {
                        estado = 32;
                    }
                    break;
                case 32:
                    i = nombre.length() + 1;
                    break;
                case 33:
                    estado = 32;
                    i = nombre.length() + 1;

                    break;

            }

        }

        if (estado == 33) {
            def = false;
        }

        return def;
    }
}

