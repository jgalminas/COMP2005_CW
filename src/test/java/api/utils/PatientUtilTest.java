package api.utils;

import data.Static;
import api.models.Admission;
import api.models.Patient;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PatientUtilTest {

    @Test
    void testFilterByAdmissionAdmissionsAndPatientsIsEmpty() {

        List<Admission> admissions = Collections.emptyList();
        List<Patient> patients = Collections.emptyList();

        List<Patient> expected = Collections.emptyList();
        List<Patient> actual = PatientUtil.filterByAdmissions(patients, admissions);

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void testFilterByAdmissionAdmissionsAndPatientsIsNull() {

        List<Admission> admissions = null;
        List<Patient> patients = null;

        List<Patient> expected = Collections.emptyList();
        List<Patient> actual = PatientUtil.filterByAdmissions(patients, admissions);

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void testFilterByAdmissionsAdmissionsIsNull() {

        List<Admission> admissions = null;
        List<Patient> patients = Collections.emptyList();

        List<Patient> expected = Collections.emptyList();
        List<Patient> actual = PatientUtil.filterByAdmissions(patients, admissions);

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void testFilterByAdmissionsPatientsIsNull() {

        List<Admission> admissions = Collections.emptyList();
        List<Patient> patients = null;

        List<Patient> expected = Collections.emptyList();
        List<Patient> actual = PatientUtil.filterByAdmissions(patients, admissions);

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void testFilterByAdmissionsAdmissionsIsEmpty() {

        List<Admission> admissions = Collections.emptyList();
        List<Patient> patients = Static.PATIENTS;

        List<Patient> expected = Collections.emptyList();
        List<Patient> actual = PatientUtil.filterByAdmissions(patients, admissions);

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void testFilterByAdmissionsPatientsIsEmpty() {

        List<Admission> admissions = Static.ADMISSIONS;
        List<Patient> patients = Collections.emptyList();

        List<Patient> expected = Collections.emptyList();
        List<Patient> actual = PatientUtil.filterByAdmissions(patients, admissions);

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void testFilterByAdmissionsPatientsAndAdmissionsIsNotEmpty() {

        List<Admission> admissions = Static.ADMISSIONS;
        List<Patient> patients = Static.PATIENTS;

        List<Patient> expected = Arrays.asList(
                new Patient(1, "Robinson", "Viv", "1113335555"),
                new Patient(2, "Carter", "Heather", "2224446666")
        );
        List<Patient> actual = PatientUtil.filterByAdmissions(patients, admissions);

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

}