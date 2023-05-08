package edu.guilford;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class ReadTextFile {
    public static void main(String[] args) throws IOException {
        Path dataLocation = null; // what's our data file?
        String fileName = null;

        PriorityQueue<String> fileQueue = new PriorityQueue<String>();

        Set<String> fileSet = new TreeSet<String>();

        // open the file and read the data
        try {
            Path locationPath = Paths.get(ReadTextFile.class
                    .getResource("/" + "Artist-Biography.txt").toURI());
            // create a file reader
            // FileReader fileReader = new FileReader(locationPath.toFile());
            // create a buffered reader
            BufferedReader bufferedReader = new BufferedReader(new FileReader(locationPath.toFile()));
            // read the data from the file
            String line = bufferedReader.readLine();
            PrintWriter fileQueuePolled = new PrintWriter(new FileWriter("output_queue_polled.txt"));
            // while reading the line, split the line into words and add them to a queue
            // exclude punctuation, and make all words lowercase
            // let the natural order of the words be alphabetical
            while (line != null) {
                // split the line into words
                String[] words = line.split("\\W+");
                // for each word in the line
                for (int i = 0; i < words.length; i++) {
                    String word = words[i];
                    // remove the non-alphabetic characters
                    word = word.replaceAll("[^a-zA-Z]", "");
                    if (!word.isEmpty()) {
                        fileQueue.add(word.toLowerCase());
                        fileSet.add(word.toLowerCase());
                    }

                }
                line = bufferedReader.readLine();
            }
            // print the queue to a file
            while (!fileQueue.isEmpty()) {
                fileQueuePolled.println(fileQueue.poll());
            }
            fileQueuePolled.close();
            // take the output and remove duplicates, using the WordCount class to count the
            // words
            // and the compareTo method to sort the words
            // print the set to a file
            PrintWriter fileSetPrint = new PrintWriter(new FileWriter("output_set.txt"));
            for (String word : fileSet) {
                // add the count of each word to the file using the WordCount class
                // print the word and its count to the file
                WordCount wordCount = new WordCount(word, Collections.frequency(fileSet, word));
                int thewordCount = wordCount.getCount();
                // the word count should be stored before the duplicatea are removed but I am
                // unsure how to do this without errors so
                // I left it here
                fileSetPrint.println(wordCount.getWord() + " " + thewordCount);
            }
            fileSetPrint.close();
        } catch (URISyntaxException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        // prompt the user for a word
        // search the set for the word
        // if the word is found, print the word and its count to the console
        // if the word is not found, print a message to the console
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a word to search for: ");
        String searchWord = scanner.nextLine();
        if (fileSet.contains(searchWord)) {
            System.out.println("The word " + searchWord + " appears " + Collections.frequency(fileSet, searchWord)
                    + " times in the file.");
        } else {
            System.out.println("The word " + searchWord + " does not appear in the file.");
        }
        scanner.close();
    }
}
