import java.util.ArrayList;
/**
 * @author - George Petkakis
 */
public class MailServer {
    private ArrayList<Account> acounts;

    public MailServer() {
        acounts = new ArrayList<>();
    }

    void Register(String Username,String Password)
    {
        Account t= new Account(Username,Password);
        acounts.add(t);
    }
    String search(String Username)
    {
        for(Account account : acounts)
        {
            if(account.getUsername().equals(Username))
               return Username;
        }
        return null;
    }
    int LogIn(String Username,String Password)
    {
        int i=0,j=0,ii=0,jj=0;
        for(int g=0;g<acounts.size();g++)
        {
            if(acounts.get(g).getUsername().equals(Username))
            {    i++;ii=g;}
            if(acounts.get(g).getPassword().equals(Password))
            {
                j++;jj=g;
            }
        }
        if(i > 0 && j > 0 && ii == jj)
            return ii;
        return -1;
    }
    String getName(int i)
    {
        return acounts.get(i).getUsername();
    }
    void setNewEmail(int i,String receiver,String subject,String mainbody)
    {
        for(int j=0;j<acounts.size();j++)
        {
            if(acounts.get(j).getUsername().equals(receiver))
                acounts.get(j).setMailBox(acounts.get(i).getUsername(),subject,mainbody);
        }
    }
    /**
     * We build a big String containing all the subjects ,splitting them with '|'
     * @param i
     * @return the built Array
     */
    String getTheSubjects(int i)
    {
        StringBuilder builder = new StringBuilder();
        String[] a = acounts.get(i).getSubjects();
        for(int j=0;j<a.length;j++)
        {
            builder.append(a[j]);
            builder.append("~");
        }
        return builder.toString();
    }
    String getTheSenders(int i)
    {
        StringBuilder builder = new StringBuilder();
        String[] a = acounts.get(i).getSenders();
        for(int j=0;j<a.length;j++)
        {
            builder.append(a[j]);
            builder.append("~");
        }
        return builder.toString();
    }
    boolean checkIfEmailExists(int i,int p)
    {
        //if p has a place in the ArrayList
        return p <= acounts.get(i).getMailBoxSize() - 1 && p >= 0;
    }
    void DeleteEmail(int i,int p)
    {
        acounts.get(i).deleteEmail(p);
    }
    String getIfNew(int i)
    {
        StringBuilder builder = new StringBuilder();
        boolean a[]= acounts.get(i).getIfIsNew();
        for(int j=0;j<a.length;j++)
        {
            if(a[j])
                builder.append("true");
            else
                builder.append("false");
            builder.append("~");
        }
        return builder.toString();
    }
    String getTheEmail(int i,int p){

        return acounts.get(i).getMail(p);
    }
    int getNumberOfEmails(int i)
    {
        return acounts.get(i).getNumberOfemails();
    }
}
