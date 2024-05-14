package Peluqueria;

import Peluqueria.Peluquera;
import java.util.LinkedList;
import java.util.Queue;

public class Peluqueria {

    private static final int numPeluqueras = 3;
    private static final int numClientes = 10;

    private Queue<Cliente> colaClientes = new LinkedList<>();
    Object lock = new Object();

    private Thread[] peluqueras;

    public Peluqueria() {
        peluqueras = new Thread[numPeluqueras];
        for (int i = 0; i < numPeluqueras; i++) {
            peluqueras[i] = new Thread(new Peluquera(this, "Peluquera" + (i + 1)));
        }
    }

    public void iniciar() {
        for (Thread peluquera : peluqueras) {
            peluquera.start();
        }

        for (int k = 1; k <= numClientes; k++) {
            Cliente cliente = new Cliente("cliente " + k);
            agregarCliente(cliente);

        }
    }

    public void agregarCliente(Cliente cliente) {
        synchronized (lock) {
            colaClientes.offer(cliente);

            lock.notify();

        }
    }

    // El método syncronized sirve para sincronizar bloques de código que usen
    // hilos, lo cual quiere decir que solo un hilo puede acceder una vez a un
    // bloque de código, sirve para evitar que dos bloques de hilos accedan
    // simultaneamente al bloque de codigo o en este caso a un mismo cliente
    public Cliente obtenerCliente() throws InterruptedException {
        synchronized (lock) {
            while (colaClientes.isEmpty()) {
                // Wait es un método de la clase java.lang.Object que se utiliza en programación
                // multihilo para suspender la ejecución de un hilo hasta que se cumpla cierta
                // condición

                lock.wait();
            }
            return colaClientes.poll();
        }

    }

}
