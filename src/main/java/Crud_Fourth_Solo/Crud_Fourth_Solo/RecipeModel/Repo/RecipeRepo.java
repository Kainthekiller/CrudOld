package Crud_Fourth_Solo.Crud_Fourth_Solo.RecipeModel.Repo;

import Crud_Fourth_Solo.Crud_Fourth_Solo.RecipeModel.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepo extends JpaRepository<Recipe, Long>
{
}
