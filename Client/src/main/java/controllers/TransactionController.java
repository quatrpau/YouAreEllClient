package controllers;

import models.Id;

import java.util.List;

public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private MessageController msgCtrl;
    private IdController idCtrl;

    public TransactionController(MessageController m, IdController j) {
        this.msgCtrl = m;
        this.idCtrl = j;
    }

    public List<Id> getIds() {
        return idCtrl.getIds();
    }
    public String postId(String idtoRegister, String githubName) {
        Id tid = new Id(idtoRegister, githubName);
        tid = idCtrl.postId(tid);
        return ("Id registered.");
    }
    public String putId(String idToEdit, String githubName) {
        Id tid = new Id(idToEdit, githubName);
        List<Id> ids = idCtrl.getIds();
        tid.setUid(ids.get(ids.indexOf(tid)).getUid());
        tid = idCtrl.putId(tid);
        return ("Id changed.");
    }

    public String makecall(String s, String cmd, String opts) {
        if(s.equals("/ids")){
            if(cmd.equals("GET")){
                return idCtrl.getIds().toString();
            }
            if(cmd.equals("POST")){
                String[] optsa = opts.split(" ");
                return postId(optsa[0],optsa[1]);
            }
            if(cmd.equals("PUT")){
                String[] optsa = opts.split(" ");
                return putId(optsa[0],optsa[1]);
            }
            else {
                return "";
            }
        }
        if(s.equals("/messages")){
            return msgCtrl.getMessages().toString();
        }
        if(s.equals("/ids/" + opts + "/messages")){
            return msgCtrl.getMessagesForId(new Id(null,opts)).toString();
        }
        return "";
    }
}
