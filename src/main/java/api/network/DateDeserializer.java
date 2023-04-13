package api.network;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import api.utils.DateUtil;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DateDeserializer implements JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

        LocalDateTime date = null;

        try {
            date = DateUtil.StringToDate(json.getAsString());
        } catch (UnsupportedOperationException | JsonParseException | DateTimeParseException ignored) { }

        return date;
    }

}
