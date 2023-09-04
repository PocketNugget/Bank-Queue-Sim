import java.util.LinkedList;
import java.util.Queue;

public class BankSim {

    private static final double LAMBDA = 5;
    private static final int num_cajas = 4;
    private static final int tiempoMin = 1;
    private static final int tiempoMax = 10;
    private static Cajero[] cajeros = new Cajero[num_cajas];

    public static void main(String[] args) throws InterruptedException {
        Queue<Cliente> clienteQ = new LinkedList<>();
        for (int i = 0; i < num_cajas; i++) {
            cajeros[i] = new Cajero(cajeros); // Pasar cajeros como argumento
        }

        for (int i = 0; i < 8; i++) {
            int arrivals = poissonRandom(LAMBDA);
            System.out.println("En una hora " + (i + 1) + ", " + arrivals + " clientes llegaron.");

            for (int j = 0; j < arrivals; j++) {
                clienteQ.add(new Cliente(j + 1, i + 1));
            }
            System.out.println("Caja | Cliente | Minutos");
            while (!clienteQ.isEmpty()) {


                for (Cajero cajero : cajeros) {
                    if (cajero.isAvailable() && !clienteQ.isEmpty()) {
                        Cliente cliente = clienteQ.poll();
                        int espera = getRandomServiceTime();
                        cajero.addCliente(cliente, espera);


                        System.out.println("  " + (cajero.getIndex() + 1)  + "  |    " + cliente.getId() + "    |    " + espera);

                    }
                }

                for (Cajero cajero : cajeros) { //va contando el tiempo del cliente hasta que llegue a 0 y cambie cliente
                    cajero.clienteServ();
                }
            }

            System.out.println("Clientes atendidos por cajero:");
            for (int k = 0; k < num_cajas; k++) {
                System.out.println("Cajero " + (k + 1) + ": " + cajeros[k].getQueueSize() + " Clientes");
            }
            System.out.println("--------------------------");
        }
    }

    public static int poissonRandom(double lambda) {
        int k = 0;
        double p = 1.0;
        double expLambda = Math.exp(-lambda);

        do {
            k++;
            p *= Math.random();
        } while (p >= expLambda);

        return k - 1;
    }

    public static int getRandomServiceTime() {
        return tiempoMin + (int) (Math.random() * (tiempoMax - tiempoMin + 1));
    }
}
