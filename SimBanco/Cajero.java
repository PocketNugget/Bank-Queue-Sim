import java.util.LinkedList;
import java.util.Queue;

public class Cajero {
    private Queue<Cliente> clientesQ = new LinkedList<>();
    private int tiempoRest = 0;
    private Cajero[] cajeros;

    public Cajero(Cajero[] cajeros) {
        this.cajeros = cajeros;
    }

    public void addCliente(Cliente cliente, int tiempoRest) {
        clientesQ.add(cliente);
        if (this.tiempoRest == 0) {
            this.tiempoRest = tiempoRest;
        }
    }

    public int getQueueSize() {
        return clientesQ.size();
    }

    public boolean isAvailable() {
        return tiempoRest == 0;
    }

    public void clienteServ() {
        if (tiempoRest > 0) {
            tiempoRest--;
        }
    }

    public int getIndex() {
        for (int i = 0; i < cajeros.length; i++) {
            if (this == cajeros[i]) {
                return i;
            }
        }
        return -1;
    }
}
