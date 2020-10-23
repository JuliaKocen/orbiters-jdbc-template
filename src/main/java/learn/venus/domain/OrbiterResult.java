package learn.venus.domain;

import learn.venus.models.Orbiter;

import java.util.ArrayList;
import java.util.List;

public class OrbiterResult <T> {
    private ArrayList<String> messages = new ArrayList<>();
    private Orbiter payload;
    private ResultType type = ResultType.SUCCESS;


    public ResultType getType() {
        return type;
    }

    public void addErrorMessage (String message) {
        messages.add(message);
    }

    public boolean isSuccess() {
        return messages.size() == 0;
    }

//    public T getPayload() {
//        return payload;
//    }
//
//    public void setPayload(T payload) {
//        this.payload = payload;
//    }


    public Orbiter getPayload() {
        return payload;
    }

    public void setPayload(Orbiter payload) {
        this.payload = payload;
    }

    public List<String> getMessages() {
        return new ArrayList<> (messages);
    }

    public void addMessage(String message, ResultType type) {
        messages.add(message);
        this.type = type;
    }


}
