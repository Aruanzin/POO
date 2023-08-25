/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atvaula;

/**
 *
 * @author 12609731
 */
public class Pessoa {
    private String nome;
    private String cpf;
    
    public Pessoa(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    
    public String getCpf(){
        return cpf;
    }
}
