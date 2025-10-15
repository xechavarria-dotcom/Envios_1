package model;

public abstract class Envio {
    protected String numero;
    protected String cliente;
    protected double distancia;
    protected double peso;
    protected String tipo;

    public Envio(String numero, String cliente, double distancia, double peso, String tipo) {
        this.numero = numero;
        this.cliente = cliente;
        this.distancia = distancia;
        this.peso = peso;
        this.tipo = tipo;
    }

    public abstract double calcularCosto();

    public String getNumero() { return numero; }
    public String getCliente() { return cliente; }
    public double getDistancia() { return distancia; }
    public double getPeso() { return peso; }
    public String getTipo() { return tipo; }
}
