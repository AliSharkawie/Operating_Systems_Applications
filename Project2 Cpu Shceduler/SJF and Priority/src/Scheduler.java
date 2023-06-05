import java.util.Scanner;

class process{
    int pid;
    int waiting_time;
    int arrival;
    int burst_time;
    int turnaround_time;
    int timetocomplete;
    int completiontime=0;
    int priority;
    int rr_quantum;
    int context_switch;


    process(int pid,int arrive,int bur,int priority){
        this.pid=pid;
        this.arrival=arrive;
        this.burst_time=bur;
        this.timetocomplete=burst_time;
        this.priority=priority;
    }
    process(int pid,int arrive,int bur,int context_switch,int rr_quantum){
        this.pid=pid;
        this.arrival=arrive;
        this.burst_time=bur;
        this.timetocomplete=burst_time;
        this.context_switch=context_switch;
        this.rr_quantum=rr_quantum;

    }

}


public class Scheduler {
    static Scanner s= new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("enter the number of proccesses");
        int n=s.nextInt();
        process [] myProcess = new process[n];
        System.out.println("please choose the type of scheduler you want to operate");
        System.out.println("1 . SFJ SCHEDULER");
        System.out.println("2 . PRIORITY SCHEDULER");
        System.out.println("3 . EXIT");
        int choice =s.nextInt();

        switch(choice){
            case 1:
                SFJ(myProcess);
                break;
            case 2:
                priority(myProcess);
                break;
            case 3:
                s.close();
                System.exit(1);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        s.close();
    }

    static void SFJ(process myProcess[]){
        int curtimeinterval=0,completed=0;
        float sum_turnaround=0,sum_waiting=0 ;
        System.out.println("please enter the context switch unit");
        int context_switch=s.nextInt();
        for(int i=0;i<myProcess.length;i++){
            System.out.println("please enter the burst time , arrival time , priority ");
            int bur=s.nextInt();
            int arrive=s.nextInt();
            int priority = s.nextInt() ;
            myProcess[i]=new process(i+1, arrive, bur,priority);}

        process curProcess;
        curProcess=myProcess[0];
        while(completed<myProcess.length){
            for(int i=0;i<myProcess.length;i++){
                if (myProcess[i].timetocomplete>0){
                    curProcess=myProcess[i];
                    //curtimeinterval+=context_switch;
                    break;}}

            System.out.println("current interval is = "+curtimeinterval);
            System.out.println("number of completed processes is = "+completed+"\n");

            for(int i=0;i<myProcess.length;i++){
                if(myProcess[i].arrival > curtimeinterval || myProcess[i].timetocomplete==0){
                    continue;}
                if(myProcess[i].timetocomplete< curProcess.timetocomplete){
                    curProcess=myProcess[i];
                }}
            curProcess.timetocomplete-=1;
            if(curProcess.timetocomplete==0){

                completed++;
                curProcess.completiontime=curtimeinterval+1;

            }
            curtimeinterval++;
        }
        for(int i=0; i<myProcess.length;i++){
            myProcess[i].waiting_time=myProcess[i].completiontime - myProcess[i].arrival -myProcess[i].burst_time;
            myProcess[i].turnaround_time=myProcess[i].waiting_time+myProcess[i].burst_time;
            sum_turnaround+=myProcess[i].turnaround_time;
            sum_waiting+=myProcess[i].waiting_time;
            System.out.println("P"+myProcess[i].pid+"");
            System.out.println("turnaround time\t\tcompletion\twaiting time");
            System.out.println("\t"+myProcess[i].turnaround_time+"\t\t"+myProcess[i].completiontime+"\t\t"+myProcess[i].waiting_time+"\n");

        }

        System.out.println("Average waiting time is : "+sum_waiting/myProcess.length +" ");
        System.out.println("Average turnaround time is : "+sum_turnaround/myProcess.length +" ");
    }


    static void priority(process myProcess[]){
        int curtimeinterval=0,completed=0;
        float sum_turnaround=0,sum_waiting=0 ;
        for(int i=0;i<myProcess.length;i++){
            System.out.println("please enter the burst time , arrival time , Priority ");
            int bur=s.nextInt();
            int arrive=s.nextInt();
            int priority = s.nextInt() ;
            myProcess[i]=new process(i+1, arrive, bur,priority);}

        process curProcess;
        curProcess=myProcess[0];
        while(completed<myProcess.length){
            for(int i=0;i<myProcess.length;i++){
                if (myProcess[i].timetocomplete>0){
                    curProcess=myProcess[i];
                    break;}}

            System.out.println("current interval is = "+curtimeinterval);
            System.out.println("number of completed processes is = "+completed+"\n");

            for(int i=0;i<myProcess.length;i++){
                if(myProcess[i].arrival > curtimeinterval || myProcess[i].timetocomplete==0){
                    continue;}
                if(myProcess[i].priority < curProcess.priority){
                    curProcess=myProcess[i];

                    while(curtimeinterval%900 ==0){
                        if(curtimeinterval!=0)
                            myProcess[i].priority-=1;
                    }
                }}
            curProcess.timetocomplete-=1;
            if(curProcess.timetocomplete==0){

                completed++;
                curProcess.completiontime=curtimeinterval+1;

            }
            curtimeinterval++;
        }
        for(int i=0; i<myProcess.length;i++){
            myProcess[i].waiting_time=myProcess[i].completiontime - myProcess[i].arrival -myProcess[i].burst_time;
            myProcess[i].turnaround_time=myProcess[i].waiting_time+myProcess[i].burst_time;
            sum_turnaround+=myProcess[i].turnaround_time;
            sum_waiting+=myProcess[i].waiting_time;
            System.out.println("P"+myProcess[i].pid+"");
            System.out.println("turnaround time\t\tcompletion\twaiting time");
            System.out.println("\t"+myProcess[i].turnaround_time+"\t\t"+myProcess[i].completiontime+"\t\t"+myProcess[i].waiting_time+"\n");

        }

        System.out.println("Average waiting time is : "+sum_waiting/myProcess.length +" ");
        System.out.println("Average turnaround time is : "+sum_turnaround/myProcess.length +" ");
    }

}





