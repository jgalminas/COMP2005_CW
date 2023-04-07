package network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MaternityAPIClient {

    private static MaternityAPIClient instance;
    private final MaternityService maternityService;

    public MaternityAPIClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://web.socem.plymouth.ac.uk/COMP2005/api")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();

        maternityService = retrofit.create(MaternityService.class);
    }

    public static MaternityAPIClient getInstance() {
        if (instance == null) {
            instance = new MaternityAPIClient();
        }

        return instance;
    }

    public MaternityService getMaternityService() {
        return maternityService;
    }

}
