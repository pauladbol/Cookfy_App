package com.livrodereceitas.cookfy.Classes;

/**
 * Created by Thiago on 13/11/2016.
 */
public class RecipeStep {
    private Integer stepOrder;
    private String description;

    public Integer getStepOrder() {
        return stepOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStepOrder(Integer stepOrder) {
        this.stepOrder = stepOrder;
    }
}

