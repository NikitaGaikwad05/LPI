import java.util.*;

public class MemoryAllocation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of memory blocks: ");
        int m = sc.nextInt();
        int[] blockSize = new int[m];
        for (int i = 0; i < m; i++) {
            System.out.print("Enter size of Block " + (i + 1) + ": ");
            blockSize[i] = sc.nextInt();
        }

        System.out.print("\nEnter number of processes: ");
        int n = sc.nextInt();
        int[] processSize = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter size of Process " + (i + 1) + ": ");
            processSize[i] = sc.nextInt();
        }

        // Perform all three allocations
        System.out.println("\n--- First Fit Allocation ---");
        allocate(blockSize.clone(), processSize, "first");

        System.out.println("\n--- Best Fit Allocation ---");
        allocate(blockSize.clone(), processSize, "best");

        System.out.println("\n--- Worst Fit Allocation ---");
        allocate(blockSize.clone(), processSize, "worst");

        sc.close();
    }

    // Function for allocation based on strategy type
    static void allocate(int[] blocks, int[] processes, String type) {
        int[] allocation = new int[processes.length];
        Arrays.fill(allocation, -1);

        for (int i = 0; i < processes.length; i++) {
            int chosenBlock = -1;

            for (int j = 0; j < blocks.length; j++) {
                if (blocks[j] >= processes[i]) {
                    if (type.equals("first")) { // First Fit
                        chosenBlock = j;
                        break;
                    } else if (type.equals("best")) { // Best Fit
                        if (chosenBlock == -1 || blocks[j] < blocks[chosenBlock])
                            chosenBlock = j;
                    } else if (type.equals("worst")) { // Worst Fit
                        if (chosenBlock == -1 || blocks[j] > blocks[chosenBlock])
                            chosenBlock = j;
                    }
                }
            }

            if (chosenBlock != -1) {
                allocation[i] = chosenBlock + 1;
                blocks[chosenBlock] -= processes[i];
            }
        }

        System.out.println("Process\tSize\tBlock Allocated");
        for (int i = 0; i < processes.length; i++) {
            if (allocation[i] != -1)
                System.out.println("P" + (i + 1) + "\t" + processes[i] + "\tB" + allocation[i]);
            else
                System.out.println("P" + (i + 1) + "\t" + processes[i] + "\tNot Allocated");
        }
    }
}
