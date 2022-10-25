package Crud_Fourth_Solo.Crud_Fourth_Solo.RecipeModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Recipe
{
//<editor-fold desc="Variables">

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("Recipe_ID")
    Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("Description")
    String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("Instructions")
    String instructions;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("Title")
    String title;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("Calories")
    int calories;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("DateCreated")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate dateCreated;
    //</editor-fold>
    //<editor-fold desc="Getters & Setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }
    //</editor-fold>
}
