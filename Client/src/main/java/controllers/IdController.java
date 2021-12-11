package controllers;

import java.util.ArrayList;
import java.util.HashMap;

import models.Id;

public class IdController {
    private HashMap<String, Id> allIds;

    Id myId;

    public ArrayList<Id> getIds() {
        return new ArrayList<Id>(allIds.values());
    }

    public Id postId(Id id) {
        // create json from id
        // call server, get json result Or error
        // result json to Id obj

        return null;
    }

    public Id putId(Id id) {
        return null;
    }
 
}