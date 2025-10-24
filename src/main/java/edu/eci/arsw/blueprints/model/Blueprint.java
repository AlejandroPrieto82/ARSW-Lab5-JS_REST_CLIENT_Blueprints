package edu.eci.arsw.blueprints.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Representa un plano (Blueprint), compuesto por un autor, un nombre
 * y una lista de puntos {@link Point}.
 *
 * Un {@code Blueprint} puede contener múltiples {@link Point} que
 * representan coordenadas dentro del diseño.
 *
 * Ejemplo:
 * <pre>
 *     Point[] puntos = {new Point(10, 20), new Point(30, 40)};
 *     Blueprint plano = new Blueprint("Alejandro", "Casa", puntos);
 * </pre>
 * 
 * @author Alejandro Prieto
 */
public class Blueprint {

    private String author;
    private List<Point> points;
    private String name;

    /**
     * Crea un nuevo {@code Blueprint} con un autor, un nombre y una lista inicial de puntos.
     *
     * @param author nombre del autor del plano.
     * @param name   nombre del plano.
     * @param pnts   arreglo de puntos iniciales.
     */
    public Blueprint(String author, String name, Point[] pnts) {
        this.author = author;
        this.name = name;
        this.points = new ArrayList<>(Arrays.asList(pnts));
    }

    /**
     * Crea un nuevo {@code Blueprint} vacío (sin puntos).
     *
     * @param author nombre del autor.
     * @param name   nombre del plano.
     */
    public Blueprint(String author, String name) {
        this.author = author;
        this.name = name;
        this.points = new ArrayList<>();
    }

    /**
     * Constructor vacío necesario para librerías de serialización (como Jackson).
     */
    public Blueprint() {
        this.points = new ArrayList<>();
    }

    /**
     * Obtiene el nombre del plano.
     *
     * @return nombre del plano.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene el autor del plano.
     *
     * @return nombre del autor.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Obtiene los puntos que conforman el plano.
     *
     * @return lista de {@link Point}.
     */
    public List<Point> getPoints() {
        return points;
    }

    /**
     * Agrega un nuevo punto al plano.
     *
     * @param p punto a añadir.
     */
    public void addPoint(Point p) {
        this.points.add(p);
    }

    // Setters para que Jackson pueda deserializar

    /**
     * Establece el autor del plano.
     *
     * @param author nuevo autor.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Establece la lista de puntos del plano.
     *
     * @param points lista de puntos.
     */
    public void setPoints(List<Point> points) {
        this.points = points;
    }

    /**
     * Establece el nombre del plano.
     *
     * @param name nuevo nombre.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Devuelve una representación en cadena del plano.
     *
     * @return cadena con autor, nombre y puntos.
     */
    @Override
    public String toString() {
        return "Blueprint{" + "author=" + author + ", name=" + name + ", points=" + points + '}';
    }

    /**
     * Calcula el hash del plano usando autor, nombre y puntos.
     *
     * @return valor hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(author, name, points);
    }

    /**
     * Compara este plano con otro para verificar igualdad.
     *
     * @param obj objeto a comparar.
     * @return {@code true} si tienen el mismo autor, nombre y puntos.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final Blueprint other = (Blueprint) obj;
        return Objects.equals(this.author, other.author)
                && Objects.equals(this.name, other.name)
                && Objects.equals(this.points, other.points);
    }
}
