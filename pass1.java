import java.util.*;
public class pass1 {

static int locCounter = 0;
static Map<String, Integer> symbolTable = new HashMap<>(); 
 static List<String> intermediateCode = new ArrayList<>();

static Set<String> instructions = Set.of("ADD", "SUB", "MOV"); 
 static Set<String> directives = Set.of("START", "END", "DC", "DS");

public static void main(String[] args) { String[] program = {
"START 100", "LOOP ADD A", "SUB B",
"MOV C",
"A DC 5",
"B DS 1", "END"
};

for (String line : program) { processLine(line.trim());
}

System.out.println("Symbol Table:");
symbolTable.forEach((sym, addr) -> System.out.println(sym + "\t" + addr));

System.out.println("\nIntermediate Code:");
 intermediateCode.forEach(System.out::println);
}

static void processLine(String line) { 
 String[] parts = line.split("\\s+"); int index = 0;

// Handle START directive
if (parts[0].equals("START")) {
locCounter = Integer.parseInt(parts[1]);
 intermediateCode.add("(AD,START) (C," + parts[1] + ")");
 return;
}

// Check if first part is label (not instruction/directive)
if (!instructions.contains(parts[0]) && !directives.contains(parts[0])) {
 
symbolTable.put(parts[0], locCounter);
 index++;
}

if (index >= parts.length) return; 
 String mnemonic = parts[index];
 index++;

if (instructions.contains(mnemonic)) {
intermediateCode.add("(IS," + mnemonic + ") " + (index < parts.length ? parts[index] : ""));
locCounter++;
} else if (mnemonic.equals("DC"))
{ intermediateCode.add("(DL,DC) (C," + parts[index] + ")"); 
  locCounter++;
} else if (mnemonic.equals("DS")) 
{ intermediateCode.add("(DL,DS) (C," + parts[index] + ")"); 
  locCounter += Integer.parseInt(parts[index]);
} else if (mnemonic.equals("END")) {
 intermediateCode.add("(AD,END)");
}
}
}



