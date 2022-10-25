package Crud_Fourth_Solo.Crud_Fourth_Solo.RecipeModel.Controller;

import Crud_Fourth_Solo.Crud_Fourth_Solo.RecipeModel.Recipe;
import Crud_Fourth_Solo.Crud_Fourth_Solo.RecipeModel.Repo.RecipeRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Recipe")
public class RecipeController {

    RecipeRepo recipeRepo;

    public RecipeController(RecipeRepo recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    //<editor-fold desc="Post a Recipe">
    @PostMapping("")
    ResponseEntity<Recipe> postARecipe(@RequestBody Recipe recipe)
    {
        return new ResponseEntity<>(this.recipeRepo.save(recipe), HttpStatus.OK);
    }

    //</editor-fold>


    //<editor-fold desc="Get All Recipe">
    @GetMapping("")
    ResponseEntity<List<Recipe>> getAllRecipe()
    {
        return new ResponseEntity<>(this.recipeRepo.findAll(), HttpStatus.OK);
    }

    //</editor-fold>


    //<editor-fold desc="Get a Single Recipe">
    @GetMapping("{id}")
    ResponseEntity<Recipe> getSingleRecipe(@PathVariable Long id)
    {
        if (this.recipeRepo.findById(id).isPresent())
        {
            return new ResponseEntity<>(this.recipeRepo.findById(id).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.I_AM_A_TEAPOT);
    }
    //</editor-fold>

    //<editor-fold desc="Delete a Recipe">
    @DeleteMapping("{id}")
    ResponseEntity<Recipe> deleteARecipe(@PathVariable Long id)
    {
        if (this.recipeRepo.findById(id).isPresent())
        {
            this.recipeRepo.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    //</editor-fold>

    //<editor-fold desc="Patch A Recipe">
    @PatchMapping("{id}")
    ResponseEntity<Recipe> patchARecipe(@PathVariable Long id, @RequestBody Map<String, String> body)
    {
        //Dose User Exist
        if (this.recipeRepo.findById(id).isPresent())
        {
            Recipe holderRecipe = this.recipeRepo.findById(id).get(); // Create A Holder Of That Data
            for(Map.Entry<String, String> entry : body.entrySet()) //For Each Loop Through the Map
            {
                switch (entry.getKey()) {
                    case "DateCreated" -> holderRecipe.setDateCreated(LocalDate.parse(entry.getValue()));
                }
            }
            return new ResponseEntity<>(this.recipeRepo.save(holderRecipe), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    //</editor-fold>
}
