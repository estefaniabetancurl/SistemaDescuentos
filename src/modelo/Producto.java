package modelo;

public abstract class Producto {
    protected String nombre;
    protected double precioBase;

    public Producto(String nombre, double precioBase) {
        this.nombre = nombre;
        this.precioBase = precioBase;
    }

    // Recibe la estrategia de descuento sin modificar la clase (Principio Abierto/Cerrado)
    public double calcularPrecioFinal(ReglaDescuento regla) {
        return regla.aplicarDescuento(this.precioBase);
    }

    public abstract String getTipo();

    public String getNombre() { return nombre; }
    public double getPrecioBase() { return precioBase; }
}