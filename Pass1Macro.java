import java.util.*;

class Pass1Macro {
    public static void main(String[] args) {
        // Data Structures
        Map<String, Integer> MNT = new HashMap<>();
        List<String> MDT = new ArrayList<>();
        List<String> ALA = new ArrayList<>();

        // Input Program
        String[] code = {
            "MACRO",
            "ADD &A,&B",
            "MOVER AREG,&A",
            "ADD AREG,&B",
            "MEND",
            "START 100",
            "ADD X,Y",
            "END"
        };

        boolean inMacro = false;
        int mdtIndex = 0;

        // Process Pass-I
        for (String line : code) {
            String[] parts = line.split(" ");
            if (line.equals("MACRO")) {
                inMacro = true;
            } 
            else if (inMacro && !line.equals("MEND")) {
                if (!MNT.containsKey(parts[0])) {
                    MNT.put(parts[0], mdtIndex); // store macro name and index
                    for (String arg : parts[1].split(",")) {
                        ALA.add(arg.replace("&", "")); // store arguments
                    }
                }
                MDT.add(line);
                mdtIndex++;
            } 
            else if (line.equals("MEND")) {
                MDT.add("MEND");
                mdtIndex++;
                inMacro = false;
            }
        }

        // Display Tables
        System.out.println("--- MACRO NAME TABLE (MNT) ---");
        for (String key : MNT.keySet())
            System.out.println(key + " -> " + MNT.get(key));

        System.out.println("\n--- ARGUMENT LIST ARRAY (ALA) ---");
        for (int i = 0; i < ALA.size(); i++)
            System.out.println((i + 1) + ": " + ALA.get(i));

        System.out.println("\n--- MACRO DEFINITION TABLE (MDT) ---");
        for (int i = 0; i < MDT.size(); i++)
            System.out.println(i + ": " + MDT.get(i));
    }
}
