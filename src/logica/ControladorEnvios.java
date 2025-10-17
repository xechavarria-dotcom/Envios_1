package logica;

import javax.swing.JOptionPane;
import modelos.*;

public class ControladorEnvios {

    private LogisticaEnvios logistica;

    public ControladorEnvios(LogisticaEnvios logistica) {
        this.logistica = logistica;
    }

   
    public boolean agregarEnvio(String codigo, String cliente, String pesoStr, String distanciaStr, TipoEnvio tipo) {
        if (!ValidadorEnvio.camposCompletos(codigo, cliente, pesoStr, distanciaStr)) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Error",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!ValidadorEnvio.valoresNumericos(codigo, pesoStr, distanciaStr)) {
            JOptionPane.showMessageDialog(null, "Número, peso y distancia deben ser numéricos.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (tipo == null) {
            JOptionPane.showMessageDialog(null, "Seleccione un tipo de envío válido.", "Error",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        double peso = Double.parseDouble(pesoStr);
        double distancia = Double.parseDouble(distanciaStr);
        Envio envio = GestionEnvios.crearEnvio(tipo, codigo, cliente, peso, distancia);
        logistica.agregarEnvio(envio);

        return true;
    }

        public void eliminarEnvio(String codigo) {
            logistica.eliminarEnvio(codigo);
        }
    }


