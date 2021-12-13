package controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import models.Id;
import models.Message;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonStructure;
import javax.json.JsonValue;

public class MessageController {

    private final HashSet<Message> messagesSeen = new HashSet<>();
    // why a HashSet??

    public ArrayList<Message> getMessages() {
        //messagesSeen.clear();
        JsonStructure jara = ServerController.shared().messagesGet("/messages");
        return convertJsonArray(jara);
    }
    public ArrayList<Message> getMessagesForId(Id Id) {
        JsonStructure jara = ServerController.shared().messagesGet("/ids/" + Id.getGithub() + "/messages");
        return convertJsonArray(jara);

    }
    public Message getMessageForSequence(String seq) {
        return null;
    }
    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        return null;
    }

    public Message postMessage(Message msg) {
        //if statement needed to make sure friends messages are concurrent when you send one?
        JsonObject job = ServerController.shared().messagePost("/ids/" + msg.getToId() + "/messages",msg);
        if(job.getValueType() != JsonValue.ValueType.OBJECT){
            System.out.println("JSON output error");
        }
        // result json to Id obj
        //how does json string look like
        return new Message(
                job.getString("sequence"),
                job.getString("timestamp"),
                job.getString("message"),
                job.getString("fromid"),
                job.getString("toid")
        );
    }
    private ArrayList<Message> convertJsonArray(JsonStructure jara){
        if(jara == null) return null;
        if(jara.getValueType() != JsonValue.ValueType.ARRAY){
            System.out.println("JSON output error");
            return null;
        }
        JsonArray jaraa = (JsonArray) jara;
        List<JsonObject> jlist =  jaraa.getValuesAs(JsonObject.class);
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
 
}