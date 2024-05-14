package Peluqueria;

import java.util.Random;

public class Peluquera implements Runnable {
    // Esta es la clase que implementa runable

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
                Cliente cliente = peluqueria.obtenerCliente();
                System.out.println(nombrePeluquera + " comienza a anteder a " + cliente.getName());
                int tiempoAtencion = random.nextInt(10) + 1;
                Thread.sleep(tiempoAtencion * 1000);
                System.out.println(nombrePeluquera + " a terminado de antender a " + cliente.getName() + " en "
                        + tiempoAtencion + " segundos");
            }
        } catch (InterruptedException e) {
            System.out.println("Error en el hilo");
        }
    }

}
