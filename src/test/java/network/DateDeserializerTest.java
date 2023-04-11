package network;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class DateDeserializerTest {

    DateDeserializer deserializer = new DateDeserializer();

    @Test
    void testDeserialization_InvalidDateString() {

        String dateString = "";
        JsonElement json = new JsonPrimitive(dateString);

        assertThrows(DateTimeParseException.class, () -> deserializer.deserialize(json, null, null));
    }

    @Test
    void testDeserialization_ValidDateString() {

        String dateString = "2023-04-11T10:30:00";
        JsonElement json = new JsonPrimitive(dateString);

        LocalDateTime expected = LocalDateTime.of(2023, Month.APRIL, 11, 10, 30, 0);
        LocalDateTime actual = deserializer.deserialize(json, null, null);

        assertEquals(expected, actual);
    }


}