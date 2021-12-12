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

    public String makecall(String s, String cmd, String opts) {
        if(s.equals("/ids")){
            if(cmd.equals("GET")){
                return idCtrl.getIds().toString();
            }
            if(cmd.equals("POST")){
                String[] optsa = opts.split(" ");
                return postId(optsa[0],optsa[1]);
            }
        }
        else{
            if(cmd.equals("GET")){
                return msgCtrl.getMessages().toString();
            }
            return "";
        }
        return "";
    }
}
