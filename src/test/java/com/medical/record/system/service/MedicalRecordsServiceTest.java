package com.medical.record.system.service;

import com.medical.record.system.dto.MedicalRecordDto;
import com.medical.record.system.entity.MedicalRecord;
import com.medical.record.system.mapper.MedicalRecordMapper;
import com.medical.record.system.repository.MedicalRecordRepository;
import com.medical.record.system.service.common.TestUtils;
import com.medical.record.system.utils.CsvUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class MedicalRecordsServiceTest {

    @Mock
    private MedicalRecordMapper medicalRecordMapper;
    @Mock
    private MedicalRecordRepository medicalRecordRepository;
    @InjectMocks
    private MedicalRecordsService medicalRecordsService;
    @Test
    void saveMedicalRecordsFile() {
        MedicalRecord medicalRecord = TestUtils.getSampleMedicalRecord1();
        MedicalRecordDto medicalRecordDto = TestUtils.getSamplePatientRecordDto();
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );
        when(medicalRecordMapper.toMedicalRecordList(anyList())).thenReturn(List.of(medicalRecord));
        when(medicalRecordRepository.saveAll(anyList())).thenReturn(List.of());
        try(MockedStatic<CsvUtils> staticmock = mockStatic(CsvUtils.class)) {
            staticmock.when(()->CsvUtils.convertCsvFile(any(), any()))
                    .thenReturn(List.of(medicalRecordDto));
            medicalRecordsService.saveMedicalRecordsFile(file);
            verify(medicalRecordMapper).toMedicalRecordList(anyList());
            verify(medicalRecordRepository).saveAll(anyList());
        }
    }

    @Test
    void getMedicalRecords() {
    }

    @Test
    void saveMedicalRecords() {
        MedicalRecord medicalRecord = TestUtils.getSampleMedicalRecord1();
        when(medicalRecordRepository.saveAll(anyList())).thenReturn(List.of(medicalRecord));
        medicalRecordsService.saveMedicalRecords(List.of(medicalRecord));
        verify(medicalRecordsService).saveMedicalRecords(anyList());
    }

    @Test
    void getMedicalRecordByCode() {
    }

    @Test
    void deleteMedicalRecords() {
    }
}