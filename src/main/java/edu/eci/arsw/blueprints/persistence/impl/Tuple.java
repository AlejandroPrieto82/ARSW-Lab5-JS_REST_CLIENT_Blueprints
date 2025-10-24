package edu.eci.arsw.blueprints.persistence.impl;

import java.util.Objects;

/**
 * Clase utilitaria que representa una tupla genérica (par ordenado)
 * compuesta por dos elementos de tipos arbitrarios.
 *
 * @param <T1> tipo del primer elemento
 * @param <T2> tipo del segundo elemento
 */
public class Tuple<T1, T2> {

    /** Primer elemento de la tupla. */
    T1 o1;

    /** Segundo elemento de la tupla. */
    T2 o2;

    /**
     * Construye una nueva tupla con los dos elementos dados.
     *
     * @param o1 primer elemento
     * @param o2 segundo elemento
     */
    public Tuple(T1 o1, T2 o2) {
        super();
        this.o1 = o1;
        this.o2 = o2;
    }

    /**
     * Retorna el primer elemento de la tupla.
     *
     * @return primer elemento
     */
    public T1 getElem1() {
        return o1;
    }

    /**
     * Retorna el segundo elemento de la tupla.
     *
     * @return segundo elemento
     */
    public T2 getElem2() {
        return o2;
    }

    /**
     * Genera un código hash para la tupla, basado en sus dos elementos.
     *
     * @return código hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.o1);
        hash = 17 * hash + Objects.hashCode(this.o2);
        return hash;
    }

    /**
     * Compara esta tupla con otra, verificando que ambos
     * elementos sean iguales.
     *
     * @param obj objeto a comparar
     * @return true si ambas tuplas son equivalentes; false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tuple<?, ?> other = (Tuple<?, ?>) obj;
        if (!Objects.equals(this.o1, other.o1)) {
            return false;
        }
        if (!Objects.equals(this.o2, other.o2)) {
            return false;
        }
        return true;
    }
}
