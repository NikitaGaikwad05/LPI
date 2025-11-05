import java.util.*;

class Process {
    int id, at, bt, rt, ct, tat, wt;
    Process(int id, int at, int bt) {
        this.id = id; this.at = at; this.bt = bt; this.rt = bt;
    }
}

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        Process[] p = new Process[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Arrival and Burst Time for P" + (i + 1) + ": ");
            p[i] = new Process(i + 1, sc.nextInt(), sc.nextInt());
        }
        System.out.print("Enter Time Quantum: ");
        int q = sc.nextInt();

        Queue<Process> qList = new LinkedList<>();
        int time = 0, completed = 0;
        boolean[] added = new boolean[n];

        while (completed < n) {
            for (int i = 0; i < n; i++)
                if (p[i].at <= time && p[i].rt > 0 && !added[i]) {
                    qList.add(p[i]); added[i] = true;
                }

            if (qList.isEmpty()) { time++; continue; }
            Process curr = qList.poll();
            int exec = Math.min(curr.rt, q);
            curr.rt -= exec;
            time += exec;

            for (int i = 0; i < n; i++)
                if (p[i].at <= time && p[i].rt > 0 && !added[i]) {
                    qList.add(p[i]); added[i] = true;
                }

            if (curr.rt > 0)
                qList.add(curr);
            else {
                curr.ct = time;
                curr.tat = curr.ct - curr.at;
                curr.wt = curr.tat - curr.bt;
                completed++;
            }
        }

        System.out.println("\nP\tAT\tBT\tCT\tTAT\tWT");
        for (Process proc : p)
            System.out.println("P" + proc.id + "\t" + proc.at + "\t" + proc.bt + "\t" +
                    proc.ct + "\t" + proc.tat + "\t" + proc.wt);
        sc.close();
    }
}
