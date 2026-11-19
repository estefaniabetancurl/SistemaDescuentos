package vista;

import modelo.Bebida;
import modelo.Comida;
import modelo.Producto;
import modelo.ReglaDescuento;
import persistencia.ConexionBD;

import javax.swing.*;
import java.awt.*;

public class VentanaDescuentos extends JFrame {
    private JTextField txtMonto;
    private JButton btnCalcular;
    private JTextArea areaResultado;
    private ReglaDescuento reglaLambda;

    public VentanaDescuentos() {
        configurarVentana();
        inicializarLogicaLambda();
        iniciarConexionBD();
    }

    private void configurarVentana() {
        setTitle("Calculadora de Descuentos - POO II");
        setSize(450, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Panel superior (Entrada y Botón)
        JPanel panelEntrada = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panelEntrada.add(new JLabel("Monto total ($):"));
        
        txtMonto = new JTextField(12);
        panelEntrada.add(txtMonto);

        btnCalcular = new JButton("Calcular Descuento");
        panelEntrada.add(btnCalcular);

        // Área de texto central (Resultados y estado de BD)
        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(areaResultado);

        panelPrincipal.add(panelEntrada, BorderLayout.NORTH);
        panelPrincipal.add(scroll, BorderLayout.CENTER);
        add(panelPrincipal);

        // Evento del botón
        btnCalcular.addActionListener(e -> procesarCalculo());
    }

    private void inicializarLogicaLambda() {
        // Expresión Lambda: si el total > $10 aplica 15% de descuento, si no 0%
        reglaLambda = monto -> (monto > 10.0) ? monto * 0.85 : monto;
    }

    private void iniciarConexionBD() {
        String estadoBD = ConexionBD.probarConexion();
        areaResultado.append(estadoBD);
        areaResultado.append("--------------------------------------------------\n");
    }

    private void procesarCalculo() {
        try {
            double montoIngresado = Double.parseDouble(txtMonto.getText().trim());
            if (montoIngresado < 0) {
                areaResultado.append("⚠️ Ingrese un monto positivo.\n");
                return;
            }

            // Demostración de polimorfismo con las clases hijas
            Producto ejemploProducto = (montoIngresado > 10.0)
                    ? new Comida("Combo Especial", montoIngresado, false)
                    : new Bebida("Jugo Natural", montoIngresado, 350);

            double totalFinal = ejemploProducto.calcularPrecioFinal(reglaLambda);
            double ahorro = montoIngresado - totalFinal;

            areaResultado.append(String.format("Producto: %s | Tipo: %s\n", 
                    ejemploProducto.getNombre(), ejemploProducto.getTipo()));
            areaResultado.append(String.format("Monto base: $%.2f | Descuento: $%.2f\n", 
                    montoIngresado, ahorro));
            areaResultado.append(String.format("TOTAL A PAGAR: $%.2f\n\n", totalFinal));

        } catch (NumberFormatException ex) {
            areaResultado.append("⚠️ Error: Por favor ingrese un número válido.\n\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaDescuentos().setVisible(true));
    }
}