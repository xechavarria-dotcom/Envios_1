package logica;

import modelos.*;

public class GestionEnvios {

    private GestionEnvios() { }

    public static Envio crearEnvio(TipoEnvio tipo, String codigo, String cliente, double peso, double distancia) {
        switch (tipo) {
            case TERRESTRE: return new Terrestre(codigo, cliente, peso, distancia);
            case MARITIMO:  return new Maritimo(codigo, cliente, peso, distancia);
            case AÉREO:     return new Aereo(codigo, cliente, peso, distancia);
            default: throw new IllegalArgumentException("Tipo de envío no válido.");
        }
    }
}
