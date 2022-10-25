package Crud_Fourth_Solo.Crud_Fourth_Solo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

//TODO	Create	| POST	| /recipe	      | “create” route   | Creates a recipe entry //
//TODO	Read	| GET	| /recipe/{id} | “show” route	   | Responds with a single recipe
//TODO	Update	| PATCH	| /recipe/{id} | “update” route | Updates attributes of the recipe
//TODO	Delete	| DELETE| /recipe/{id} | “delete” route  | Deletes the recipe at the id
//TODO	List	       | GET	| /recipe	      | “list” route        | Responds with a list of recipes
//TODO	Long	           id
//TODO	String 	       description
//TODO	String             instructions
//TODO	String             title
//TODO	int                   calories
//TODO	LocalDate        dateCreated

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
