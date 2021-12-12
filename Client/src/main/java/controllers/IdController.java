package controllers;

import java.util.ArrayList;
import java.util.HashMap;

import models.Id;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.print.attribute.standard.Severity;

public class IdController {
    private HashMap<String, Id> allIds;

    Id myId;

    public ArrayList<Id> getIds() {
        return null;
    }

    public Id postId(Id id) {
        // create json from id
        JsonObject jstr = ServerController.shared().idPost(id);
        // call server, get json result Or error
        if(jstr.getValueType() != JsonValue.ValueType.OBJECT){
            System.out.println("JSON output error");
        }
        // result json to Id obj
        //how does json string look like
        String[] unfiltered = jstr.toString().split("\\W+");
        Id returner =new Id(unfiltered[3],unfiltered[5]);
        returner.setUid(unfiltered[1]);
        return returner;

    }

    public Id putId(Id id) {
        return null;
    }
 
}