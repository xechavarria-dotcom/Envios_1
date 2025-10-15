package logica;

import java.util.ArrayList;
import java.util.List;
import modelos.Envio;

public class Logistica {
    private List<Envio> envios;

    public Logistica() {
        envios = new ArrayList<>();
    }

    // Agregar envío
    public void agregarEnvio(Envio envio) {
        envios.add(envio);
    }

    // Retirar envío por código
    public void eliminarEnvio(String codigo) {
        for (int i = 0; i < envios.size(); i++) {
            Envio e = envios.get(i);
            if (e.getCodigo().equals(codigo)) {
                envios.remove(i);
                break;
            }
        }
    }

    // Listar todos los envíos
    public List<Envio> listarEnvios() {
        return envios;
    }
}

