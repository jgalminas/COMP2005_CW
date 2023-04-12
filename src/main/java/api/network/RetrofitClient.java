package api.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.time.LocalDateTime;

@Configuration
public class RetrofitClient {

    private static RetrofitClient instance;
    private final MaternityAPI maternityAPI;

    public RetrofitClient() {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new DateDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://web.socem.plymouth.ac.uk/COMP2005/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(new OkHttpClient())
                .build();

        maternityAPI = retrofit.create(MaternityAPI.class);
    }

    @Bean
    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }

        return instance;
    }

    @Bean
    public MaternityAPI getMaternityAPI() {
        return maternityAPI;
    }

}
