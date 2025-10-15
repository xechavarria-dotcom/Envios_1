package model;

public class EnvioFluvial extends Envio {
    public EnvioFluvial(String numero, String cliente, double distancia, double peso) {
        super(numero, cliente, distancia, peso, "Fluvial");
    }

    @Override
    public double calcularCosto() {
        return (distancia * 0.7) + (peso * 3000);
    }
}
