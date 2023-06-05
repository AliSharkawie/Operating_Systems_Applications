import java.util.ArrayList;
public class Main {
       //sort function
    static ArrayList<process> sort(ArrayList<process> array, String sortType) {
        process temp = new process();

        if (sortType == "FCFS") {
            for (int i = 0; i < array.size() - 1; i++) {
                for (int j = 0; j < array.size() - 1; j++) {
                    if (array.get(j).getArrival_time() > array.get(j + 1).getArrival_time()) {
                        temp = array.get(j);
                        array.set(j, array.get(j + 1));
                        array.set(j + 1, temp);
                    }
                }
            }
        } else if (sortType == "NPP") {
            for (int i = 0; i < array.size() - 1; i++) {
                for (int j = 0; j < array.size() - 1; j++) {
                    if (array.get(j).get_priority() > array.get(j + 1).get_priority()) {
                        temp = array.get(j);
                        array.set(j, array.get(j + 1));
                        array.set(j + 1, temp);
                    }
                }
            }
        } else if (sortType == "SJF") {
            for (int i = 0; i < array.size() - 1; i++) {
                for (int j = 0; j < array.size() - 1; j++) {
                    if (array.get(j).getRemaining_time() > array.get(j + 1).getRemaining_time()) {
                        temp = array.get(j);
                        array.set(j, array.get(j + 1));
                        array.set(j + 1, temp);
                    }
                }
            }
        }
        return array;
    }
    //delta function to return the q at any percentage entered
    static int delta(int q, double percentage) {
        double result = (percentage / 100.0) * q;
        double result2 = Math.ceil(result);
        return (int) result2;
    }
    //terminate process function used at the AG_Scheduler function to terminate process
    static process terminateProcess(process currentProcess, int currentTime, ArrayList<process> pArray, ArrayList<process> fArray, ArrayList<process> tempArray, String sortType) {
        // updating process state
        currentProcess.setQuantum_time(0);
        currentProcess.setRemaining_time(0);
        currentProcess.setEnd_time(currentTime);

        //  transfer the process from procceses arry to the finished processes array
        fArray.add(currentProcess);
        pArray.remove(currentProcess);

        // picking the Nxt process By FCFS
        tempArray = get_tillnow(pArray, currentTime);
        if (tempArray.size() != 0)
            currentProcess = sort(tempArray, sortType).get(0);

        return currentProcess;
    }
    // function get_till now which return array with element found in ready queue at that time
    static ArrayList<process> get_tillnow(ArrayList<process> arr, int currentTime) {

        ArrayList<process> developed_Arr = new ArrayList<process>();

        for (int i = 0; i < arr.size(); i++) {
            if ((arr.get(i).getArrival_time() <= currentTime)) {
                developed_Arr.add(arr.get(i));
            }
        }
        return developed_Arr;
    }
//main function
    public static void main(String[] args) {
          ArrayList<process> arr = new ArrayList<process>();
          output output=new output();
//        get the input from  user and inter in arr
          arr=output.usage();

          ArrayList<process> arr1 = new ArrayList<process>();
          ArrayList<String> execution = new ArrayList<String>();

//        arr.add(new process("P1", 0, 17, 7, 4));
//        arr.add(new process("P2", 2, 6, 9, 7));
//        arr.add(new process("P3", 5, 11, 4, 3));
//        arr.add(new process("P4", 15, 4, 6, 6));

        arr1 = AG_Scheduler(arr, execution);
        arr1 = sort(arr1, "FCFS");
        double sum = 0;
        double waitingSum = 0;
        for (int i = 0; i < arr1.size(); i++) {
            int v1 = arr1.get(i).getEnd_time() - arr1.get(i).getArrival_time();
            int v2 = arr1.get(i).getEnd_time() - (arr1.get(i).getArrival_time() + arr1.get(i).getBurst_time());
            System.out.println("Process " + (i + 1) + " Turnaround: " + v1);
            System.out.println("Process " + (i + 1) + " Waiting: " + v2);
            sum += v1;
            waitingSum += v2;
        }
        System.out.println("Processes Average Turnaround: " + sum / arr1.size());
        System.out.println("Processes Average Waiting: " + waitingSum / arr1.size());
        for (int i = 0; i < execution.size(); i++) {
            System.out.print(execution.get(i) + " ");
        }
        // Turnaround = endtime - arrivaltime
        // average turn
        //  waiting =completion time-(brust time+arrival time)
        // average waiting

    }

