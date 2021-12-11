package youareell;

import controllers.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

//this is the command processor and this is where you create the url stuff
public class YouAreEll {

    TransactionController tt;

    public YouAreEll (TransactionController t) {
        this.tt = t;
    }

    public YouAreEll(MessageController messageController, IdController idController) {
        this.tt = new TransactionController(messageController,idController);
    }

    public static void main(String[] args) {
        // hmm: is this Dependency Injection?
        YouAreEll urlhandler = new YouAreEll(
            new TransactionController(
                new MessageController(), new IdController()
        ));
        System.out.println(urlhandler.MakeURLCall("/ids", "GET", ""));
        System.out.println(urlhandler.MakeURLCall("/messages", "GET", ""));
    }

    public String get_ids() {
        return tt.makecall("/ids", "GET", "");
    }

    public String get_messages() {
        return MakeURLCall("/messages", "GET", "");
    }

    private String MakeURLCall(String bisection, String cmd, String opts) {
        /**
        try{
            HttpURLConnection conn = (HttpURLConnection) (new URL("http","zipcode.rocks",8085,bisection).openConnection());
            if(bisection.equals("/messages")){
                //return "";
            }
            else if(bisection.equals("/ids")){
                //return "";
            }
            else {
                return "";
            }
            conn.setRequestMethod(cmd);
            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){

            }

        }
        catch(MalformedURLException murle){
            murle.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
         */
        return "";
    }


}
