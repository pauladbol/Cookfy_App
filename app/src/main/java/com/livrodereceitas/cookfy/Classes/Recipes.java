package com.livrodereceitas.cookfy.Classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Asus on 01/10/2016.
 */
public class Recipes implements Parcelable {
        private String id;
        private int drawableId;
        private String name;
        private String description;
        private String ingredientes;
        private String prepTime;
        private String difficulty;
        private String[] recipeBooks;

    public Recipes(String id, String nome, int imagem, String ingredientes, String description) {
        this.id = id;
        this.name = nome;
        this.drawableId = imagem;
        this.description = description;
        this.ingredientes = ingredientes;
    }

    public Recipes(){
    }


/*
        private String executionTime;
        private String difficulty;*/


    protected Recipes(Parcel in) {
        id = in.readString();
        drawableId = in.readInt();
        name = in.readString();
        description = in.readString();
        ingredientes = in.readString();
        prepTime = in.readString();
        difficulty = in.readString();
        recipeBooks = in.createStringArray();
    }

    public static final Creator<Recipes> CREATOR = new Creator<Recipes>() {
        @Override
        public Recipes createFromParcel(Parcel in) {
            return new Recipes(in);
        }

        @Override
        public Recipes[] newArray(int size) {
            return new Recipes[size];
        }
    };

    public String getId() {

        return id;
        }
    public void setId(String id) {

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
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        /*public String getExecutionTime() {
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

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getExecutionTime() {
        return prepTime;
    }

    public void setExecutionTime(String executionTime) {
        this.prepTime = executionTime;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String[] getRecipeBooks() {
        return recipeBooks;
    }

    public void setRecipeBooks(String[] recipeBooks) {
        this.recipeBooks = recipeBooks;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(drawableId);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(ingredientes);
        dest.writeString(prepTime);
        dest.writeString(difficulty);
        dest.writeStringArray(recipeBooks);
    }
}
