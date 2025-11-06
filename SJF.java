import java.util.*;

public class SJF{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] at = new int[n];   // Arrival time
        int[] bt = new int[n];   // Burst time
        int[] rt = new int[n];   // Remaining time
        int[] ct = new int[n];   // Completion time
        int[] tat = new int[n];  // Turnaround time
        int[] wt = new int[n];   // Waiting time

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Arrival Time of P" + (i + 1) + ": ");
            at[i] = sc.nextInt();
            System.out.print("Enter Burst Time of P" + (i + 1) + ": ");
            bt[i] = sc.nextInt();
            rt[i] = bt[i];
        }

        int completed = 0, time = 0;
        while (completed < n) {
            int shortest = -1;
            int min = Integer.MAX_VALUE;

            // find process with smallest remaining time that has arrived
            for (int i = 0; i < n; i++) {
                if (at[i] <= time && rt[i] < min && rt[i] > 0) {
                    min = rt[i];
                    shortest = i;
                }
            }

            if (shortest == -1) { // no process arrived yet
                time++;
                continue;
            }

            rt[shortest]--;  // execute process for 1 unit
            time++;

            if (rt[shortest] == 0) { // process finished
                completed++;
                ct[shortest] = time;
                tat[shortest] = ct[shortest] - at[shortest];
                wt[shortest] = tat[shortest] - bt[shortest];
            }
        }

        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
        int totalTAT = 0, totalWT = 0;
        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i + 1) + "\t" + at[i] + "\t" + bt[i] + "\t" +
                               ct[i] + "\t" + tat[i] + "\t" + wt[i]);
            totalTAT += tat[i];
            totalWT += wt[i];
        }

        System.out.printf("\nAverage Turnaround Time: %.2f", (double) totalTAT / n);
        System.out.printf("\nAverage Waiting Time: %.2f\n", (double) totalWT / n);

        sc.close();
    }
}
