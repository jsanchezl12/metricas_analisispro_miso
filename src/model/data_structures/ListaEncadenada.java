package model.data_structures;

public class ListaEncadenada<T extends Comparable<T>> implements ILista<T> {

    private Nodo<T> first;

    private int size;

    private Nodo<T> last;

    private String nonEx = "No es válido el elemento ingresado";
    private String posEx = "La posición no es válida";
    private String emptyEx = "La lista está vacía";

    public ListaEncadenada() {
        first = null;
        last = null;
        size = 0;
    }

    public ListaEncadenada(T element) {
        first = new Nodo<>(element);
        last = first;
        size = 1;
    }

    //Siempre se llama a insert o a delete primero, esos métodos manejan los casos de que el elemento sea null,
    //isEmpty o que la posición no sea válida
    public void addFirst(T element) {
        Nodo<T> actual = new Nodo<>(element);
        actual.setNext(first);
        first = actual;
    }

    //Siempre se llama a insert o a delete primero, esos métodos manejan los casos de que el elemento sea null,
    //isEmpty o que la posición no sea válida
    public void addLast(T element) {
        Nodo<T> actual = new Nodo<>(element);
        last.setNext(actual);
        last = actual;
        actual.setNext(null);

    }

    public void addLastCola(T element) throws NullException {

        if (element == null) {
            throw new NullException(nonEx);
        } else {
            if (first == null) {
                Nodo<T> actual = new Nodo<>(element);
                last = actual;
                first = actual;
            } else {
                Nodo<T> actual = new Nodo<>(element);
                last.setNext(actual);
                last = actual;
                actual.setNext(null);
            }
            size++;
        }
    }

    public void insertElement(T elemento, int pos) throws PosException, NullException {
        Nodo<T> nuevo = new Nodo<>(elemento);

        if (pos < 1 || pos - 1 > size) {
            throw new PosException(posEx);
        } else if (elemento == null) {
            throw new NullException(nonEx);
        } else {
            if (isEmpty()) {
                first = nuevo;
                last = first;
            } else if (pos == 1) {
                this.addFirst(elemento);
            } else if (pos == size + 1) {
                this.addLast(elemento);
            } else {
                Nodo<T> actual = first;
                for (int i = 0; i < pos - 2; i++) {
                    actual = actual.getNext();
                }
                nuevo.setNext(actual.getNext());
                actual.setNext(nuevo);
            }
        }
        size++;
    }

    //Siempre se llama a insert o a delete primero, esos métodos manejan los casos de que el elemento sea null,
    //isEmpty o que la posición no sea válida
    public T removeFirst() throws VacioException {
        T primero = firstElement();
        if (first != null) {
            first = first.getNext();
        }

        return primero;

    }

    //Siempre se llama a insert o a delete primero, esos métodos manejan los casos de que el elemento sea null,
    //isEmpty o que la posición no sea válida
    public T removeLast() {
        Nodo<T> penultimo = first;
        while (penultimo.getNext().getNext() != null) {
            penultimo = penultimo.getNext();
        }
        Nodo<T> ultimo = penultimo.getNext();

        penultimo.disconnectNext(penultimo);
        last = penultimo;

        return ultimo.getInfo();

    }

    public T removeLastPila() throws VacioException {
        Nodo<T> ultimo = null;
        if (isEmpty()) {
            throw new VacioException(emptyEx);
        } else if (first.getNext() != null) {
            if (first.getNext().getNext() != null) {
                Nodo<T> penultimo = first;

                while (penultimo.getNext().getNext() != null) {
                    penultimo = penultimo.getNext();
                }
                ultimo = penultimo.getNext();

                penultimo.disconnectNext(penultimo);
                last = penultimo;

                size--;
            } else {
                Nodo<T> penultimo = first;
                ultimo = penultimo.getNext();
                penultimo.disconnectNext(penultimo);
                last = penultimo;
                size--;
            }
        } else {
            ultimo = first;
            first = null;
        }
        return ultimo.getInfo();
    }

