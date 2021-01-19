package practical.chapter8;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ParsingDate {
    public static void main(String[] args) {
        String year = "2019";
        String month = "03";
        String day = "14";
        String input = year + ' ' + month + ' ' + day;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
            // Parsing
            LocalDate date = LocalDate.parse(input, formatter);
            System.out.printf("%s%n", date.toString());
        }
        catch(DateTimeParseException e) {
            e.printStackTrace();
        }

    }
}
