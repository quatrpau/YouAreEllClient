package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import models.Id;

import javax.json.*;

public class IdController {
    //name, github id
    private HashMap<String, Id> allIds;

    Id myId;

    public ArrayList<Id> getIds() {
        //if not in allIds;
        allIds.clear();
        JsonArray jarray = ServerController.shared().idGet();
        List<JsonObject> jlist= jarray.getValuesAs(JsonObject.class);
        //make jarray into arraylist
        if(jarray.getValueType() != JsonValue.ValueType.ARRAY){
            System.out.println("JSON output error");
        }
        ArrayList<Id> trove = new ArrayList<Id>();
        int i = 0;
        while(i < jlist.size()){
            JsonObject job = jlist.get(i);
            Id temper = new Id(
                    job.getString("name"),
                    job.getString("github")
            );
            temper.setUid(job.getString("userid"));
            trove.add(temper);
            allIds.put(temper.getName(),temper);
            i++;
        }
        return trove;


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