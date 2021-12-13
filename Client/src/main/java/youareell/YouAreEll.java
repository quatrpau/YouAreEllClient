package youareell;

import controllers.*;
import models.Id;

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
        return tt.makecall("/messages", "GET", "");
    }

    private String MakeURLCall(String tine, String cmd, String opts) {
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
    public String postOrPut(String name, String github){
        if(tt.getIds().contains(new Id(null,github))){
            return put_id(name,github);
        }
        return post_id(name,github);
    }

    private String put_id(String name, String github) {
        return tt.makecall( "/ids","PUT",name + " " + github);
    }

    private String post_id(String name, String github) {
        return tt.makecall("/ids", "POST",name + " " + github);
    }
    public String get_messages_from_me(String github){
        return tt.makecall("/ids/" + github + "/messages", "GET",github);
    }
    public String post_message_to_world(String github, String message) {
        return tt.makecall("/ids/" + github + "/messages", "POST " + github,message);
    }
}
