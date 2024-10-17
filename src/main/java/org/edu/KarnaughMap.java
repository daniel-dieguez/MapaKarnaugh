package org.edu;

import java.util.List;

public class KarnaughMap {

    private final int[][] map; // Matriz que representará el mapa de Karnaugh
    private final int numVariables; // Número de variables booleanas

    // Constructor que inicializa el mapa de Karnaugh según el número de variables
    public KarnaughMap(int numVariables) {
        this.numVariables = numVariables;
        int size = (int) Math.pow(2, numVariables / 2 + numVariables % 2); // Tamaño de filas/columnas
        this.map = new int[size][size];
    }

    // Rellena el mapa con los minterms (donde la función booleana es 1)
    public void fillMap(List<Integer> minterms) {
        for (int minterm : minterms) {
            int row = getRow(minterm);
            int col = getColumn(minterm);
            map[row][col] = 1;
        }
    }

    // Determina la fila en el mapa según el minterm
    private int getRow(int minterm) {
        return switch (numVariables) {
            case 2 -> (minterm & 1); // Para 2 variables, solo se usan los últimos bits
            case 3, 4 -> (minterm >> 1) & 1; // Para 3 o 4 variables, usamos la codificación adecuada
            default -> throw new IllegalArgumentException("Número de variables no soportado");
        };
    }

    // Determina la columna en el mapa según el minterm
    private int getColumn(int minterm) {
        return switch (numVariables) {
            case 2 -> (minterm >> 1) & 1; // Para 2 variables, el bit más significativo determina la columna
            case 3, 4 -> minterm & 3; // Para 3 o 4 variables, usamos los bits correspondientes
            default -> throw new IllegalArgumentException("Número de variables no soportado");
        };
    }

    // Muestra el mapa de Karnaugh en consola
    public void displayMap() {
        System.out.println("Mapa de Karnaugh:");
        for (int[] row : map) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    // Lógica básica de simplificación (implementación simple que detecta grupos de unos)
    public String simplify() {
        StringBuilder result = new StringBuilder("F = ");
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col] == 1) {
                    result.append(termFor(row, col)).append(" + ");
                }
            }
        }
        if (result.length() > 4) {
            result.setLength(result.length() - 3); // Elimina el último " + "
        }
        return result.toString();
    }

    // Método que convierte una posición del mapa en un término booleano
    private String termFor(int row, int col) {
        String term = "";
        if (numVariables >= 2) {
            term += (row == 0 ? "A'" : "A");
            term += (col == 0 ? "B'" : "B");
        }
        return term;
    }

}
