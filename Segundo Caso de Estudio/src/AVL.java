public class AVL {
    NodoAVL raiz;

    public AVL() {
        this.raiz = null;
    }

    // Buscar: retorna el nodo si lo encuentra, si no null
    public NodoAVL buscar(int llave) {
        return buscarRec(this.raiz, llave);
    }

    private NodoAVL buscarRec(NodoAVL nodo, int llave) {
        if (nodo == null) return null;
        if (llave == nodo.llave) return nodo;
        if (llave < nodo.llave) return buscarRec(nodo.izquierda, llave);
        else return buscarRec(nodo.derecha, llave);
    }

    // Insertar (público)
    public void insertar(int llave) {
        this.raiz = insertarRec(this.raiz, llave);
    }

    // Insertar recursivo y reequilibrar
    private NodoAVL insertarRec(NodoAVL nodo, int llave) {
        if (nodo == null) return new NodoAVL(llave);

        if (llave < nodo.llave) nodo.izquierda = insertarRec(nodo.izquierda, llave);
        else if (llave > nodo.llave) nodo.derecha = insertarRec(nodo.derecha, llave);
        else return nodo; // llaves duplicadas no permitidas

        // actualizar altura
        nodo.altura = 1 + Math.max(NodoAVL.altura(nodo.izquierda), NodoAVL.altura(nodo.derecha));

        // chequear balance
        int balance = NodoAVL.factorBalance(nodo);

        // Caso LL
        if (balance > 1 && llave < nodo.izquierda.llave) {
            return NodoAVL.rotarDerecha(nodo);
        }

        // Caso RR
        if (balance < -1 && llave > nodo.derecha.llave) {
            return NodoAVL.rotarIzquierda(nodo);
        }

        // Caso LR
        if (balance > 1 && llave > nodo.izquierda.llave) {
            nodo.izquierda = NodoAVL.rotarIzquierda(nodo.izquierda);
            return NodoAVL.rotarDerecha(nodo);
        }

        // Caso RL
        if (balance < -1 && llave < nodo.derecha.llave) {
            nodo.derecha = NodoAVL.rotarDerecha(nodo.derecha);
            return NodoAVL.rotarIzquierda(nodo);
        }

        return nodo;
    }

    // Eliminar (público)
    public void eliminar(int llave) {
        this.raiz = eliminarRec(this.raiz, llave);
    }

    private NodoAVL eliminarRec(NodoAVL nodo, int llave) {
        if (nodo == null) return null;

        // BST deletion
        if (llave < nodo.llave) nodo.izquierda = eliminarRec(nodo.izquierda, llave);
        else if (llave > nodo.llave) nodo.derecha = eliminarRec(nodo.derecha, llave);
        else {
            // nodo encontrado
            if (nodo.izquierda == null || nodo.derecha == null) {
                NodoAVL temp = (nodo.izquierda != null) ? nodo.izquierda : nodo.derecha;
                if (temp == null) {
                    // sin hijos
                    nodo = null;
                } else {
                    // un hijo
                    nodo = temp;
                }
            } else {
                // dos hijos: obtener sucesor inorder (mínimo en subárbol derecho)
                NodoAVL temp = minValorNodo(nodo.derecha);
                nodo.llave = temp.llave;
                nodo.derecha = eliminarRec(nodo.derecha, temp.llave);
            }
        }

        if (nodo == null) return null;

        // actualizar altura
        nodo.altura = 1 + Math.max(NodoAVL.altura(nodo.izquierda), NodoAVL.altura(nodo.derecha));

        // chequear balance
        int balance = NodoAVL.factorBalance(nodo);

        // Rebalance según casos (simétrico a inserción)
        // Caso LL
        if (balance > 1 && NodoAVL.factorBalance(nodo.izquierda) >= 0) {
            return NodoAVL.rotarDerecha(nodo);
        }

        // Caso LR
        if (balance > 1 && NodoAVL.factorBalance(nodo.izquierda) < 0) {
            nodo.izquierda = NodoAVL.rotarIzquierda(nodo.izquierda);
            return NodoAVL.rotarDerecha(nodo);
        }

        // Caso RR
        if (balance < -1 && NodoAVL.factorBalance(nodo.derecha) <= 0) {
            return NodoAVL.rotarIzquierda(nodo);
        }

        // Caso RL
        if (balance < -1 && NodoAVL.factorBalance(nodo.derecha) > 0) {
            nodo.derecha = NodoAVL.rotarDerecha(nodo.derecha);
            return NodoAVL.rotarIzquierda(nodo);
        }

        return nodo;
    }

    private NodoAVL minValorNodo(NodoAVL nodo) {
        NodoAVL actual = nodo;
        while (actual.izquierda != null) actual = actual.izquierda;
        return actual;
    }

    // Recorridos: in-order
    public void recorridoEnOrden() {
        recorridoEnOrdenRec(this.raiz);
        System.out.println();
    }

    private void recorridoEnOrdenRec(NodoAVL nodo) {
        if (nodo != null) {
            recorridoEnOrdenRec(nodo.izquierda);
            System.out.print(nodo.llave + " ");
            recorridoEnOrdenRec(nodo.derecha);
        }
    }
}
