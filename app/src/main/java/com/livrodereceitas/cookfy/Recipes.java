package com.livrodereceitas.cookfy;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Asus on 01/10/2016.
 */
public class Recipes implements Serializable {
        private int id;
        private  int drawableId;
        private String name;

    public Recipes(int id, String nome, int imagem) {
        this.id = id;
        this.name = nome;
        this.drawableId = imagem;
    }

    /*private String description;

        private String executionTime;
        private String difficulty;*/




    public int getId() {

        return id;
        }
    public void setId(int id) {

        this.id = id;
        }
    public String getName() {

        return name;
        }
    public void setName(String name) {

        this.name = name;
        }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public int getDrawableId() {
        return drawableId;
    }
    /*public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public String getExecutionTime() {
            return executionTime;
        }
        public void setExecutionTime(String executionTime) {
            this.executionTime = executionTime;
        }
        public String getDifficulty() {
            return difficulty;
        }
        public void setDifficulty(String difficulty) {
            this.difficulty = difficulty;
        }*/

}
