import java.util.*;

public class PageFifo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of frames: ");
        int f = sc.nextInt();
        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();

        int[] pages = new int[n];
        System.out.println("Enter page reference string:");
        for (int i = 0; i < n; i++) pages[i] = sc.nextInt();

        int[] frame = new int[f];
        Arrays.fill(frame, -1);
        int faults = 0, pos = 0;

        System.out.println("\nPage\tFrames\t\tFault");
        for (int p : pages) {
            boolean hit = false;
            for (int j = 0; j < f; j++)
                if (frame[j] == p) hit = true;

            if (!hit) { // page fault
                frame[pos] = p;
                pos = (pos + 1) % f;
                faults++;
            }

            System.out.print(p + "\t");
            for (int x : frame) System.out.print((x == -1 ? "-" : x) + " ");
            System.out.println(hit ? "\tNo" : "\tYes");
        }
        System.out.println("\nTotal Page Faults: " + faults);
        sc.close();
    }
}
