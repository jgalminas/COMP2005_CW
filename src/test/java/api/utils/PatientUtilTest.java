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
    void testFilterByAdmission_AdmissionsAndPatientsIsEmpty() {

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
    void testFilterByAdmission_AdmissionsAndPatientsIsNull() {

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
    void testFilterByAdmissions_AdmissionsIsNullAndPatientsIsEmpty() {

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
    void testFilterByAdmissions_PatientsIsNullAndAdmissionsIsEmpty() {

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
    void testFilterByAdmissions_AdmissionsIsEmpty() {

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
    void testFilterByAdmissions_PatientsIsEmpty() {

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
    void testFilterByAdmissions_PatientsAndAdmissionsIsNotEmpty() {

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