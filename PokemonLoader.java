import java.io.*;
import java.util.*;

public class PokemonLoader {

    /**
     * Parses the semicolon-delimited CSV and returns a list of Pokemon.
     * Expected column order: ID; Name; Type1; Type2; Total; HP; Attack; Defense; Sp.Atk; Sp.Def; Speed
     */
    public static List<Pokemon> loadFromCSV(String filePath) {
        List<Pokemon> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                // Skip the header
                if (firstLine) { firstLine = false; continue; }

                // Remove Semicolons
                line = line.trim();
                if (line.endsWith(";")) line = line.substring(0, line.length() - 1);

                String[] parts = line.split(";");

                // Skip malformed rows
                if (parts.length < 11) continue;

                // Remove Quotes
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].replace("\"", "").trim();
                }

                int    id             = Integer.parseInt(parts[0]);
                String name           = parts[1];
                String type1          = parts[2];
                String type2          = parts[3]; // empty string if no 2nd type
                int    total          = Integer.parseInt(parts[4]);
                int    hp             = Integer.parseInt(parts[5]);
                int    attack         = Integer.parseInt(parts[6]);
                int    defense        = Integer.parseInt(parts[7]);
                int    specialAttack  = Integer.parseInt(parts[8]);
                int    specialDefense = Integer.parseInt(parts[9]);
                int    speed          = Integer.parseInt(parts[10]);

                list.add(new Pokemon(id, name, type1, type2,
                                     total, hp, attack, defense,
                                     specialAttack, specialDefense, speed));
            }

        } catch (IOException e) {
            System.err.println("Could not load CSV: " + e.getMessage());
        }

        return list;
    }
}
