import java.util.Scanner;
public class Main {
    public static int round_robin_quantam ;
    public static int context_switch ;
    public static int num_processes  ;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in) ;
        Scanner string_input = new Scanner(System.in) ;
        System.out.println("enter number of process ");
        num_processes = input.nextInt() ;
        System.out.println("round_robin_quantam  ");
        round_robin_quantam = input.nextInt() ;
        System.out.println("context_switch time   ");
        context_switch = input.nextInt() ;
        Process [] our_processes = new Process[num_processes] ;
        for(int i=0 ;i<num_processes ; i++){
            System.out.println("enter data for process (name , burst ,arrive )  for process  " + (i+1) );
            our_processes[i] = new Process() ;
            our_processes[i].setProcess_name(string_input.nextLine() );
            our_processes[i].setBurst_time(input.nextInt() );
            our_processes[i].setArrival_time(input.nextInt() );

        }
        Round_Robin RR = new Round_Robin(our_processes,4,20,0);
    /*
        Process p1 = new Process("P1",5 ,0);
        Process p2 = new Process("p2", 6,1);
        Process p3 = new Process("p3", 3,2);
        Process p4 = new Process("p4", 1,3);
        Process p5 = new Process("p5", 5,4);
        Process p6 = new Process("p6", 4,6);
        ///*
        Process p1 = new Process("P1",53 ,0);
        Process p2 = new Process("p2", 17,0);
        Process p3 = new Process("p3", 68,0);
        Process p4 = new Process("p4", 24,0);
        Process [] our_processes = {p1,p2,p3,p4};
        Round_Robin RR = new Round_Robin(our_processes,4,20,0);
        */

    }
}