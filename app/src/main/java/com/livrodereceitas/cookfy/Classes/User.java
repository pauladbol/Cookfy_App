package com.livrodereceitas.cookfy.Classes;

import java.io.Serializable;

/**
 * Created by pauladbol on 2016-10-29.
 */
public class User implements Serializable{
    private int id;
    private String nome;
    private String email;
    private String username;
    private String hash;
    private String adapter;
    private byte[] imagem = null;
    public User () {

    }

    public User(int id, String nome, String email, String username, String hash, String adapter, byte[] imagem) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.username = username;
        this.hash = hash;
        this.adapter = adapter;
        this.imagem = imagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAdapter() {
        return adapter;
    }

    public void setAdapter(String adapter) {
        this.adapter = adapter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHash() {
        return hash;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
