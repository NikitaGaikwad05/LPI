import java.util.*;

public class PageReplacement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();

        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();

        int[] pages = new int[n];
        System.out.println("Enter the page reference string:");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        System.out.println("\n--- FIFO Page Replacement ---");
        fifo(pages, frames);

        System.out.println("\n--- LRU Page Replacement ---");
        lru(pages, frames);

        System.out.println("\n--- Optimal Page Replacement ---");
        optimal(pages, frames);

        sc.close();
    }

    // 🔹 FIFO Algorithm
    static void fifo(int[] pages, int frames) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        int faults = 0;

        for (int page : pages) {
            if (!set.contains(page)) {
                if (set.size() == frames) {
                    int removed = queue.poll();
                    set.remove(removed);
                }
                queue.add(page);
                set.add(page);
                faults++;
            }
            System.out.println("Frames: " + queue + (set.contains(page) ? "" : " (Fault)"));
        }
        System.out.println("Total Page Faults (FIFO): " + faults);
    }

    // 🔹 LRU Algorithm
    static void lru(int[] pages, int frames) {
        List<Integer> list = new ArrayList<>();
        int faults = 0;

        for (int page : pages) {
            if (!list.contains(page)) {
                if (list.size() == frames)
                    list.remove(0);
                list.add(page);
                faults++;
            } else {
                list.remove((Integer) page);
                list.add(page);
            }
            System.out.println("Frames: " + list + (list.contains(page) ? "" : " (Fault)"));
        }
        System.out.println("Total Page Faults (LRU): " + faults);
    }

    // 🔹 Optimal Algorithm
    static void optimal(int[] pages, int frames) {
        List<Integer> list = new ArrayList<>();
        int faults = 0;

        for (int i = 0; i < pages.length; i++) {
            int page = pages[i];
            if (!list.contains(page)) {
                if (list.size() == frames) {
                    int replaceIndex = findOptimalIndex(list, pages, i + 1);
                    list.set(replaceIndex, page);
                } else {
                    list.add(page);
                }
                faults++;
            }
            System.out.println("Frames: " + list + (list.contains(page) ? "" : " (Fault)"));
        }
        System.out.println("Total Page Faults (Optimal): " + faults);
    }

    static int findOptimalIndex(List<Integer> list, int[] pages, int start) {
        int farthest = start, index = 0;
        for (int i = 0; i < list.size(); i++) {
            int j;
            for (j = start; j < pages.length; j++) {
                if (list.get(i) == pages[j]) {
                    if (j > farthest) {
                        farthest = j;
                        index = i;
                    }
                    break;
                }
            }
            if (j == pages.length)
                return i;
        }
        return index;
    }
}
