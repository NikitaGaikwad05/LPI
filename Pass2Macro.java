import java.util.*;

class Pass2Macro {
    public static void main(String[] args) {
        // ----- Macro Name Table (MNT): macro name -> MDT index -----
        Map<String, Integer> MNT = new HashMap<>();
        MNT.put("ADD", 0);

        // ----- Arguments of macro -----
        Map<String, List<String>> MNTArgs = new HashMap<>();
        MNTArgs.put("ADD", Arrays.asList("A", "B"));

        // ----- Macro Definition Table (MDT) -----
        List<String> MDT = Arrays.asList(
            "ADD &A,&B",
            "MOVER AREG,&A",
            "ADD AREG,&B",
            "MEND"
        );

        // ----- Input Program (no macro definitions) -----
        String[] program = {
            "START 100",
            "ADD X,Y",
            "PRINT Z",
            "ADD P,Q",
            "END"
        };

        // ----- Pass-II: Expand macros -----
        System.out.println("--- EXPANDED CODE ---");
        for (String line : program) {
            String[] parts = line.split(" ", 2);
            String word = parts[0];

            if (MNT.containsKey(word)) {
                int start = MNT.get(word);
                String[] actualArgs = parts[1].split(",");
                List<String> formals = MNTArgs.get(word);

                for (int i = start; i < MDT.size(); i++) {
                    String mLine = MDT.get(i);
                    if (mLine.equals("MEND")) break;

                    String expanded = mLine;
                    for (int j = 0; j < formals.size(); j++)
                        expanded = expanded.replace("&" + formals.get(j), actualArgs[j]);

                    if (!expanded.startsWith(word))  // skip macro header line
                        System.out.println(expanded);
                }
            } else {
                System.out.println(line);
            }
        }
    }
}
