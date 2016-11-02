package com.livrodereceitas.cookfy.Classes;

/**
 * Created by Asus on 15/10/2016.
 */
public class Ingrediente {
    String nome;
    String medida;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    @Override
    public String toString(){
        return nome;
    }
}
