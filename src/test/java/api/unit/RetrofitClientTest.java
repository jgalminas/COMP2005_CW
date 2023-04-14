package api.unit;

import api.network.RetrofitClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RetrofitClientTest {

    RetrofitClient retrofitClient = RetrofitClient.getInstance();

    @Test
    void testRetrofitClientNotNull() {
        assertNotNull(retrofitClient);
    }

    @Test
    void testMaternityAPINotNull() {
        assertNotNull(retrofitClient.getMaternityAPI());
    }
}