package modelo;

@FunctionalInterface
public interface ReglaDescuento {
    double aplicarDescuento(double precioBase);
}