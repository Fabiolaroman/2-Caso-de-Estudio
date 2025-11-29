import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AVL arbol = new AVL();
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 5) {
            System.out.println("==== Árbol AVL - Menú ====");
            System.out.println("1. Insertar nodo");
            System.out.println("2. Buscar nodo");
            System.out.println("3. Eliminar nodo");
            System.out.println("4. Mostrar en orden");
            System.out.println("5. Salir");
            System.out.print("Ingrese opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Intente de nuevo.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la llave a insertar (entero): ");
                    try {
                        int llaveIns = Integer.parseInt(sc.nextLine());
                        arbol.insertar(llaveIns);
                        System.out.println("Insertado: " + llaveIns);
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida.");
                    }
                    break;
                case 2:
                    System.out.print("Ingrese la llave a buscar: ");
                    try {
                        int llaveBus = Integer.parseInt(sc.nextLine());
                        NodoAVL encontrado = arbol.buscar(llaveBus);
                        if (encontrado != null) {
                            System.out.println("Nodo encontrado: " + encontrado.llave + " (altura=" + encontrado.altura + ")");
                        } else {
                            System.out.println("Nodo no encontrado.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida.");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese la llave a eliminar: ");
                    try {
                        int llaveElim = Integer.parseInt(sc.nextLine());
                        arbol.eliminar(llaveElim);
                        System.out.println("Si existía, se eliminó: " + llaveElim);
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida.");
                    }
                    break;
                case 4:
                    System.out.println("Recorrido in-order:");
                    arbol.recorridoEnOrden();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        sc.close();
    }
}
