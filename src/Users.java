import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Users {
    private ArrayList<String> names;
    private ArrayList<String> passwords;

    public Users()
    {
        names = new ArrayList<>();
        passwords = new ArrayList<>();
    }
    void readUsers()
    {
        try (BufferedReader in = new BufferedReader(
                new FileReader("NamesAndPasswords"));
        ) {
            String l;
            while ((l = in.readLine()) != null) {
                names.add(l);
                l = in.readLine();
                passwords.add(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    ArrayList<String> getNames()
    {
        return names;
    }
    ArrayList<String> getPasswords()
    {
        return passwords;
    }
}
