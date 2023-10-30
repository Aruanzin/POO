package Modelo;

public class Fogo extends PersonagemMovel{
            
	private static final long serialVersionUID = -1085753963596546867L;
	
	public Fogo(int positionX, int positionY, int lado) {
        super(lado,21);
        this.bMortal = true;
        this.ladoVirado = lado;
        this.setPosicao(positionX, positionY);
    }

    
    
}
