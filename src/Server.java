import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author - George Petkakis
 */
public class Server {
    public static void main (String[] args) {
        try{
            int serverPort = 7896; // the server port
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while(true) {
                Socket clientSocket = listenSocket.accept();
                System.out.println("Request from client" + clientSocket.getInetAddress()+"at port "+ clientSocket.getPort());
                Connection c = new Connection(clientSocket);


            }
        } catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
    }
}
class Connection extends Thread {
    PrintWriter out;
    //get the input stream from the connected socket
    InputStream inputStream;
    // create a data input stream so we can read data from it
    DataInputStream dataInputStream;

    Socket clientSocket;
    public Connection (Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            inputStream = clientSocket.getInputStream();
            dataInputStream = new DataInputStream(inputStream);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            start();
        } catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
    }
    public void run(){
        String inputt="";
        MailServer m = new MailServer();
        int aa=0;
        Users users = new Users();
        users.readUsers();
        ArrayList<String> names = users.getNames();
        ArrayList<String> passwords = users.getPasswords();
        for(int nam=0;nam<names.size();nam++)
        {
            m.Register(names.get(nam),passwords.get(nam)); //registers all Users from textFile
        }
        String input;
        int p;
        InputStreamReader in = new InputStreamReader(dataInputStream);
        BufferedReader bf = new BufferedReader(in);
        try {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
            input= bf.readLine();
               while(input!=null){

                   if(input.equals("LogIn")) //for log in
                   {
                       do {
                           String exit;
                           do {
                               String Username;
                               Username = bf.readLine();
                               String Password;
                               Password = bf.readLine();
                               p = m.LogIn(Username, Password);
                               if (p == -1)
                                   exit = "Wrong";
                               else
                                   exit = m.getName(p);
                               out.println(exit);
                               out.flush();
                           } while (exit.equals("Wrong"));
                           do {
                               input = bf.readLine();
                               if (input.equals("NewEmail")) {
                                   String receiver;
                                   do {
                                       receiver = bf.readLine(); //checks if the receiver's name exists
                                       receiver = m.search(receiver);
                                       if (receiver != null)
                                           out.println("IsOk");
                                       else
                                           out.println("IsNotOk");
                                       out.flush();
                                   } while (receiver == null);
                                   String subject;
                                   do {
                                       subject = bf.readLine();
                                   } while (subject == null);
                                   String mainbody;
                                   do {
                                       mainbody = bf.readLine();
                                   } while (mainbody == null);
                                   m.setNewEmail(p, receiver, subject, mainbody);
                               } else if (input.equals("ShowEmails")) {
                                   out.println(m.getNumberOfEmails(p)); //sends the number of emails
                                   out.flush();
                                   out.println(m.getTheSubjects(p)); //sends the subjects
                                   out.flush();
                                   out.println(m.getTheSenders(p)); //sends the senders
                                   out.println(m.getIfNew(p)); //sends if is new or not the email
                                   out.flush();
                               } else if (input.equals("ReadEmail")) {
                                   int t = Integer.parseInt(bf.readLine()) - 1;
                                   if (m.checkIfEmailExists(p, t)) {
                                       out.println(m.getTheEmail(p, t)); //p the account i and t the email i
                                   } else
                                       out.println("Bad");
                                   out.flush();
                               } else if (input.equals("DeleteEmail")) {
                                   int t = Integer.parseInt(bf.readLine()) - 1;
                                   if (m.checkIfEmailExists(p, t)) {
                                       m.DeleteEmail(p, t);
                                       out.println("Good");
                                   } else
                                       out.println("Bad");
                                   out.flush();
                               } else if (input.equals("Exit")) {
                                   aa=1;
                                   inputt = "retry";
                                   break;
                               }
                               if(input.equals("LogOut"))
                                   inputt="retry";
                           } while (!input.equals("LogOut"));
                       } while (!inputt.equals("retry"));
                   }
                   else if(input.equals("Register")) //creates a new user
                   {
                       String Username;
                       Username = bf.readLine();
                       String Password;
                       Password = bf.readLine();
                       m.Register(Username,Password);
                   }
                   else if(input.equals("Exit")) //exits
                   {
                       break;
                   }
                   if(aa==1)
                       break;
                   input= bf.readLine();
                }
                bf.close();
                out.close();
            } catch (EOFException e) {
                System.out.println("EOF:" + e.getMessage());
            } catch (IOException e) {
                System.out.println("readline:" + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {/*close failed*/}
            }
    }
}
