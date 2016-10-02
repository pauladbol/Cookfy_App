package com.livrodereceitas.cookfy;

import java.util.UUID;

/**
 * Created by Asus on 01/10/2016.
 */
public class Recipes {
        private int id;

        private String name;

    public Recipes(int i, String s) {
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
