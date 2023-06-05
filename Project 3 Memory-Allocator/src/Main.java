import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Process> processes = new ArrayList<>();
        ArrayList<Partition> partitions = new ArrayList<>();
//        System.out.println("please enter the number of partitions");
//        int num =scanner.nextInt();
//        for(int i=0;i<num;i++){
//            System.out.println("partition "+Integer.toString(i)+" name :");
//            String name=scanner.next();
//            System.out.println("Partition "+Integer.toString(i)+" size : ");
//            int partitionSize  =scanner.nextInt();
//            Partition tempP = new Partition(name,partitionSize);
//        }
//        System.out.println("please enter the number of processes");
//        num =scanner.nextInt();
//        for(int i=0;i<num;i++){
//            System.out.println("process "+Integer.toString(i)+" name :");
//            String name = scanner.next();
//            System.out.println("Process "+Integer.toString(i)+" size : ");
//            int processSize  =scanner.nextInt();
//            Process tempP = new Process(name,processSize);
//        }

        Partition partition0 = new Partition("partition 0 ", 90);
        Partition partition1 = new Partition("partition 1 ", 20);
        Partition partition2 = new Partition("partition 2 ", 5);
        Partition partition3 = new Partition("partition 3 ", 30);
        Partition partition4 = new Partition("partition 4 ", 120);
        Partition partition5 = new Partition("partition 5 ", 80);

        partitions.add(partition0);
        partitions.add(partition1);
        partitions.add(partition2);
        partitions.add(partition3);
        partitions.add(partition4);
        partitions.add(partition5);

        Process process1 = new Process("process1", 15);
        Process process2 = new Process("process2", 90);
        Process process3 = new Process("process3", 30);
        Process process4 = new Process("process4", 100);

        processes.add(process1);
        processes.add(process2);
        processes.add(process3);
        processes.add(process4);


        MemoryAllocator M1 = new MemoryAllocator(processes, partitions);
//        do{
//            System.out.println("Select the policy you want to apply:\n" +
//                    "1. First fit\n" +
//                    "2. Worst fit\n" +
//                    "3. Best fit");
//            num =scanner.nextInt();
//
//        }while ( num <= 0 || num > 3 );


        // enter the policy
        M1.loadProcesses(1);
        M1.printMemory();

        M1.compact();
//      M1.printMemory();

        System.out.println("\n");
        M1.loadWaiting();
        M1.printMemory();

    }
}