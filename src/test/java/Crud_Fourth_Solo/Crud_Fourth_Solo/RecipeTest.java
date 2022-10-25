package Crud_Fourth_Solo.Crud_Fourth_Solo;

import Crud_Fourth_Solo.Crud_Fourth_Solo.RecipeModel.Recipe;
import Crud_Fourth_Solo.Crud_Fourth_Solo.RecipeModel.Repo.RecipeRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RecipeTest
{
    //<editor-fold desc="Variables">
    @Autowired
    RecipeRepo recipeRepo;

    @Autowired
    MockMvc mvc;

ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
    Recipe recipeOne;
    Recipe recipeTwo;
    Recipe recipeThree;
    //</editor-fold>

    //<editor-fold desc="Before Each">
    @BeforeEach
    void init()
    {
        recipeOne = new Recipe();
        recipeTwo = new Recipe();
        recipeThree = new Recipe();

        recipeOne.setDescription("Bake a Cake");
        recipeOne.setInstructions("Shove it in the over");
        recipeOne.setTitle("NickCake");
        recipeOne.setCalories(1000);
        recipeOne.setDateCreated(LocalDate.of(1955,5,5));
        recipeTwo.setDescription("Bake a Pie");
        recipeTwo.setInstructions("Be Nice To It");
        recipeTwo.setTitle("NickPie");
        recipeTwo.setCalories(1000);
        recipeTwo.setDateCreated(LocalDate.of(1955,5,5));
        recipeThree.setDescription("Cook some Soup");
        recipeThree.setInstructions("Cook Over Stove");
        recipeThree.setTitle("NickSoup");
        recipeThree.setCalories(1000);
        recipeThree.setDateCreated(LocalDate.of(1955,5,5));

        this.recipeRepo.save(recipeOne);
        this.recipeRepo.save(recipeTwo);
        this.recipeRepo.save(recipeThree);

    }
    //</editor-fold>

    //<editor-fold desc="All Test Holder">


    //<editor-fold desc="Post Test">

    @Test
    @Transactional
    @Rollback
    void testPosting() throws Exception
    {
        Recipe recipePost = new Recipe();
        recipePost.setDescription("Cook some Soup");
        recipePost.setInstructions("Cook Over Stove");
        recipePost.setTitle("NickSoup");
        recipePost.setCalories(1000);
        recipePost.setDateCreated(LocalDate.of(1955,5,5));
        String json = mapper.writeValueAsString(recipePost);

        MockHttpServletRequestBuilder request = post("/Recipe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Description").value("Cook some Soup"))
                .andExpect(jsonPath("$.Instructions").value("Cook Over Stove"));
    }



    //</editor-fold>

    //<editor-fold desc="Get All Recipe">
    @Test
    @Transactional
    @Rollback
    void getAllRecipe() throws Exception
    {
        MockHttpServletRequestBuilder request = get("/Recipe");


        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].Description").value("Bake a Cake"))
                .andExpect(jsonPath("$[1].Description").value("Bake a Pie"))
                .andExpect(jsonPath("$[2].Description").value("Cook some Soup"));
    }

    //</editor-fold>

    //<editor-fold desc="Get A Single Recipe">

    @Test
    @Transactional
    @Rollback
    void testGetOneRecipe() throws Exception
    {
        MockHttpServletRequestBuilder request = get(String.format("/Recipe/%d",recipeTwo.getId()));
        MockHttpServletRequestBuilder requestNotFound = get("/Recipe/9999999999999");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Description").value("Bake a Pie"));

        this.mvc.perform(requestNotFound)
                .andExpect(status().isIAmATeapot());
    }

    //</editor-fold>

    //<editor-fold desc="Delete a Recipe">

    @Test
    @Transactional
    @Rollback
    void testDeletingARecipe() throws Exception
    {
        MockHttpServletRequestBuilder request = delete(String.format("/Recipe/%d",recipeTwo.getId()));

        this.mvc.perform(request)
                .andExpect(status().isOk());

        MockHttpServletRequestBuilder requestNotFound = delete(String.format("/Recipe/%d",recipeTwo.getId()));

        this.mvc.perform(request)
                .andExpect(status().isNotFound());

    }

    //</editor-fold>


    //<editor-fold desc="Patch a Recipe">
    @Test
    @Transactional
    @Rollback
    void testPatchingARecipe() throws Exception
    {
        Recipe recipePatch = new Recipe();
        recipePatch.setDateCreated(LocalDate.of(1999,12,6));
        String json = mapper.writeValueAsString(recipePatch);
        MockHttpServletRequestBuilder request = patch(String.format("/Recipe/%d",recipeTwo.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.DateCreated").value("1999-12-06"));

    }

    //</editor-fold>


    //</editor-fold>

}
