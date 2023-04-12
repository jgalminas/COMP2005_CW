package api.network;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import api.utils.DateUtil;
import java.lang.reflect.Type;
import java.time.LocalDateTime;

public class DateDeserializer implements JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return DateUtil.StringToDate(json.getAsString());
    }

}
