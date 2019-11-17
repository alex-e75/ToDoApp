package ToDoApp;

import Exceptions.TacheException;
import com.mysql.jdbc.StringUtils;

public class Tache {
    private int id;
    private String libelle;

    public Tache(int id, String libelle) throws TacheException {
        if (StringUtils.isNullOrEmpty(libelle)) {
            throw new TacheException("Message vide");
        }

        this.id = id;
        this.libelle = cleanTextContent(libelle);

        if (this.libelle.length() > 255) {
            throw new TacheException("Erreur : message supérieur à 255 caractères");
        }

    }

    private static String cleanTextContent(String text)
    {
        // strips off all non-ASCII characters
        text = text.replaceAll("[^\\x00-\\x7F]", "");

        // erases all the ASCII control characters
        text = text.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");

        // removes non-printable characters from Unicode
        text = text.replaceAll("\\p{C}", "");

        text = text.replaceAll("[^ -~]","");

        text = text.replaceAll("[^\\p{ASCII}]", "");

        text = text.replaceAll("\\\\x\\p{XDigit}{2}", "");

        text = text.replaceAll("\\n","");

        text = text.replaceAll("[^\\x20-\\x7e]", "");
        return text.trim();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
