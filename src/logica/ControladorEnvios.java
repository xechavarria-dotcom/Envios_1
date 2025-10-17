package logica;

import modelos.*;

public class ControladorEnvios {

    private LogisticaEnvios logistica;

    public ControladorEnvios(LogisticaEnvios logistica) {
        this.logistica = logistica;
    }

    public String agregarEnvio(String codigo, String cliente, String pesoStr, String distanciaStr, TipoEnvio tipo) {
        if (!ValidadorEnvio.camposCompletos(codigo, cliente, pesoStr, distanciaStr)) {
            return "Complete todos los campos.";
        }

        if (!ValidadorEnvio.valoresNumericos(codigo, pesoStr, distanciaStr)) {
            return "Número, peso y distancia deben ser numéricos.";
        }

        if (tipo == null) {
            return "Seleccione un tipo de envío válido.";
        }

        double peso = Double.parseDouble(pesoStr);
        double distancia = Double.parseDouble(distanciaStr);

        Envio envio = GestionEnvios.crearEnvio(tipo, codigo, cliente, peso, distancia);
        logistica.agregarEnvio(envio);

        return "OK"; 
    }

    public void eliminarEnvio(String codigo) {
        logistica.eliminarEnvio(codigo);
    }
}




