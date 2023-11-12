package Controler;

import Modelo.Hero;
import Modelo.Obstaculo;
import Modelo.Personagem;
import Modelo.Rio;
import Modelo.Cobra;
import Modelo.DinoRosa;
import Modelo.Bau;
import Modelo.Caveira;
//import Modelo.Chao;
import Modelo.Vida;
import Modelo.Tatu;
import Modelo.ObstaculoMovel;

import Auxiliar.Consts;

public class FabricaPersonagem {
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
			return new Rio();
		case Consts.CAVEIRA:
			return new Caveira();
		case Consts.TATU:
			return new Tatu();
		case Consts.ARBUSTO_VERDE:
			return new Obstaculo(6,8);
//		case Consts.PONTE:
//			return new Chao(7,8);
		case Consts.DINO_ROSA:
			return new DinoRosa();		
		default:
			return null;
		}
	}
}