    public T deleteElement(int pos) throws PosException, VacioException {
        if (pos < 1 || pos > size) {
            throw new PosException(posEx);
        }

        if (isEmpty()) {
            throw new VacioException(emptyEx);
        }

        T retorno;

        if (pos == 1) {
            retorno = removeFirst();
        } else if (pos == size()) {
            retorno = removeLast();
        } else {
            Nodo<T> actual = first;
            Nodo<T> anterior = null;

            for (int i = 1; i < pos; i++) {
                anterior = actual;
                actual = actual.getNext();
            }

            retorno = actual.getInfo();
            anterior.disconnectNext(anterior);
        }
        size--;
        return retorno;
    }


    public T firstElement() throws VacioException {
        if (isEmpty()) {
            throw new VacioException(emptyEx);
        } else {
            return first.getInfo();
        }
    }

    public T lastElement() {
        if (isEmpty()) {
            return null;
        } else {
            return last.getInfo();
        }

    }

    public T getElement(int pos) throws PosException, VacioException {
        if (pos < 1 || pos > size) {
            throw new PosException(posEx);
        } else if (isEmpty()) {
            throw new VacioException(emptyEx);
        } else {
            Nodo<T> actual = first;

            for (int i = 0; i < pos - 1; i++) {
                actual = actual.getNext();
            }
            return actual.getInfo();
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int isPresent(T element) throws VacioException, NullException, PosException {
        int pos = -1;
        if (element == null) {
            throw new NullException(nonEx);
        } else if (isEmpty()) {
            throw new VacioException(emptyEx);
        } else {
            boolean end = false;
            for (int i = 0; i < size && !end; i++) {
                if (getElement(i).equals(element)) {
                    pos = i;
                    end = true;
                }
            }
        }

        return pos + 1;
    }

    private Nodo<T> getNodeAtPosition(int pos) throws PosException {
        Nodo<T> actual = first;
        for (int i = 1; i < pos; i++) {
            if (actual == null) {
                throw new PosException(posEx);
            }
            actual = actual.getNext();
        }
        return actual;
    }
    public void exchange(int pos1, int pos2) throws PosException, VacioException {
        if (pos1 < 1 || pos1 > size || pos2 < 1 || pos2 > size) {
            throw new PosException(posEx);
        } else if (isEmpty() || pos1 == pos2) {
            return;  // No hay cambios necesarios
        }
        Nodo<T> actual1 = getNodeAtPosition(pos1);
        Nodo<T> actual2 = getNodeAtPosition(pos2);
        T tempInfo = actual1.getInfo();
        actual1.change(actual2.getInfo());
        actual2.change(tempInfo);
    }

    public void changeInfo(int pos, T element) throws PosException, VacioException, NullException {
        if (pos < 1 || pos > size) {
            throw new PosException(posEx);
        } else if (isEmpty()) {
            throw new VacioException(emptyEx);
        } else if (element == null) {
            throw new NullException(nonEx);
        } else {
            Nodo<T> actual = first;
            for (int i = 0; i < pos - 1; i++) {
                actual = actual.getNext();
            }
            actual.change(element);
        }
    }

    public ILista<T> sublista(int pos, int numElementos) throws PosException, VacioException, NullException {
        if (isEmpty()) {
            throw new VacioException(emptyEx);
        } else if (numElementos < 0) {
            throw new PosException("La cantidad de elementos no es válida");
        } else if (numElementos >= size()) {
            return this;
        } else {
            ILista<T> copia = new ListaEncadenada<>();
            int contador = pos;
            for (int i = 0; i < numElementos; i++) {
                copia.insertElement(this.getElement(contador), i + 1);
                contador++;
            }
            return copia;
        }
    }

    @Override
    public int compareTo(ILista o) {
        return 0;
    }
}
