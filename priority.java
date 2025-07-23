import java.util.*;

public class PriorityScheduling {

    static class Process {
        int pid;
        int burstTime;
        int priority;

        Process(int pid, int burstTime, int priority) {
            this.pid = pid;
            this.burstTime = burstTime;
            this.priority = priority;
        }
    }

    public static void main(String[] args) {
        List<Process> processes = new ArrayList<>();
        processes.add(new Process(1, 10, 2));
        processes.add(new Process(2, 5, 0));
        processes.add(new Process(3, 8, 1));
        PriorityQueue<Process> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.priority));

       
        pq.addAll(processes);

        int currentTime = 0;
        System.out.println("PID\tPriority\tBurst Time\tStart Time\tEnd Time");

        while (!pq.isEmpty()) {
            Process p = pq.poll(); 
            int startTime = currentTime;
            int endTime = startTime + p.burstTime;
            currentTime = endTime;

            System.out.println(p.pid + "\t" + p.priority + "\t\t" + p.burstTime +
                               "\t\t" + startTime + "\t\t" + endTime);
        }
    }
}
