package controllers;

import models.Id;

public class ServerController() {
    private String rootURL = "http://zipcode.rocks:8085";

    private static final ServerController svr = new ServerController();

    private ServerController() {}

    public static ServerController shared() {
        return svr;
    }

    public JsonString idGet() {
        // url -> /ids/
        // send the server a get with url
        // return json from server
    }
    public JsonString idPost(Id id) {
        // url -> /ids/
        // create json from Id
        // request
        // reply
        // return json
    }
    public JsonString idPut(Id id) {
        // url -> /ids/
    }


}

// ServerController.shared.doGet()