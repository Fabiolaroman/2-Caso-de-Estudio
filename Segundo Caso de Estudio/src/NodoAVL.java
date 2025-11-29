public class NodoAVL {
    int llave;
    int altura;
    NodoAVL izquierda;
    NodoAVL derecha;

    public NodoAVL(int llave) {
        this.llave = llave;
        this.altura = 1; // nodo hoja tiene altura 1
        this.izquierda = null;
        this.derecha = null;
    }

    // altura de un nodo (null -> 0)
    public static int altura(NodoAVL n) {
        return (n == null) ? 0 : n.altura;
    }

    // factor de balance: altura(izquierda) - altura(derecha)
    public static int factorBalance(NodoAVL n) {
        if (n == null) return 0;
        return altura(n.izquierda) - altura(n.derecha);
    }

    // Rotación simple a la derecha
    public static NodoAVL rotarDerecha(NodoAVL y) {
        NodoAVL x = y.izquierda;
        NodoAVL T2 = x.derecha;

        // rotación
        x.derecha = y;
        y.izquierda = T2;

        // actualiza alturas
        y.altura = Math.max(altura(y.izquierda), altura(y.derecha)) + 1;
        x.altura = Math.max(altura(x.izquierda), altura(x.derecha)) + 1;

        // nueva raíz
        return x;
    }

    // Rotación simple a la izquierda
    public static NodoAVL rotarIzquierda(NodoAVL x) {
        NodoAVL y = x.derecha;
        NodoAVL T2 = y.izquierda;

        // rotación
        y.izquierda = x;
        x.derecha = T2;

        // actualizar alturas
        x.altura = Math.max(altura(x.izquierda), altura(x.derecha)) + 1;
        y.altura = Math.max(altura(y.izquierda), altura(y.derecha)) + 1;

        // nueva raíz
        return y;
    }

    // Rotación doble izquierda-derecha (LR): primero rotar izquierda en hijo izquierdo, luego rotar derecha en nodo
    public static NodoAVL rotarIzquierdaDerecha(NodoAVL nodo) {
        nodo.izquierda = rotarIzquierda(nodo.izquierda);
        return rotarDerecha(nodo);
    }

    // Rotación doble derecha-izquierda (RL): primero rotar derecha en hijo derecho, luego rotar izquierda en nodo
    public static NodoAVL rotarDerechaIzquierda(NodoAVL nodo) {
        nodo.derecha = rotarDerecha(nodo.derecha);
        return rotarIzquierda(nodo);
    }
}
