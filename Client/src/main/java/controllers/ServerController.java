package controllers;

import jdk.nashorn.internal.parser.JSONParser;
import models.Id;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonString;

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
                return Json.createReader(url.openStream()).readObject().getJsonString(null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public JsonString idPost(Id id) {
        // url -> /ids/
        // create json from Id
        // request
        // reply
        // return json
        return
    }
    public JsonString idPut(Id id) {
        // url -> /ids/
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