package Controler;

import Modelo.Hero;
import Modelo.Obstaculo;
import Modelo.Personagem;
import Modelo.Cobra;
import Modelo.Bau;
import Modelo.Vida;
import Modelo.ObstaculoMovel;

import Auxiliar.Consts;

public class FabricaPersonagem {
    public static Personagem criarPersonagem(int valor) {
        switch (valor) {
            case Consts.ARBUSTOVERMELHO:
                return new Obstaculo(5,8);
            case Consts.COBRA:
                return new Cobra();
            case Consts.BAU:
                return new Bau();
            case Consts.VIDA:
                return new Vida();
            case Consts.HEROI:
                return new Hero();
            case Consts.OBSTACULO_MOVEL:
                return new ObstaculoMovel();
            default:
                return null; 
        }
    }
}
