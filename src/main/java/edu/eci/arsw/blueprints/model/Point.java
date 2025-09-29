package edu.eci.arsw.blueprints.model;

import java.util.Objects;

/**
 * Representa un punto en el plano con coordenadas cartesianas {@code (x, y)}.
 *
 * Ejemplo:
 * <pre>
 *     Point p = new Point(5, 10);
 * </pre>
 * 
 * @author Alejandro Prieto
 */
public class Point {

    private int x;
    private int y;

    /**
     * Crea un nuevo {@code Point} con coordenadas específicas.
     *
     * @param x coordenada X.
     * @param y coordenada Y.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor vacío necesario para librerías de serialización (como Jackson).
     */
    public Point() {
    }

    /**
     * Obtiene la coordenada X.
     *
     * @return valor de X.
     */
    public int getX() {
        return x;
    }

    /**
     * Establece la coordenada X.
     *
     * @param x nuevo valor de X.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Obtiene la coordenada Y.
     *
     * @return valor de Y.
     */
    public int getY() {
        return y;
    }

    /**
     * Establece la coordenada Y.
     *
     * @param y nuevo valor de Y.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Compara este punto con otro para verificar igualdad.
     *
     * @param obj objeto a comparar.
     * @return {@code true} si las coordenadas son iguales.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final Point other = (Point) obj;
        return this.x == other.x && this.y == other.y;
    }

    /**
     * Calcula el valor hash del punto basado en sus coordenadas.
     *
     * @return valor hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Devuelve una representación en cadena del punto.
     *
     * @return representación en formato {@code "(x,y)"}.
     */
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
