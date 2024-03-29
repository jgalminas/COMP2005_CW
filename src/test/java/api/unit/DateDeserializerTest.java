package api.unit;

import api.network.DateDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class DateDeserializerTest {

    DateDeserializer deserializer = new DateDeserializer();

    @Test
    void testDeserialization_NullDateString() {

        // arrange
        JsonElement json = JsonNull.INSTANCE;

        // act
        LocalDateTime actual = deserializer.deserialize(json, null, null);

        // assert
        assertNull(actual);
    }

    @Test
    void testDeserialization_InvalidDateString() {

        // arrange
        String dateString = "";
        JsonElement json = new JsonPrimitive(dateString);

        // act
        LocalDateTime actual = deserializer.deserialize(json, null, null);

        // assert
        assertNull(actual);
    }

    @Test
    void testDeserialization_ValidDateString() {

        // arrange
        String dateString = "2023-04-11T10:30:00";
        JsonElement json = new JsonPrimitive(dateString);
        LocalDateTime expected = LocalDateTime.of(2023, Month.APRIL, 11, 10, 30, 0);

        // act
        LocalDateTime actual = deserializer.deserialize(json, null, null);

        // assert
        assertEquals(expected, actual);
    }


}