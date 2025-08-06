import java.util.Scanner;

public class  NextFitAllocation {
    public static void main(String[] args) {
        final int MAX = 25;
        int[] frag = new int[MAX];
        int[] b = new int[MAX];
        int[] f = new int[MAX];
        int[] bf = new int[MAX]; // block flags (1 if allocated)
        int[] ff = new int[MAX]; // file to block mapping

        int nb, nf, temp;
        int lastAllocatedIndex = 1; // Start from first block

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

        // Next Fit Allocation
        for (int i = 1; i <= nf; i++) {
            boolean allocated = false;

            // Start searching from last allocated index
            int j = lastAllocatedIndex;
            int count = 0;

            while (count < nb) {
                if (bf[j] != 1 && b[j] >= f[i]) {
                    ff[i] = j;
                    frag[i] = b[j] - f[i];
                    bf[j] = 1; // mark block as allocated
                    lastAllocatedIndex = j; // update the pointer
                    allocated = true;
                    break;
                }
                j++;
                if (j > nb) j = 1; // wrap around
                count++;
            }

            if (!allocated) {
                ff[i] = 0; // not allocated
                frag[i] = -1; // invalid fragment
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
