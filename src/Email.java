import java.util.stream.Stream;

public class Email {

    private boolean isNew; //new-true, read-false
    private String sender;
    private String receiver;
    private String subject;
    private String mainbody;

    public Email()
    {}
    void setIsNew()
    {
        isNew=true;
    }
    void setIsOld()
    {
        isNew=false;
    }
    void setSender(String Sender){sender=Sender;}
    void setReceiver(String Receiver){receiver=Receiver;}
    void setSubject(String Subject){subject=Subject;}
    void setMainbody(String MainBody){mainbody=MainBody;}
    boolean getIsNew()
    {
        return isNew;
    }
    String getSender()
    {
        return sender;
    }
    String getSubject()
    {
        return subject;
    }
    String getMainbody()
    {
        return mainbody;
    }
}
