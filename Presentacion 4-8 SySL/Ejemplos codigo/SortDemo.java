// SortDemo.java — Ejemplo de ordenamiento nativo en Java
// Compilar: javac SortDemo.java
// Ejecutar: java SortDemo

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortDemo {

    // Clase auxiliar para demostrar sort de objetos
    static class Persona implements Comparable<Persona> {
        String nombre;
        int edad;

        Persona(String nombre, int edad) {
            this.nombre = nombre;
            this.edad = edad;
        }

        @Override
        public int compareTo(Persona otro) {
            return Integer.compare(this.edad, otro.edad);
        }

        @Override
        public String toString() {
            return nombre + "(" + edad + ")";
        }
    }

    public static void main(String[] args) {

        // ─── 1. Sort de primitivos → Dual-Pivot Quicksort ────────────
        System.out.println("\n=== Sort de primitivos (int[]) — Dual-Pivot Quicksort ===");

        int[] nums = { 5, 2, 8, 1, 9, 3, 7, 4, 6 };
        System.out.println("Antes:   " + Arrays.toString(nums));

        Arrays.sort(nums);   // ← Dual-Pivot Quicksort (inestable, O(n log n) prom.)
        System.out.println("Después: " + Arrays.toString(nums));

        // Sort parcial: solo elementos del índice 2 al 5 (exclusive)
        int[] arr = { 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        System.out.print("Parcial antes: " + Arrays.toString(arr));
        Arrays.sort(arr, 2, 6);  // sort arr[2..6)
        System.out.println(" → parcial [2,6): " + Arrays.toString(arr));

        // ─── 2. Sort de double[] ──────────────────────────────────────
        System.out.println("\n=== Sort de doubles ===");
        double[] floats = { 3.14, 1.41, 2.71, 1.73, 0.57 };
        System.out.println("Antes:   " + Arrays.toString(floats));
        Arrays.sort(floats);
        System.out.println("Después: " + Arrays.toString(floats));

        // ─── 3. Sort de objetos → TimSort ─────────────────────────────
        System.out.println("\n=== Sort de objetos (Integer[]) — TimSort (estable) ===");

        Integer[] objetos = { 5, 2, 8, 1, 9, 3, 7, 4, 6 };
        System.out.println("Antes:   " + Arrays.toString(objetos));
        Arrays.sort(objetos);  // ← TimSort (estable, O(n log n))
        System.out.println("Después: " + Arrays.toString(objetos));

        // Sort descendente con Comparator
        Arrays.sort(objetos, Comparator.reverseOrder());
        System.out.println("Desc:    " + Arrays.toString(objetos));

        // ─── 4. Sort de Strings ───────────────────────────────────────
        System.out.println("\n=== Sort de Strings ===");
        String[] palabras = { "banana", "apple", "cherry", "date", "elderberry" };
        System.out.println("Antes:   " + Arrays.toString(palabras));
        Arrays.sort(palabras);   // TimSort, orden lexicográfico natural
        System.out.println("Después: " + Arrays.toString(palabras));

        // Sort ignorando mayúsculas/minúsculas
        String[] mezclado = { "Banana", "apple", "Cherry", "Date" };
        Arrays.sort(mezclado, String.CASE_INSENSITIVE_ORDER);
        System.out.println("Case-insensitive: " + Arrays.toString(mezclado));

        // ─── 5. Sort de objetos personalizados ───────────────────────
        System.out.println("\n=== Sort de Personas (Comparable) ===");

        Persona[] personas = {
            new Persona("Carlos",  30),
            new Persona("Ana",     25),
            new Persona("Beatriz", 35),
            new Persona("Diego",   28),
            new Persona("Elena",   25),   // misma edad que Ana — test de estabilidad
        };

        System.out.println("Antes: " + Arrays.toString(personas));

        // TimSort es ESTABLE: Ana y Elena (edad 25) mantienen su orden relativo
        Arrays.sort(personas);  // usa compareTo → por edad
        System.out.println("Por edad (estable): " + Arrays.toString(personas));
        // Ana debe aparecer antes que Elena aunque tengan la misma edad

        // Sort con Comparator lambda (Java 8+)
        Arrays.sort(personas, (a, b) -> a.nombre.compareTo(b.nombre));
        System.out.println("Por nombre: " + Arrays.toString(personas));

        // ─── 6. Collections.sort() ────────────────────────────────────
        System.out.println("\n=== Collections.sort() — TimSort ===");

        List<Integer> lista = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3));
        System.out.println("Antes:   " + lista);
        Collections.sort(lista);   // también usa TimSort
        System.out.println("Después: " + lista);

        // ─── 7. List.sort() (Java 8+) ─────────────────────────────────
        List<String> lenguas = new ArrayList<>(Arrays.asList("Zig", "Java", "Python", "C", "Rust"));
        lenguas.sort(Comparator.comparingInt(String::length));
        System.out.println("Por longitud: " + lenguas);

        // ─── 8. Verificar si está ordenado ────────────────────────────
        System.out.println("\n=== Verificación ===");
        int[] sorted   = { 1, 2, 3, 4, 5 };
        int[] unsorted = { 3, 1, 4, 1, 5 };

        // Java no tiene un método nativo isSorted(), pero podemos verificar:
        System.out.println("¿[1,2,3,4,5] ordenado? " + isSorted(sorted));
        System.out.println("¿[3,1,4,1,5] ordenado? " + isSorted(unsorted));

        System.out.println("\n✓ Demo completado\n");
    }

    // Método auxiliar — Java no tiene isSorted() nativo en el estándar
    static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) return false;
        }
        return true;
    }
}
