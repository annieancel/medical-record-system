package com.medical.record.system.service.common;

import com.medical.record.system.dto.MedicalRecordDto;
import com.medical.record.system.entity.MedicalRecord;

import java.sql.Date;
import java.time.LocalDate;

public class TestUtils {

    public static MedicalRecord getSampleMedicalRecord1() {
        MedicalRecord record = new MedicalRecord();
        record.setCode("code");
        record.setCodeListCode("codelist");
        record.setDisplayValue("disp");
        record.setFromDate(Date.valueOf(LocalDate.now()));
        record.setId(1L);
        record.setToDate(Date.valueOf(LocalDate.now()));
        record.setLongDescription("desc");
        return record;
    }

    public static MedicalRecord getSampleMedicalRecord2() {
        MedicalRecord record = new MedicalRecord();
        record.setCode("code");
        record.setCodeListCode("codelist");
        record.setDisplayValue("disp");
        record.setFromDate(Date.valueOf(LocalDate.now()));
        record.setId(2L);
        record.setToDate(Date.valueOf(LocalDate.now()));
        record.setLongDescription("desc");
        return record;
    }

    public static MedicalRecordDto getSampleMedicalRecordDto() {
        MedicalRecordDto record = new MedicalRecordDto();
        record.setCode("code");
        record.setCodeListCode("codelist");
        record.setDisplayValue("disp");
        record.setFromDate(LocalDate.now());
        record.setToDate(LocalDate.now());
        record.setLongDescription("desc");
        return record;
    }

}
