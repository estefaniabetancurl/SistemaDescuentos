package modelo;

public class Comida extends Producto {
    private boolean esVegetariana;

    public Comida(String nombre, double precioBase, boolean esVegetariana) {
        super(nombre, precioBase);
        this.esVegetariana = esVegetariana;
    }

    public boolean isEsVegetariana() {
        return esVegetariana;
    }

    @Override
    public String getTipo() {
        return esVegetariana ? "Comida Vegetariana" : "Comida Regular";
    }
}