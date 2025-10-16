package logica;

public class ValidadorEnvio {
    public static boolean camposCompletos(String codigo, String cliente, String peso, String distancia) {
    String[] campos = {codigo, cliente, peso, distancia};
    
    for (String campo : campos) {
        if (campo.trim().isEmpty()) {
            return false; 
        }
    }
    
    return true;
}
    public static boolean valoresNumericos(String codigo, String peso, String distancia) {
        try {
            Double.parseDouble(peso);
            Double.parseDouble(distancia);
            Integer.parseInt(codigo);
            return true;
        } catch (NumberFormatException error) {
            return false;
        }
    }

    public static boolean tipoValido(int index) {
        return index != 0; 
    }
}