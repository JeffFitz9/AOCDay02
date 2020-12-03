import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * AOC Day2
 * Passwordpolicy
 * 
 * @author Jeff Fitzgerald
 * @version 12/02/2020
 */

public class AOCDay2 {

    /**
     * Reads the input file of policies and passwords.
     * 
     * @param filename name of file to read
     * @return an array of input values
     */
    public static String[] readFile(String fileName) {

        ArrayList<String> passwords = new ArrayList<>();
        Scanner inFile = null;

        try {
            inFile = new Scanner(new File(fileName));
            
            while (inFile.hasNext()) {
                passwords.add(inFile.nextLine());
            }

        } catch (IOException e) {
            System.out.println("Error reading input file: " + fileName);
            System.exit(0);

        } finally {
            if (inFile != null) {
                inFile.close();
            }
        }

        String[] passwordArray = new String[passwords.size()]; 
        passwordArray = passwords.toArray(passwordArray); 
        return passwordArray;     
    }

    /**
     * Checks the given password against the password policy to determine if it is a valid password.
     * 
     * @param passwordAndPolicy String holding the values of minimum, maximum, character for policy, and the password to be checked.
     * @return boolean of whether the password is valid.
     */
    public static boolean checkValidPassword(String passwordAndPolicy) {
        int min; // minimum number of characters required.
        int max; // maximum number of characters required.
        char character; // character to check if it meets the min and max required.
        String password; // password to check.
        int matchingCharacters = 0;
        boolean validPassword = false;

        // Split the string into values
        String[] tokens = passwordAndPolicy.split("[- ]");

        min = Integer.parseInt(tokens[0]);
        max = Integer.parseInt(tokens[1]);
        character = tokens[2].charAt(0);
        password = tokens[3];
        
        // check if password has a length
        if(password.length() > 0) {
            // compare all characters against min/max character.
            for(int j = 0; j < password.length(); j++) {
                if(character == password.charAt(j)) {
                    matchingCharacters++;
                }
            }
            if(matchingCharacters >= min && matchingCharacters <= max) {
                validPassword = true;
            }
        }
        return validPassword;
    }
    
    public static void main(String[] args) {
        String fileName = "AOCDay2Input.txt";
        String[] allPasswords = readFile(fileName);
        int validPasswords = 0;

        for(int i = 0; i < allPasswords.length; i++){
            if(checkValidPassword(allPasswords[i])) {
                validPasswords++;
            }
        }

        System.out.println("Th number of valid passwords is " + validPasswords);

    }
}