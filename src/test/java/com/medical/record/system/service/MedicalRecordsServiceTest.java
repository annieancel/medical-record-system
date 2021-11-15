package com.medical.record.system.service;

import com.medical.record.system.dto.MedicalRecordDto;
import com.medical.record.system.entity.MedicalRecord;
import com.medical.record.system.mapper.MedicalRecordMapper;
import com.medical.record.system.repository.MedicalRecordRepository;
import com.medical.record.system.service.common.TestUtils;
import com.medical.record.system.utils.CsvUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
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
        MedicalRecordDto medicalRecordDto = TestUtils.getSampleMedicalRecordDto();
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
        MedicalRecord medicalRecord = TestUtils.getSampleMedicalRecord1();
        MedicalRecordDto medicalRecordDto = TestUtils.getSampleMedicalRecordDto();
        when(medicalRecordRepository.findAll()).thenReturn(List.of(medicalRecord));
        when(medicalRecordMapper.toMedicalRecordDtoList(anyList())).thenReturn(List.of(medicalRecordDto));
        List<MedicalRecordDto> result = medicalRecordsService.getMedicalRecords();
        assertThat(result).contains(medicalRecordDto);
        verify(medicalRecordRepository).findAll();
        verify(medicalRecordMapper).toMedicalRecordDtoList(anyList());
    }

    @Test
    void saveMedicalRecords() {
        MedicalRecord medicalRecord = TestUtils.getSampleMedicalRecord1();
        when(medicalRecordRepository.saveAll(anyList())).thenReturn(List.of(medicalRecord));
        medicalRecordsService.saveMedicalRecords(List.of(medicalRecord));
        verify(medicalRecordRepository).saveAll(anyList());
    }

    @Test
    void getMedicalRecordByCode() {
        MedicalRecord medicalRecord = TestUtils.getSampleMedicalRecord1();
        MedicalRecordDto medicalRecordDto = TestUtils.getSampleMedicalRecordDto();
        when(medicalRecordRepository.findMedicalRecordByCode("1")).thenReturn(Optional.of(medicalRecord));
        when(medicalRecordMapper.toMedicalRecordDto(medicalRecord)).thenReturn(medicalRecordDto);
        MedicalRecordDto result = medicalRecordsService.getMedicalRecordByCode("1");
        assertThat(result).isEqualTo(medicalRecordDto);
        verify(medicalRecordRepository).findMedicalRecordByCode("1");
        verify(medicalRecordMapper).toMedicalRecordDto(medicalRecord);
    }
    @Test
    void getMedicalRecordByCodeWhenInvalidCode() {
        MedicalRecord medicalRecord = TestUtils.getSampleMedicalRecord1();
        MedicalRecordDto medicalRecordDto = TestUtils.getSampleMedicalRecordDto();
        when(medicalRecordRepository.findMedicalRecordByCode("1")).thenReturn(Optional.empty());
//        MedicalRecordDto result = medicalRecordsService.getMedicalRecordByCode("1");
        assertThatThrownBy(()->medicalRecordsService.getMedicalRecordByCode("1"))
                .isInstanceOf(NoSuchElementException.class)
                        .hasMessageContaining("Medical record for the given code is not found");
        verify(medicalRecordRepository).findMedicalRecordByCode("1");
        verify(medicalRecordMapper, never()).toMedicalRecordDto(medicalRecord);
    }

    @Test
    void deleteMedicalRecords() {
        doNothing().when(medicalRecordRepository).deleteAll();
        medicalRecordsService.deleteMedicalRecords();
        verify(medicalRecordRepository).deleteAll();
    }
}