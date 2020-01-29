import java.util.ArrayList;

public class Account {
    private String Username;
    private String Password;
    private ArrayList<Email> MailBox;

    public Account(String username,String password)
    {
        Username=username;
        Password=password;
        MailBox = new ArrayList<>();
    }
    void setMailBox(String sender,String subject,String mainbody){
        Email m =new Email();
        m.setReceiver(Username);
        m.setIsNew();
        m.setSender(sender);
        m.setSubject(subject);
        m.setMainbody(mainbody);
        MailBox.add(m);
    }
    int getMailBoxSize()
    {
        return MailBox.size();
    }
    /**
     * An Array named ret that returns for all emails if there have been read or not
     * @return the boolean Array ret
     */
    boolean[] getIfIsNew()
    {
        boolean[] ret = new boolean[MailBox.size()];
        for(int i=0;i<MailBox.size();i++)
        {
            ret[i]= MailBox.get(i).getIsNew();
        }
        return  ret;
    }

    /**
     * An Array named Sub that returns for all emails the subjects
     * @return the String Array Sub
     */
    String[] getSubjects()
    {
        String[] Sub = new String[MailBox.size()];
        for(int i=0;i<MailBox.size();i++)
        {
            Sub[i]= MailBox.get(i).getSubject();
        }
        return  Sub;
    }
    String[] getSenders()
    {
        String[] Sub = new String[MailBox.size()];
        for(int i=0;i<MailBox.size();i++)
        {
            Sub[i]= MailBox.get(i).getSender();
        }
        return  Sub;
    }
    String getMail(int p)
    {
           MailBox.get(p).setIsOld();
           return MailBox.get(p).getMainbody();
    }
    void deleteEmail(int id)
    {
            MailBox.remove(id);
    }
    int getNumberOfemails()
    {
        return MailBox.size();
    }
    String getUsername(){
        return Username;
    }
    String getPassword()
    {
        return Password;
    }







}

