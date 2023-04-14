package api.unit;

import api.models.AdmissionDuration;
import api.models.Day;
import api.models.Patient;
import api.services.APIService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.Collections;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@WebMvcTest
class APIControllerTest {

    @MockBean
    APIService apiService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testGetDayWithMostAdmissions() throws Exception {

        when(apiService.getDayWithMostAdmissions()).thenReturn(new Day("MONDAY"));

        String expected = "{\"name\":\"MONDAY\"}";
        String result = mockMvc.perform(get("/day-with-most-admissions"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(expected, result);

    }

    @Test
    void testGetAvgPatientTimeByEmployeeId() throws Exception {

        int id = 4;

        when(apiService.getAvgPatientTimeByEmployeeId(id)).thenReturn(new AdmissionDuration(2934));

        String expected = "{\"unit\":\"MINUTES\",\"amount\":2934}";
        String result = mockMvc.perform(get("/avg-patient-time/{id}", id))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(expected, result);

    }

    @Test
    void testGetPatientsByEmployeeId() throws Exception {

        int id = 4;

        when(apiService.getPatientsByEmployeeId(id)).thenReturn(Arrays.asList(
                new Patient(1, "Robinson", "Viv", "1113335555"),
                new Patient(2, "Carter", "Heather", "2224446666")
        ));

        String expected = "[{\"id\":1,\"surname\":\"Robinson\",\"forename\":\"Viv\",\"nhsNumber\":\"1113335555\"}," +
                          "{\"id\":2,\"surname\":\"Carter\",\"forename\":\"Heather\",\"nhsNumber\":\"2224446666\"}]";

        String result = mockMvc.perform(get("/patients-by-employee/{id}", id))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(expected, result);

    }

    @Test
    void testGetPatientsByEmployeeId_EmptyArray() throws Exception {

        int id = 4;

        when(apiService.getPatientsByEmployeeId(id)).thenReturn(Collections.emptyList());

        String expected = "[]";

        String result = mockMvc.perform(get("/patients-by-employee/{id}", id))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(expected, result);

    }

    @Test
    void testGetPatientsDischargedWithin3Days_EmptyArray() throws Exception {

        when(apiService.getPatientsDischargedWithin3Days()).thenReturn(Collections.emptyList());

        String expected = "[]";
        String result = mockMvc.perform(get("/discharged-within-3-days"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(expected, result);

    }

    @Test
    void testGetPatientsDischargedWithin3Days() throws Exception {

        when(apiService.getPatientsDischargedWithin3Days()).thenReturn(Arrays.asList(
                new Patient(2, "Carter", "Heather", "2224446666")
        ));

        String expected = "[{\"id\":2,\"surname\":\"Carter\",\"forename\":\"Heather\",\"nhsNumber\":\"2224446666\"}]";
        String result = mockMvc.perform(get("/discharged-within-3-days"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(expected, result);

    }
}