public class Process {
    private String process_name;
    private int Arrival_time;
    private int Burst_time;
    private int turn_arroundtime =0;
    private int waiting_time = 0 ;
    private int Response_time;
    private int completion_time =0;
    private int Process_priority;
    //private int count =0 ;
    private int end_time;
    int remaining_time ;
    int counter=0;
    public Process(String process_name , int burst_time , int arrival_time ) {
        this.process_name = process_name;
        Arrival_time = arrival_time;
        Burst_time = burst_time;
    }
    public Process(){
        //count = 0 ;
    }
    public void setTurn_arroundtime(int turn_arroundtime) {
        this.turn_arroundtime = turn_arroundtime;
    }


    public int getTurn_arroundtime() {
        return turn_arroundtime;
    }

    public void setArrival_time(int Arrival_time) {
        this.Arrival_time = Arrival_time;
    }

    public void setBurst_time(int Burst_time) {
        this.Burst_time = Burst_time;
    }

    public void setProcess_priority(int Process_priority) {
        this.Process_priority = Process_priority;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }
    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

    public int getCompletion_time() {
        return completion_time;
    }

    public void setCompletion_time(int completion_time) {
        this.completion_time = completion_time;
    }

    public int getResponse_time() {
        return Response_time;
    }

    public void setResponse_time(int response_time) {
        Response_time = response_time;
    }


    public void setProcess_name(String process_name) {
        this.process_name = process_name;
    }

    public int getTurn_arroundime() {
        return turn_arroundtime;
    }

    public void setTurn_arroundime(int turn_arroundime) {
        this.turn_arroundtime = turn_arroundime;
    }

    public int getWaiting_time() {
        return waiting_time;
    }

    public void setWaiting_time(int waiting_time) {
        this.waiting_time = waiting_time;
    }
    public String getProcess_name() {
        return process_name;
    }

    public int getArrival_time() {
        return Arrival_time;
    }

    public int getBurst_time() {
        return Burst_time;
    }

    public int getProcess_priority() {
        return Process_priority;
    }

    public void update(Process ope){
        this.Burst_time--;

    }

    public int getRemaining_time() {
        return remaining_time;
    }

    public void setRemaining_time(int remaining_time) {
        this.remaining_time = remaining_time;
    }
}

