import java.util.*;

public class FCFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] at = new int[n], bt = new int[n], ct = new int[n], tat = new int[n], wt = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Arrival Time of P" + (i + 1) + ": ");
            at[i] = sc.nextInt();
            System.out.print("Enter Burst Time of P" + (i + 1) + ": ");
            bt[i] = sc.nextInt();
        }

        // Sort by arrival time
        for (int i = 0; i < n - 1; i++)
            for (int j = i + 1; j < n; j++)
                if (at[j] < at[i]) {
                    int temp = at[i]; 
                    at[i] = at[j];
                     at[j] = temp;
                    temp = bt[i]; 
                    bt[i] = bt[j];
                     bt[j] = temp;
                }

        int time = 0;
        for (int i = 0; i < n; i++) {
            if (time < at[i]) time = at[i];
            time += bt[i];
            ct[i] = time;
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
        }

        System.out.println("\nP\tAT\tBT\tCT\tTAT\tWT");
        int totalTAT = 0, totalWT = 0;
        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i + 1) + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
            totalTAT += tat[i]; 
            totalWT += wt[i];
        }

        System.out.printf("\nAvg TAT = %.2f, Avg WT = %.2f\n", (double) totalTAT / n, (double) totalWT / n);
        sc.close();
    }
}

