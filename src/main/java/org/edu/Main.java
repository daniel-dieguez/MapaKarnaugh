package org.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        System.out.println("Hello world!");

        Scanner scanner = new Scanner(System.in);

        // Pedir número de variables
        System.out.println("Ingrese el número de variables (2, 3 o 4): ");
        int numVariables = scanner.nextInt();

        // Validar número de variables (solo soportamos hasta 4 en esta implementación)
        if (numVariables < 2 || numVariables > 4) {
            System.out.println("Este programa solo soporta de 2 a 4 variables.");
            return;
        }

        KarnaughMap kMap = new KarnaughMap(numVariables);

        // Pedir los minterms (donde la función es 1)
        System.out.println("Ingrese los minterms (separados por espacios): ");
        scanner.nextLine(); // Limpiar el buffer de entrada
        String mintermsInput = scanner.nextLine();
        List<Integer> minterms = parseMinterms(mintermsInput);

        // Llenar el mapa con los minterms
        kMap.fillMap(minterms);

        // Mostrar el mapa de Karnaugh
        kMap.displayMap();

        // Simplificar la expresión booleana
        String simplifiedExpression = kMap.simplify();
        System.out.println("Expresión simplificada: " + simplifiedExpression);
    }

    // Método que convierte la entrada de minterms en una lista de enteros
    private static List<Integer> parseMinterms(String input) {
        List<Integer> minterms = new ArrayList<>();
        for (String s : input.split(" ")) {
            minterms.add(Integer.parseInt(s));
        }
        return minterms;


    }
}