/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package executaRabbit;

import Util.ThreadHoraPopulcaoAlimentacao;

/**
 *
 * @author ronal
 */
public class RabbitLinux {

    private static void excutaRobo() {
        ThreadHoraPopulcaoAlimentacao hora = new ThreadHoraPopulcaoAlimentacao();
        hora.threadHoraPopulcao();
        System.out.println("Iniciado Rabbit.");
    }
    /**
     *
     */
    public static void main(String[] args) {

        excutaRobo();
    }
}
