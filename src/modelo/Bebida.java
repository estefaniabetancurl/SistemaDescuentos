package modelo;

public class Bebida extends Producto {
    private double volumenMl;

    public Bebida(String nombre, double precioBase, double volumenMl) {
        super(nombre, precioBase);
        this.volumenMl = volumenMl;
    }

    public double getVolumenMl() {
        return volumenMl;
    }

    @Override
    public String getTipo() {
        return "Bebida (" + volumenMl + " ml)";
    }
}