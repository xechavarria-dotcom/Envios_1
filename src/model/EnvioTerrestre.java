package model;

public class EnvioTerrestre extends Envio {
    public EnvioTerrestre(String numero, String cliente, double distancia, double peso) {
        super(numero, cliente, distancia, peso, "Terrestre");
    }

    @Override
    public double calcularCosto() {
        return (distancia * 0.5) + (peso * 2000);
    }
}
