
import java.util.Scanner;

public class FCFS {
    public static void main(String[] args) {
        int n = 5;
        int[] arrival = new int[n];
        int[] burst = new int[n];
        int[] completion = new int[n];
        int[] turnaround = new int[n];
        int[] waiting = new int[n];

        Scanner sc = new Scanner(System.in);

        // Input arrival and burst times
        for (int i = 0; i < n; i++) {
            System.out.println("Enter Arrival Time and Burst Time for Process " + (i + 1) + ":");
            arrival[i] = sc.nextInt();
            burst[i] = sc.nextInt();
        }

        // FCFS Scheduling
        int currentTime = 0;
        for (int i = 0; i < n; i++) {
            if (currentTime < arrival[i]) {
                currentTime = arrival[i];
            }
            completion[i] = currentTime + burst[i];
            turnaround[i] = completion[i] - arrival[i];
            waiting[i] = turnaround[i] - burst[i];
            currentTime = completion[i];
        }

        // Calculate averages
        double totalTAT = 0, totalWT = 0;
        for (int i = 0; i < n; i++) {
            totalTAT += turnaround[i];
            totalWT += waiting[i];
        }
        double avgTAT = totalTAT / n;
        double avgWT = totalWT / n;

        // Output
        System.out.println("\nPID\tArrival\tBurst\tCompletion\tTurnaround\tWaiting");
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "\t" + arrival[i] + "\t" + burst[i] + "\t" +
                    completion[i] + "\t\t" + turnaround[i] + "\t\t" + waiting[i]);
        }

        System.out.printf("\nAverage Turnaround Time = %.2f\n", avgTAT);
        System.out.printf("Average Waiting Time = %.2f\n", avgWT);
    }
}


