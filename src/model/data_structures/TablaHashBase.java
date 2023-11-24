package model.data_structures;

import java.text.DecimalFormat;

public abstract class TablaHashBase<K extends Comparable<K>, V extends Comparable<V>> implements ITablaSimbolos<K, V> {

    protected static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n > 1 && n <= 3) return true;

        if (n % 2 == 0 || n % 3 == 0) return false;

        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;

        return true;
    }

    protected static int nextPrime(int N) {
        if (N <= 1)
            return 2;

        int prime = N;
        boolean found = false;

        while (!found) {
            prime++;

            if (isPrime(prime))
                found = true;
        }
        return prime;
    }

    public String toString( int minicial, int tamanoTabla, int tamanoAct, int cantidadRehash) {
        String retorno = "";
        retorno += "La cantidad de duplas: " + keySet().size();
        retorno += "\nEl m inicial es: " + minicial;
        retorno += "\nEl m final es: " + tamanoTabla;
        double tam = tamanoAct;
        double tam2 = tamanoTabla;
        DecimalFormat df = new DecimalFormat("###.##");
        double tamanioCarga = tam / tam2;
        retorno += "\nEl factor de carga es: " + df.format(tamanioCarga);
        retorno += "\nLa cantidad de rehash es: " + cantidadRehash;

        return retorno;
    }


}
