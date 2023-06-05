public class process {
    private String process_name;
    private int Arrival_time;
    private int Burst_time;
    private int Quantum_time;
    private int remaining_Quantum_time;
    private int remaining_time ;
    private int priority;
    private int end_time;

    public process(String process_name, int arrival_time, int burst_time,int Quantum_time,int priority) {
        this.process_name = process_name;
        this.Quantum_time = Quantum_time;
        this.priority = priority;
        this.Arrival_time = arrival_time;
        this.Burst_time = burst_time;
        this.remaining_time = burst_time;
        this.remaining_Quantum_time=Quantum_time;
    }

    public process(){
        this.process_name = null;
        this.Quantum_time = 0;
        this.priority = 0;
        this.Arrival_time = 0;
        this.Burst_time = 0;
        this.remaining_time = 0;
    }

    public int get_priority(){
        return this.priority;
    }

    public int getQuantum_time(){
        return this.Quantum_time;
    }

    public void setQuantum_time(int quantumTime){
        this.Quantum_time = quantumTime;
    }

    public int getEnd_time() {
        return this.end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
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
    public int getRemaining_time() {
        return remaining_time;
    }
    public int getQuantumRemaining() {
        return remaining_Quantum_time;
    }
    public void setQuantumRemaining(int QuantumRemaining) {
        this.remaining_Quantum_time = QuantumRemaining;
    }

    public void setRemaining_time(int remaining_time) {
        this.remaining_time = remaining_time;
    }
}
