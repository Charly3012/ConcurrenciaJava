package Peluqueria;

import Peluqueria.Peluquera;
import java.util.LinkedList;
import java.util.Queue;

class Peluqueria {
    private static final int numPeluqueras = 3;
    private static final int numClientes = 10;

    // Cola que almacena los clientes
    private Queue<Cliente> colaClientes = new LinkedList<>();
    // Objeto para la sincronización
    private final Object lock = new Object();
    // Arreglo para las peluqueras
    private Thread[] peluqueras;

    public Peluqueria() {
        peluqueras = new Thread[numPeluqueras];
        for (int i = 0; i < numPeluqueras; i++) {
            peluqueras[i] = new Thread(new Peluquera(this, "Peluquera" + (i + 1)));
        }
    }

    public void iniciar() {
        // Iniciar los hilos de las peluqueras
        for (Thread peluquera : peluqueras) {
            peluquera.start();
        }

        // Crear y agregar clientes a la cola
        for (int k = 1; k <= numClientes; k++) {
            Cliente cliente = new Cliente("cliente " + k);
            agregarCliente(cliente);
        }

        // Agregar clientes "FIN" para indicar a las peluqueras que deben detenerse
        // Usar un cliente especial como "FIN" es una manera sencilla de indicar a los
        // hilos que deben detenerse.
        for (int i = 0; i < numPeluqueras; i++) {
            agregarCliente(new Cliente("FIN"));
        }
    }

    public void agregarCliente(Cliente cliente) {
        synchronized (lock) {
            // Agregar el cliente a la cola
            colaClientes.offer(cliente);
            // Notificar a una peluquera que hay un nuevo cliente
            lock.notify();
        }
    }

    public Cliente obtenerCliente() throws InterruptedException {
        synchronized (lock) {
            // Esperar mientras la cola de clientes esté vacía
            while (colaClientes.isEmpty()) {
                lock.wait();
            }
            // Obtener y remover el cliente de la cola
            return colaClientes.poll();
        }
    }
}