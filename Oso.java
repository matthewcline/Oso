import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.HashMap;
import java.lang.Integer;
import java.util.Scanner;
import java.util.Stack;
import java.util.Arrays;
import java.nio.file.StandardCopyOption;

/**
 * Oso: a Spanish Vocabulary Quizzer.
 * @author Matthew Cline
 */

public class Oso {

    public static void main(String[] args) {

        /**
         * Store the Spanish words and their equivalent hint.
         */
        HashMap<String, String> wordsWithHints = new HashMap<String, String>();
        wordsWithHints.put("Aburrido", "Oh no another burrito!  I'm so bored with burritos!");
        String command = args[0];
        switch (command) {
            case "init":
                File gitlet = new File(".gitlet"); 
                if (gitlet.exists()) {
                    System.out.println("An Oso system already exists in the current directory.");
                    return;
                } else {
                    gitlet.mkdir();
                    HashSet<String> newListStaged = new HashSet<String>();
                    saveFileStaged(newListStaged);
                    HashSet<String> newListRemoved = new HashSet<String>();
                    saveFileRemoved(newListRemoved);
                    CommitTree firstCommit = new CommitTree();
                    saveFileCommitted(firstCommit);
                    return;
                }
            case "i-rebase":
                return;
        }
    }

    /**
     * Reads in a set of files and adds it to STAGED.SER to be included in the next commit.
     * I got the basic idea for the structure of this method 
     * from Sarah Kim @ http://www.sarahjikim.com/cs61b
     */
    private static void saveFileStaged(HashSet<String> staged) {
        if (staged == null) {
            return;
        }
        try {
            FileOutputStream fileOutput = new FileOutputStream(".gitlet/staged.ser");
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(staged);
            objectOutput.close();
        } catch (IOException e) {
            System.out.println("IOException while saving File.");
        }
    }
}
