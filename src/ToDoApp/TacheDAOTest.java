package ToDoApp;

import Exceptions.TacheException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TacheDAOTest {
    private TacheDAO tacheDAO;

    public TacheDAOTest() {
        tacheDAO = new TacheDAO();
    }

    /**
     * Test de l'ajout d'une tâche à la BDD
     */
    @Test
    void testAdd() {
        try{

            Tache t = new Tache(0,"Unit Test"); ///On crée une tâche avec l'ID 0
            tacheDAO.add(t); //Lors de l'ajout d'une tâche à la BDD, on remplace l'ID par celui de la BDD
            assertNotSame(0,t.getId()); //Forcément différent de 0 car Primary Key en Auto Increment
        }catch (TacheException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test si une chaîne >255 caractères est refusée
     */

    @Test
    void testLongString(){
        try {
            Tache t = new Tache(0,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer imperdiet leo a magna tincidunt sagittis. Morbi sit amet pretium enim. Quisque maximus eros a congue viverra. Maecenas in eros scelerisque, pulvinar velit in, finibus arcu. Pellentesque sed. Quisque maximus eros a congue viverra.");
        }catch (TacheException e){
            assertEquals("Erreur : message supérieur à 255 caractères",e.getMessage());
        }
    }

    @Test
    void testEmptyMessage(){
        try {
            Tache t = new Tache(0,"");
        }catch (TacheException e){
            assertEquals("Message vide",e.getMessage());
        }
    }

    @Test
    void testNullObject(){
        try {
            Tache t = new Tache(0,null);
        }catch (TacheException e){
            assertEquals("Message vide",e.getMessage());
        }
    }

    @Test
    void testSpecialCaracters(){
        try {
            Tache t = new Tache(0,"\uD83D\uDE1D\uD83E\uDD20\uD83E\uDD21\uD83E\uDD27\uD83D\uDE44\uD83D\uDE2E\uD83D\uDE36 Let'\\\\xe2\\\\x80\\\\x99s also rethink how\\\\nwe care for ourselves.");
        }catch (TacheException e){
            assertEquals("Message vide",e.getMessage());
        }
    }
}