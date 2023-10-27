package Controler;

import Modelo.Hero;
import Modelo.Obstaculo;
import Modelo.Personagem;
import Modelo.BichinhoVaiVemHorizontal;
import Modelo.Bau;
import Modelo.Vida;

import Auxiliar.Consts;

public class FabricaPersonagem {
    public static Personagem criarPersonagem(int valor) {
        switch (valor) {
            case Consts.ARBUSTOVERMELHO:
                return new Obstaculo(5,8);
            case Consts.COBRA:
                return new BichinhoVaiVemHorizontal();
            case Consts.BAU:
                return new Bau();
            case Consts.VIDA:
                return new Vida();
            case Consts.HEROI:
                return new Hero();
            default:
                return null; 
        }
    }
}
