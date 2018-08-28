package domain;

public class Status {

    private Boolean complete;

    public Status(){
        this.complete = false;
    }

    public Status(Boolean complete) {
        this.complete = complete;
    }


    public Boolean isComplete() {
        return complete;
    }

    public void complete(){
        this. complete = true;
    }

}
