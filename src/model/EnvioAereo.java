package model;

public class EnvioAereo extends Envio {
    public EnvioAereo(String numero, String cliente, double distancia, double peso) {
        super(numero, cliente, distancia, peso, "Aéreo");
    }

    @Override
    public double calcularCosto() {
        return (distancia * 1.0) + (peso * 5000);
    }
}
