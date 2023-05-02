package api.unit;

import api.utils.PatientUtil;
import test_data.Data;
import api.models.Admission;
import api.models.Patient;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PatientUtilTest {

    @Test
    void testFilterByAdmission_AdmissionsAndPatientsIsEmpty() {

        // arrange
        List<Admission> admissions = Collections.emptyList();
        List<Patient> patients = Collections.emptyList();
        List<Patient> expected = Collections.emptyList();

        // act
        List<Patient> actual = PatientUtil.filterByAdmissions(patients, admissions);

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void testFilterByAdmission_AdmissionsAndPatientsIsNull() {

        // arrange
        List<Admission> admissions = null;
        List<Patient> patients = null;
        List<Patient> expected = Collections.emptyList();

        // act
        List<Patient> actual = PatientUtil.filterByAdmissions(patients, admissions);

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void testFilterByAdmissions_AdmissionsIsNullAndPatientsIsEmpty() {

        // arrange
        List<Admission> admissions = null;
        List<Patient> patients = Collections.emptyList();
        List<Patient> expected = Collections.emptyList();

        // act
        List<Patient> actual = PatientUtil.filterByAdmissions(patients, admissions);

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void testFilterByAdmissions_PatientsIsNullAndAdmissionsIsEmpty() {

        // arrange
        List<Admission> admissions = Collections.emptyList();
        List<Patient> patients = null;
        List<Patient> expected = Collections.emptyList();

        // act
        List<Patient> actual = PatientUtil.filterByAdmissions(patients, admissions);

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void testFilterByAdmissions_AdmissionsIsEmpty() {

        // arrange
        List<Admission> admissions = Collections.emptyList();
        List<Patient> patients = Data.PATIENTS;
        List<Patient> expected = Collections.emptyList();

        // act
        List<Patient> actual = PatientUtil.filterByAdmissions(patients, admissions);

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void testFilterByAdmissions_PatientsIsEmpty() {

        // arrange
        List<Admission> admissions = Data.ADMISSIONS;
        List<Patient> patients = Collections.emptyList();
        List<Patient> expected = Collections.emptyList();

        // act
        List<Patient> actual = PatientUtil.filterByAdmissions(patients, admissions);

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void testFilterByAdmissions_PatientsAndAdmissionsIsNotEmpty() {

        // arrange
        List<Admission> admissions = Data.ADMISSIONS;
        List<Patient> patients = Data.PATIENTS;
        List<Patient> expected = Arrays.asList(
                new Patient(1, "Robinson", "Viv", "1113335555"),
                new Patient(2, "Carter", "Heather", "2224446666")
        );

        // act
        List<Patient> actual = PatientUtil.filterByAdmissions(patients, admissions);

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

}