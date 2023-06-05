
public class Round_Robin {
    public  Round_Robin(Process [] our ,int num_processes ,int quantum , int context ){
        int total = 0 ; // total time for all processes
        Process[] temp = new Process[num_processes];
        for(int i=0 ;i<num_processes ;i++){
            temp[i] = new Process() ;
            temp[i].setProcess_name(our[i].getProcess_name());
            temp[i].setBurst_time(our[i].getBurst_time());
            temp[i].setArrival_time(our[i].getArrival_time());
            total += our[i].getBurst_time() ;
        }
        // copy array
        // modify sort according  arrive
        Process[] work = new Process[num_processes];
        int k ;
        for(int i=0  ;i<num_processes;i++){
            k = i ;
            int mini = temp[i].getArrival_time();
            if(mini<0)continue ;
            for(int j=i+1 ;j<num_processes;j++){
                if(temp[j].getArrival_time()<0)continue ;
                if(temp[j].getArrival_time()<temp[k].getArrival_time())k=j ;
            }
            work[i] = new Process() ;
            work[i].setArrival_time(temp[k].getArrival_time());
            work[i].setBurst_time(temp[k].getBurst_time());
            work[i].setProcess_name(temp[k].getProcess_name());
            work[i].setCounter(0);
            temp[k].setArrival_time(-1)  ;
        }
        int wait = 0 ;
        int time = 0 ;
        while( total>0 ){
            for(int i=0 ;i<num_processes ;i=(i+1)%num_processes){
                //work[i].setWaiting_time(wait );
                if(work[i].getArrival_time()>=time&& work[i].getBurst_time()>0){
                    if(work[i].getBurst_time()>=quantum){
                        total-= quantum ;
                        time += quantum+context ;   // time += work[i].getBurst_time()+context ;
                        work[i].setWaiting_time( (wait-work[i].getCounter()));
                        work[i].setCounter((work[i].getCounter()+quantum+context));
                        work[i].setBurst_time( (work[i].getBurst_time()-quantum) ) ;
                        wait += quantum+context ;
                        if(work[i].getBurst_time()==0){
                            work[i].setCompletion_time(time);
                        }
                    }
                    else {
                        total -= work[i].getBurst_time() ;
                        time+= work[i].getBurst_time()+context  ;
                        work[i].setWaiting_time((wait-work[i].getCounter()));
                        work[i].setCounter((work[i].getCounter()+work[i].getBurst_time()));
                        wait += work[i].getBurst_time() + context ;
                        work[i].setBurst_time(0) ;
                        work[i].setCompletion_time(time);
                    }
                }
                else time++ ;
                if(total<=0)break ;
            }
            if(total<=0)break ;
        }
        double total_around = 0 ;
        double total_wait = 0 ;
        for(int i=0 ;i<num_processes ;i++){
            work[i].setWaiting_time((work[i].getWaiting_time()-work[i].getArrival_time()));
            work[i].setTurn_arroundime(work[i].getWaiting_time()+temp[i].getBurst_time()-work[i].getArrival_time());//work[i].getCompletion_time()-work[i].getArrival_time()
            total_around += work[i].getTurn_arroundime() ;
            total_wait += work[i].getWaiting_time() ;
        }
        for(int i=0 ;i<num_processes ;i++){
            System.out.println("name         :  " + work[i].getProcess_name());
            System.out.println("turnaround   :  " + work[i].getTurn_arroundime());
            System.out.println("waiting      :  " + work[i].getWaiting_time());
            System.out.println();
        }
        System.out.println(" average Turn around time    :  " + (total_around/num_processes));
        System.out.println(" average waiting time        :  " + (total_wait/num_processes));

    }
}
