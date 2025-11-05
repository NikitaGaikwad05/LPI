import java.util.*;

public class PriorityScheduling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] at = new int[n], bt = new int[n], pr = new int[n], ct = new int[n], tat = new int[n], wt = new int[n];
        boolean[] done = new boolean[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter AT, BT, and Priority for P" + (i + 1) + ": ");
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
            pr[i] = sc.nextInt();
        }

        int time = 0, doneCount = 0;
        while (doneCount < n) {
            int idx = -1, minPr = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (!done[i] && at[i] <= time && pr[i] < minPr) {
                    minPr = pr[i];
                    idx = i;
                }
            }
            if (idx == -1) { time++; continue; }
            time += bt[idx];
            ct[idx] = time;
            tat[idx] = ct[idx] - at[idx];
            wt[idx] = tat[idx] - bt[idx];
            done[idx] = true;
            doneCount++;
        }

        System.out.println("\nP\tAT\tBT\tPR\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++)
            System.out.println("P" + (i + 1) + "\t" + at[i] + "\t" + bt[i] + "\t" + pr[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
        sc.close();
    }
}