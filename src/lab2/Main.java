package lab2;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input processing file name(relative to hseJavaLabs dir, or full)");
        String fileInName = scanner.nextLine();
        File fileIn = new File(fileInName);

        System.out.println("Input output file name(relative to hseJavaLabs dir, or full)");
        String fileOutName = scanner.nextLine();
        File fileOut = new File(fileOutName);

        scanner.close();

        if (!fileIn.exists()) {
            throw new FileNotFoundException("File with name " + fileInName + " not found.");
        }

        if (!fileOut.exists()) {
            try {
                fileOut.getParentFile().mkdirs();
                fileOut.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Out file was created!");
        }

        Map<Character, Integer> charsMapping = new HashMap<>();
        Scanner scannerFileIn = new Scanner(fileIn);
        while (scannerFileIn.hasNextLine()) {
            String line = scannerFileIn.nextLine();
            line.chars()
                    .filter(c -> (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
                    .forEach(c -> charsMapping.merge((char) c, 1, Integer::sum));
        }
        scanner.close();

        try(FileWriter writer = new FileWriter(fileOutName, false)) {
            writer.write(charsMapping.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done!");
    }
}
