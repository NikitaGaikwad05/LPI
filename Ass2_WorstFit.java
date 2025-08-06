import java.util.Scanner;

public class WorstFitAllocation {
    public static void main(String[] args) {
        final int MAX = 25;
        int[] frag = new int[MAX];
        int[] b = new int[MAX];
        int[] f = new int[MAX];
        int[] bf = new int[MAX]; // block flags (1 if allocated)
        int[] ff = new int[MAX]; // file to block mapping

        int nb, nf, temp, highest;

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of blocks: ");
        nb = sc.nextInt();

        System.out.print("Enter the number of files: ");
        nf = sc.nextInt();

        System.out.println("\nEnter the size of the blocks:");
        for (int i = 1; i <= nb; i++) {
            System.out.print("Block " + i + ": ");
            b[i] = sc.nextInt();
        }

        System.out.println("Enter the size of the files:");
        for (int i = 1; i <= nf; i++) {
            System.out.print("File " + i + ": ");
            f[i] = sc.nextInt();
        }

        // Worst Fit Logic
        for (int i = 1; i <= nf; i++) {
            highest = -1;
            for (int j = 1; j <= nb; j++) {
                if (bf[j] != 1) { // if block is not allocated
                    temp = b[j] - f[i];
                    if (temp >= 0 && temp > highest) {
                        ff[i] = j;
                        highest = temp;
                    }
                }
            }

            frag[i] = highest;
            if (ff[i] != 0) {
                bf[ff[i]] = 1; // mark block as allocated
            }
        }

        // Output
        System.out.println("\nFile No\tFile Size\tBlock No\tBlock Size\tFragment");
        for (int i = 1; i <= nf; i++) {
            if (ff[i] != 0) {
                System.out.println(i + "\t\t" + f[i] + "\t\t" + ff[i] + "\t\t" + b[ff[i]] + "\t\t" + frag[i]);
            } else {
                System.out.println(i + "\t\t" + f[i] + "\t\tNot Allocated");
            }
        }

        sc.close();
    }
}
