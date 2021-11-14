package com.medical.record.system.dto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * Dto class for Medical record
 */
@Data
public class MedicalRecordDto {

    @CsvBindByName(column = "source")
    private String source;

    @CsvBindByName(column = "codeListCode")
    private String codeListCode;

    @CsvBindByName(column = "code")
    private String code;

    @CsvBindByName(column = "displayValue")
    private String displayValue;

    @CsvBindByName(column = "longDescription")
    private String longDescription;

    @CsvDate(value = "dd-MM-yyyy")
    @CsvBindByName(column = "fromDate")
    private LocalDate fromDate;

    @CsvDate(value = "dd-MM-yyyy")
    @CsvBindByName(column = "toDate")
    private LocalDate toDate;

    @CsvBindByName(column = "sortingPriority")
    private int sortingPriority;
}
