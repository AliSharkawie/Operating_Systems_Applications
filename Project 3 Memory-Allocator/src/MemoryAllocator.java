import java.util.ArrayList;

public class MemoryAllocator {
    public ArrayList<Process> processes;
    public ArrayList<Partition> partitions;
    public ArrayList<Process> waitingList = new ArrayList<Process>();

    public MemoryAllocator(ArrayList<Process> processes, ArrayList<Partition> partitions) {

        this.processes = processes;
        this.partitions = partitions;
    }

    // A method that uses various policies to properly allocate each of the incoming processes
    public void loadProcesses(int policy) {
        /*
           We attempt to obtain a partition using the provided policy before loading the process using load Fn;
           if a suitable partition cannot be found, the process is added to the waiting list.
        */

        if (policy == 1) {
            Partition temp;
            for (int i = 0; i < processes.size(); i++) {
                temp = getPartition(processes.get(i), "FristFit");
                if (temp != null) load(processes.get(i), temp);
                else waitingList.add(processes.get(i));
            }

        } else if (policy == 2) {
            Partition temp;
            for (int i = 0; i < processes.size(); i++) {
                temp = getPartition(processes.get(i), "BestFit");
                if (temp != null) load(processes.get(i), temp);
                else waitingList.add(processes.get(i));
            }
        } else if (policy == 3) {
            Partition temp;
            for (int i = 0; i < processes.size(); i++) {
                temp = getPartition(processes.get(i), "WorstFit");
                if (temp != null) load(processes.get(i), temp);
                else waitingList.add(processes.get(i));
            }
        } else System.out.println("Wrong input");
    }

    // FN attempts to get a proper partiton to get the process into using the policy and the process attribute.
    public Partition getPartition(Process p, String policy) {

        if (policy == "WorstFit") {
            Partition WorstPartition = partitions.get(0);
            boolean flag = false;

            for (int i = 0; i < this.partitions.size(); i++) {
                if (p.isFit(partitions.get(i)) > 0 && partitions.get(i).isEmpty()) {
                    if (WorstPartition.size < partitions.get(i).size || flag == false) {
                        WorstPartition = partitions.get(i);
                        flag = true;
                    }
                }
            }
            if (flag == true) return WorstPartition;
            else return null;
        } else if (policy == "BestFit") {

            Partition BestPartition = partitions.get(0);
            boolean flag = false;

            for (int i = 0; i < this.partitions.size(); i++) {
                if (p.isFit(partitions.get(i)) > 0 && partitions.get(i).isEmpty()) { // condition to fit
                    if (BestPartition.size > partitions.get(i).size || flag == false) {
                        BestPartition = partitions.get(i);
                        flag = true;
                    }
                }
            }

            if (flag == true) return BestPartition;
            else return null;

        } else {
            for (int i = 0; i < this.partitions.size(); i++) {
                if (p.isFit(partitions.get(i)) > 0 && partitions.get(i).isEmpty())
                    return partitions.get(i);
            }
        }
        return null;
    }


    //In this method, we remove the empty space between processes;
    // and combine them into a single partition at the end of memory.
    public void compact() {
        int new_size = 0;
        for (int i = 0; i < this.partitions.size(); i++) {
            // After determining whether it is empty,
            // we add its size to the partition we want to create and remove it from the list.
            if (partitions.get(i).isEmpty()) {
                new_size += partitions.get(i).size;
                partitions.remove(i);
                i--;
            }
        }
        String str = "partition " + Integer.toString(Partition.counter) + " ";
        Partition p = new Partition(str, new_size);
        partitions.add(p);

    }


    // It moves the process to a specific partition and makes any necessary changes.
    public void load(Process proc, Partition part) {
        // assigning the process to the partition
        part.process = proc;
        //calc the remaining space to see if a new partition will be created or not
        part.remainingSpace = part.size - proc.size;
        // making the partition size eql to the process size
        part.size = proc.size;

        // If the partition has any free space, split it and create a new partition with the free space.
        if (part.remainingSpace > 0) {
            String str = "partition " + Integer.toString(part.counter) + " ";
            Partition newPartition = new Partition(str, part.remainingSpace);
            part.remainingSpace = 0;
            int j = partitions.indexOf(part);
            partitions.add(j + 1, newPartition);

        }
    }

    // it prints the sates of all the partition in the memory
    public void printMemory() {
        for (int i = 0; i < partitions.size(); i++) {
            partitions.get(i).print();
        }
        System.out.println("Waiting Processes");
        for (int i = 0; i < waitingList.size(); i++) {
            System.out.println(waitingList.get(i).name);
        }
    }

    // It's a Fn that loads all the pending processes that can be placed in their correct places.
    public void loadWaiting() {
        for (int i = 0; i < waitingList.size(); i++) {
            int j = partitions.size() - i - 1;
            Partition tempP = partitions.get(j);
            if (waitingList.get(i).isFit(tempP) > 0) {
                load(waitingList.get(i), partitions.get(j));
                Process temp = waitingList.get(i);
                waitingList.remove(temp);

            }
        }
    }

}