    static ArrayList<process> AG_Scheduler(ArrayList<process> pArray, ArrayList<String> exec) {
        int currentTime = 0;
        ArrayList<process> fArray = new ArrayList<process>();
        ArrayList<process> tempArray = new ArrayList<process>();

        // the currentProcess = working process = running process
        process currentProcess;
        process previousProcess;

        currentProcess = sort(pArray, "FCFS").get(0);

        while(pArray.size() > 0) {
            exec.add(currentProcess.getProcess_name());
            int Q1 = delta(currentProcess.getQuantum_time(), 25);

            if (currentProcess.getRemaining_time() <= Q1) {

                currentTime += currentProcess.getRemaining_time();
                currentProcess = terminateProcess(currentProcess, currentTime, pArray, fArray, tempArray,"FCFS");
                continue;
            }
            else {
                currentTime += Q1;

                // updating process state
                currentProcess.setRemaining_time(currentProcess.getRemaining_time() - Q1);
                currentProcess.setQuantumRemaining(currentProcess.getQuantumRemaining() - Q1);

                // picking the Nxt process By NPP
                previousProcess = currentProcess;
                tempArray = get_tillnow(pArray, currentTime);
                currentProcess = sort(tempArray, "NPP").get(0);

                //! First if under else
                if (previousProcess == currentProcess) {
                    int Q2 = delta(currentProcess.getQuantum_time(), 50);

                    if (currentProcess.getRemaining_time() <= (Q2 - Q1)) {
                        currentTime += currentProcess.getRemaining_time();

                        currentProcess = terminateProcess(currentProcess, currentTime, pArray, fArray, tempArray,"FCFS");
                        continue;
                    }
                    else {
                        currentTime += (Q2 - Q1);
                        currentProcess.setRemaining_time(currentProcess.getRemaining_time() - (Q2 - Q1));
                        currentProcess.setQuantumRemaining(currentProcess.getQuantumRemaining() - (Q2 - Q1));

                        previousProcess = currentProcess;
                        tempArray = get_tillnow(pArray, currentTime);
                        currentProcess = sort(tempArray, "SJF").get(0);

                        if (currentProcess == previousProcess) {
                            int Q4 = delta(currentProcess.getQuantum_time(), 100);
                            boolean flag = false ;



                            for (int i = Q2; i < Q4; i++) {
                                if(currentProcess.getRemaining_time() == 0){

                                    currentProcess = terminateProcess(currentProcess, currentTime, pArray, fArray, tempArray,"FCFS");
                                    flag = true;
                                    break;
                                }
                                tempArray = get_tillnow(pArray, currentTime);
                                previousProcess = currentProcess;
                                currentProcess = sort(tempArray, "SJF").get(0);

                                if (previousProcess == currentProcess) {
                                    currentProcess.setRemaining_time(currentProcess.getRemaining_time() - 1);
                                    currentProcess.setQuantumRemaining(currentProcess.getQuantumRemaining() - 1);

                                } else {
                                    //! Context Switch!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                                    previousProcess.setQuantum_time(previousProcess.getQuantum_time() + previousProcess.getQuantumRemaining());
                                    previousProcess.setQuantumRemaining(previousProcess.getQuantum_time());
                                    flag = true;
                                    break;
                                }
                                currentTime++;
                            }
                            if (flag) continue;
                            if (currentProcess.getRemaining_time()>0){

                                currentProcess.setQuantum_time(currentProcess.getQuantum_time()+2);
                                currentProcess.setQuantumRemaining(currentProcess.getQuantum_time());

                                // picking the Nxt process By FCFS
                                tempArray = get_tillnow(pArray, currentTime);
                                currentProcess = sort(tempArray, "FCFS").get(0);
                                continue;

                            }
                            else{
                                currentProcess = terminateProcess(currentProcess, currentTime, pArray, fArray, tempArray,"FCFS");
                                continue;
                            }

                        }
                        else {
                            previousProcess.setQuantum_time(previousProcess.getQuantum_time() + previousProcess.getQuantumRemaining());
                            previousProcess.setQuantumRemaining(previousProcess.getQuantum_time());
                            continue;
                        }
                    }

                }
                else {
                    //! Context Switch!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    previousProcess.setQuantum_time(previousProcess.getQuantum_time() + (previousProcess.getQuantumRemaining()/2));
                    previousProcess.setQuantumRemaining(previousProcess.getQuantum_time());
                    continue;
                }
            }
        }
        return fArray;
    }
}