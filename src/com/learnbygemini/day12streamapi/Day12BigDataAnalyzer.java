package com.learnbygemini.day12streamapi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.OptionalDouble;
import java.util.stream.Stream;

public class Day12BigDataAnalyzer {
    public static void analyzeLogFile(Path path) {
        // Log format: "TIMESTAMP | LEVEL | RESPONSE_TIME_MS | MESSAGE"

        // Point 10 & 8: Try-with-resources untuk File Stream
        try (Stream<String> lines = Files.lines(path)) {

            OptionalDouble average = lines
                    // Step 1: Filter -> keep only ERROR logs (lazy)
                    .filter(line -> line.contains("ERROR"))

                    // Step 2: Debug - See what passed filter (Point 9)
                    .peek(line -> System.out.println("Processing: " + line))

                    // Step 3: Transform -Split CSV
                    .map(line -> line.split("\\|"))

                    // Step 4: Validate - defensive programming
                    .filter(parts -> parts.length >= 3)

                    // Step 5: Extract & Trim - Ambil kolom ke-3
                    .map(parts -> parts[2].trim())

                    // Step : Primitive Conversion (Point 7)
                    // String -> primitive int (Performance Boost!)
                    .mapToInt(Integer::parseInt)

                    // Step 7: Terminal Op -> Trigger pipeline!
                    .average();

            System.out.println("Average Response Time: " + average.getAsDouble());
        } catch (IOException e) {
            System.err.println("Gagal membaca file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Data korup: Format angka salah.");
        }
    }
}
