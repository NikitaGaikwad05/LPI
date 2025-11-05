import java.util.concurrent.Semaphore;
@SuppressWarnings("deprecation")
public class ReaderWriter {
    // Create semaphores for synchronization
    static Semaphore mutex = new Semaphore(1);      // Controls access to readCount
    static Semaphore writeLock = new Semaphore(1);  // Ensures mutual exclusion for writers
    static int readCount = 0;                       // Number of active readers

    // Reader class
    static class Reader extends Thread {
        public void run() {
            try {
                // Entry section for readers
                mutex.acquire();            // Lock before modifying readCount
                readCount++;
                if (readCount == 1)
                    writeLock.acquire();    // First reader locks writers
                mutex.release();

                // Critical section
                System.out.println("Reader " + this.getId() + " is READING...");
                Thread.sleep(1000);
                System.out.println("Reader " + this.getId() + " has FINISHED reading.");

                // Exit section for readers
                mutex.acquire();
                readCount--;
                if (readCount == 0)
                    writeLock.release();    // Last reader unlocks writers
                mutex.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Writer class
    static class Writer extends Thread {
        public void run() {
            try {
                writeLock.acquire();        // Lock before writing
                System.out.println("Writer " + this.getId() + " is WRITING...");
                Thread.sleep(1000);
                System.out.println("Writer " + this.getId() + " has FINISHED writing.\n");
                writeLock.release();        // Unlock after writing
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        Reader r1 = new Reader();
        Reader r2 = new Reader();
        Writer w1 = new Writer();
        Reader r3 = new Reader();
        Writer w2 = new Writer();

        // Start all threads
        r1.start();
        r2.start();
        w1.start();
        r3.start();
        w2.start();
    }
}
