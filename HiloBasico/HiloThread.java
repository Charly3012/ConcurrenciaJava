package HiloBasico;

import javax.management.RuntimeErrorException;

public class HiloThread extends Thread {

    // La finalidad de esta practica es crear dos blubles que se ejecuten de manera
    // simultanea
    public void run() {
        for (int i = 0; i <= 5; i++) {
            System.out.println("Hilo " + Thread.currentThread().getId() + ": " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("El hilo se ha interrumpido!");

            }
        }
    }

    public static void main(String[] args) {

        HiloThread hilo1 = new HiloThread();
        HiloThread hilo2 = new HiloThread();

        hilo1.start();
        hilo2.start();

    }

}
