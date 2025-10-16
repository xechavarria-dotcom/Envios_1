package logica;

import java.util.ArrayList;
import java.util.List;
import modelos.Envio;

public class Logistica {
    private List<Envio> envios;

    public Logistica() {
        envios = new ArrayList<Envio>();
    }

    public void agregarEnvio(Envio envio) {
        envios.add(envio);
    }

    public void eliminarEnvio(String codigo) {
        for (int i = 0; i < envios.size(); i++) {
            if (envios.get(i).getCodigo().equals(codigo)) {
                envios.remove(i);
                break;
            }
        }
    }

    public List<Envio> listarEnvios() {
        return envios;
    }
}


