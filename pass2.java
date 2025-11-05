import java.util.*;

class Pass2 {
    public static void main(String[] args) {
        // ----- Symbol Table -----
        Map<String, Integer> symtab = new HashMap<>();
        symtab.put("A", 101);
        symtab.put("B", 102);
        symtab.put("C", 103);

        System.out.println("--- SYMBOL TABLE ---");
        for (String s : symtab.keySet())
            System.out.println(s + " -> " + symtab.get(s));

        // ----- Intermediate Code -----
        String[] code = {
            "(AD,START) (C,100)",
            "(IS,01) A",
            "(IS,02) B",
            "(DL,DC) (C,5)",
            "(DL,DS) (C,1)",
            "(AD,END)"
        };

        // ----- Machine Code Generation -----
        int lc = 0;
        System.out.println("\n--- MACHINE CODE ---");
        System.out.println("Loc\tOpcode\tOperand");

        for (String line : code) {
            if (line.contains("START"))
                lc = getConst(line);
            else if (line.contains("IS")) {
                String op = line.substring(4, 6);
                String sym = line.split(" ")[1];
                System.out.println(lc++ + "\t" + op + "\t" + symtab.get(sym));
            } 
            else if (line.contains("DC"))
                System.out.println(lc++ + "\t00\t" + getConst(line));
            else if (line.contains("DS"))
                System.out.println(lc++ + "\t00\t0");
        }
    }

    static int getConst(String s) {
        int i = s.indexOf("(C,") + 3;
        return Integer.parseInt(s.substring(i, s.indexOf(")", i)));
    }
}
