package Controler;

import Modelo.Hero;
import Modelo.Obstaculo;
import Modelo.Personagem;
import Modelo.Cobra;
import Modelo.Bau;
import Modelo.Caveira;
import Modelo.Vida;
import Modelo.ObstaculoMovel;

import Auxiliar.Consts;

public class FabricaPersonagem {
<<<<<<< HEAD
	public static Personagem criarPersonagem(char valor) {
		switch (valor) {
		case Consts.ARBUSTOVERMELHO:
			return new Obstaculo(5, 8);
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
		case Consts.MUROBRANCO_INTEIRO:
			return new Obstaculo(5, 10);
		case Consts.MUROBRANCO_METADE:
			return new Obstaculo(6, 11, 8, 16);
		case Consts.RIO:
			return new Obstaculo(6, 9);
		case Consts.CAVEIRA:
			return new Caveira();
		default:
			return null;
		}
	}
=======
    public static Personagem criarPersonagem(char valor) {
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
            case Consts.MUROBRANCO_INTEIRO:
            	return new Obstaculo(5, 10);
            case Consts.MUROBRANCO_METADE:
            	return new Obstaculo(6, 11);
            	case Consts.RIO:
                	return new Obstaculo(6, 9);
            	case Consts.CAVEIRA:
            		return new Caveira();
            default:
                return null; 
        }
    }
>>>>>>> 4fc98d404d595275a309b6496e5ea33ed9141fc9
}
