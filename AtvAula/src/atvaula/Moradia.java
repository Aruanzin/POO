/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atvaula;

/**
 *
 * @author 12609731
 */
public abstract class Moradia {
    private String rua;
    private int num;
    private Pessoa morador;
    
    public Moradia(String rua, int num, Pessoa p1){
        this.rua = rua;
        this.num = num;
        this.morador = p1;
    }
    
    public void imprimeEnd(){
        System.out.println("Endereço: " + rua + " " + num);
    }
    
    public void imprimeMorador(){
        System.out.println("Morador: " + morador.getNome() + " cpf: " + morador.getCpf());
    }
}
