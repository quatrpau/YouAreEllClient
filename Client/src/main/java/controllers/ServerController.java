package controllers;

import models.Id;
import models.Message;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.json.*;

public class ServerController {
    private final String rootURL = "http://zipcode.rocks:8085";

    private static final ServerController svr = new ServerController();

    private ServerController() {
    }

    public static ServerController shared() {
        return svr;
    }
    public JsonObject messagePost(String tine, Message msg){
        try{
            //url -> /messages
            URL url = getConnected(tine);
            if(url== null){
                throw new NullPointerException("URL is null");
            }
            HttpURLConnection conn =(HttpURLConnection) (url.openConnection());
            JsonObject newbie = Json.createObjectBuilder()
                    .add("fromid",msg.getFromId())
                    .add("toid",msg.getToId())
                    .add("message",msg.getMessage())
                    .build();
            // send the server a get with url
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/json; utf-8");
            conn.setRequestProperty("Accept","application/json");
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            JsonWriter jw = Json.createWriter(os);
            jw.write(newbie);
            jw.close();
            os.close();
            int respo;
            if((respo = conn.getResponseCode() )!= 200){
                System.out.println(conn.getResponseMessage());
                throw new RuntimeException("HTTP Response Code: " + respo);
            }
            else{
                return Json.createReader(conn.getInputStream()).readObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
    public JsonStructure messagesGet(String tine){
        try{
            //url -> /messages
            URL url = getConnected(tine);
            if(url== null){
                throw new NullPointerException("URL is null");
            }
            HttpURLConnection conn =(HttpURLConnection) (url.openConnection());
            // send the server a get with url
            conn.setRequestMethod("GET");
            int respo;
            if((respo = conn.getResponseCode() )!= 200){
                throw new RuntimeException("HTTP Response Code: " + respo);
            }
            else{
                JsonReader jr = Json.createReader(conn.getInputStream());
                return jr.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonException jpe){
            System.err.println("NO MESSAGES");
        }
        return null;
    }

    //creates and sends a json object
    public JsonArray idGet() {
        try {
            // url -> /ids/
            URL url = getConnected("/ids");
            if(url== null){
                throw new NullPointerException("URL is null");
            }
            HttpURLConnection conn =(HttpURLConnection) (url.openConnection());
            // send the server a get with url
            conn.setRequestMethod("GET");
            // return json from server
            int respo;
            if((respo = conn.getResponseCode() )!= 200){
                throw new RuntimeException("HTTP Response Code: " + respo);
            }
            else{
                return Json.createReader(conn.getInputStream()).readArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public JsonObject idPost(Id id) {
        try{
            // url -> /ids/
            URL url = getConnected("ids");
            if(url== null){
                throw new NullPointerException("URL is null");
            }
            // create json from Id
            JsonObject newbie = Json.createObjectBuilder()
                    .add("userid", "-")
                    .add("name", id.getName())
                    .add("github", id.getGithub())
                    .build();
            // request
            HttpURLConnection conn =(HttpURLConnection) (url.openConnection());
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/json; utf-8");
            conn.setRequestProperty("Accept","application/json");
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            JsonWriter jw = Json.createWriter(os);
            jw.write(newbie);
            jw.close();
            os.close();
            int respo;
            if((respo = conn.getResponseCode() )!= 200){
                throw new RuntimeException("HTTP Response Code: " + respo);
            }
            // reply
            JsonObject jso =  Json.createReader(conn.getInputStream()).readObject();
            return jso;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public JsonObject idPut(Id id) {
        try {
            URL url = getConnected("ids");
            if (url == null) {
                throw new NullPointerException("URL is null");
            }
            HttpURLConnection conn = (HttpURLConnection) (url.openConnection());
            // create json from Id
            JsonObject newbie = Json.createObjectBuilder()
                    .add("userid", id.getUid())
                    .add("name", id.getName())
                    .add("github", id.getGithub())
                    .build();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            JsonWriter jw = Json.createWriter(os);
            jw.write(newbie);
            jw.close();
            os.close();
            int respo;
            if((respo = conn.getResponseCode() )!= 200){
                throw new RuntimeException("HTTP Response Code: " + respo);
            }
            return Json.createReader(conn.getInputStream()).readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private URL getConnected(String tine){
        try{
            return new URL(rootURL + tine);
        }
        catch(MalformedURLException murle){
            murle.printStackTrace();
        }
        return null;
    }


}

// ServerController.shared.doGet()