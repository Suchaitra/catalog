import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class Abc {
    public static void main(String[] args) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("input.json"));

            System.out.println("Secret C for Test Case 1: " + calculateSecret(jsonObject, 1));
            System.out.println("Secret C for Test Case 2: " + calculateSecret(jsonObject, 2));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static BigInteger calculateSecret(JSONObject jsonObject, int testCaseNumber) {
        JSONObject testCase = (JSONObject) jsonObject.get(String.valueOf(testCaseNumber));
        JSONObject keys = (JSONObject) testCase.get("keys");
        long k = (long) keys.get("k");

        BigInteger secretC = BigInteger.ZERO;

        for (long i = 1; i <= k; i++) {
            JSONObject root = (JSONObject) testCase.get(String.valueOf(i));
            int base = Integer.parseInt((String) root.get("base"));
            String value = (String) root.get("value");

            BigInteger decodedY = new BigInteger(value, base);
            secretC = secretC.add(decodedY);
        }

        return secretC;
    }
}