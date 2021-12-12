package controllers;

import jdk.nashorn.internal.parser.JSONParser;
import models.Id;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.json.*;
import javax.json.stream.JsonGenerator;

public class ServerController() {
    private final String rootURL = "http://zipcode.rocks:8085";

    private static final ServerController svr = new ServerController();

    private ServerController() {
    }

    public static ServerController shared() {
        return svr;
    }


    //creates and sends a json object
    public JsonString idGet() {
        try {
            // url -> /ids/
            URL url = getConnected();
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
                /**
                String response = new BufferedReader(
                        new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))
                        .lines()
                        .collect(Collectors.joining("\n"));

                */
                return Json.createReader(conn.getInputStream()).readObject().getJsonString(null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public JsonString idPost(Id id) {
        try{
            // url -> /ids/
            URL url = getConnected();
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
            return Json.createReader(conn.getInputStream()).readObject().getJsonString(null);
            // return json
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public JsonString idPut(Id id) {
        return null;
    }
    private URL getConnected(){
        try{
            return new URL(rootURL + "/id/");
        }
        catch(MalformedURLException murle){
            murle.printStackTrace();
        }
        return null;
    }


}

// ServerController.shared.doGet()