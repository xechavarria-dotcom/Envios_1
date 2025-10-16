package logica;

import modelos.*;

public class GestionEnvios {

    private GestionEnvios() { }

    public static Envio crearEnvio(String tipo, String codigo, String cliente, double peso, double distancia) {
        switch (tipo) {
            case "Terrestre":
                return new Terrestre(codigo, cliente, peso, distancia);
            case "Maritimo":
                return new Maritimo(codigo, cliente, peso, distancia);
            case "Aéreo":
                return new Aereo(codigo, cliente, peso, distancia);
            default:
                throw new IllegalArgumentException("Tipo de envío no válido.");
        }
    }
}
