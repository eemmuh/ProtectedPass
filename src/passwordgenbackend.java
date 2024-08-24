import java.util.Random;


public class passwordgenbackend {

    //randomly selected from these options
    public static final String lowercase = "abcdefghijklmnopqrstuvwxyz";
    public static final String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String numbers = "0123456789";
    public static final String symbols = "!@#$%^&*()-_=+{}[]|;:',<.>/?";


    private final Random randomgen;
    public passwordgenbackend() {
        randomgen = new Random();
    }

    public String generatePass(int length, boolean includeUppercase, boolean includeLowercase, boolean includeNumbers,
                                   boolean includeSpecialSymbols) {

        StringBuilder passBuilder = new StringBuilder();

        // store valid characters
        String validCharacters = "";
        if(includeUppercase) validCharacters += uppercase;
        if(includeLowercase) validCharacters += lowercase;
        if(includeNumbers) validCharacters += numbers;
        if(includeSpecialSymbols) validCharacters += symbols;

        // build password
        for(int i = 0; i < length; i++) {

            // generate a random index
            int randomIndex = randomgen.nextInt(validCharacters.length());

            // get the char based on the random index
            char randomChar = validCharacters.charAt(randomIndex);

            // store char into password builder
            passBuilder.append(randomChar);

            // continue until reach length that the user has provided
        }

        // return the result
        return passBuilder.toString();
    }

}
