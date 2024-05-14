package Peluqueria;

import java.util.Random;

class Peluquera implements Runnable {
    private final Peluqueria peluqueria;
    private final String nombrePeluquera;

    public Peluquera(Peluqueria peluqueria, String nombrePeluquera) {
        this.peluqueria = peluqueria;
        this.nombrePeluquera = nombrePeluquera;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            while (true) {
                // Obtener un cliente de la cola
                Cliente cliente = peluqueria.obtenerCliente();

                // Si el cliente es "FIN", terminar el bucle y finalizar el hilo
                if (cliente.getName().equals("FIN")) {
                    break;
                }

                // Simular la atención al cliente
                System.out.println(nombrePeluquera + " comienza a atender a " + cliente.getName());
                // Generar el tiempo simulado
                int tiempoAtencion = random.nextInt(10) + 1;
                // Tiempo de atención aleatorio entre 1 y 10 segundos
                Thread.sleep(tiempoAtencion * 1000);
                // Simular el tiempo de atención
                System.out.println(nombrePeluquera + " ha terminado de atender a " + cliente.getName() + " en "
                        + tiempoAtencion + " segundos");
            }
        } catch (InterruptedException e) {
            System.out.println("Error en el hilo");
        }
    }
}
