package controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import models.Id;
import models.Message;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

public class MessageController {

    private final HashSet<Message> messagesSeen = new HashSet<>();
    // why a HashSet??

    public ArrayList<Message> getMessages() {
        //messagesSeen.clear();
        JsonArray jara = ServerController.shared().messagesGet();
        List<JsonObject> jlist = jara.getValuesAs(JsonObject.class);
        if(jara.getValueType() != JsonValue.ValueType.ARRAY){
            System.out.println("JSON output error");
            return null;
        }
        ArrayList<Message> trove = new ArrayList<>();
        int i = 0;
        while(i < jlist.size()){
            JsonObject job = jlist.get(i);
            Message temper = new Message(
                    job.getString("sequence"),
                    job.getString("timestamp"),
                    job.getString("message"),
                    job.getString("fromid"),
                    job.getString("toid")
            );
            trove.add(temper);
            messagesSeen.add(temper);
            i++;
        }
        return trove;

    }
    public ArrayList<Message> getMessagesForId(Id Id) {
        return null;
    }
    public Message getMessageForSequence(String seq) {
        return null;
    }
    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        return null;
    }

    public Message postMessage(Id myId, Id toId, Message msg) {
        return null;
    }
 
}