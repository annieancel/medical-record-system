package com.medical.record.system.service;

import com.medical.record.system.dto.MedicalRecordDto;
import com.medical.record.system.entity.MedicalRecord;
import com.medical.record.system.exception.RecordProcessingException;
import com.medical.record.system.mapper.MedicalRecordMapper;
import com.medical.record.system.repository.MedicalRecordRepository;
import com.medical.record.system.utils.CsvUtils;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Service class for medical records processing
 */
@Service
@RequiredArgsConstructor
public class MedicalRecordsService {

    private final MedicalRecordMapper medicalRecordMapper;
    private final MedicalRecordRepository medicalRecordRepository;

    /**
     * Converts medical records file and saves to DB
     *
     * @param medicalRecordFile
     */
    public void saveMedicalRecordsFile(MultipartFile medicalRecordFile) {
        try {
            var medicalRecordsDtos = CsvUtils.convertCsvFile(medicalRecordFile, MedicalRecordDto.class);
            var medicalRecords = medicalRecordMapper.toMedicalRecordList(medicalRecordsDtos);
            saveMedicalRecords(medicalRecords);
        } catch (IOException | CsvException e) {
            throw new RecordProcessingException("Failed to upload the medical record file.");
        }
    }

    /**
     * Get all medical records from DB
     * @return
     */
    public List<MedicalRecordDto> getMedicalRecords() {
        var medicalRecords = medicalRecordRepository.findAll();
        return medicalRecordMapper.toMedicalRecordDtoList(medicalRecords);
    }

    /**
     * Save all medical records to DB
     * @param medicalRecords
     */
    public void saveMedicalRecords(List<MedicalRecord> medicalRecords) {
        medicalRecordRepository.saveAll(medicalRecords);
    }

    /**
     * Get medical record by code.
     * @param code
     * @return
     */
    public MedicalRecordDto getMedicalRecordByCode(String code) {
        var medicalRecord = medicalRecordRepository.findMedicalRecordByCode(code)
                .orElseThrow(() -> new NoSuchElementException("Medical record for the given code is not found"));
        return medicalRecordMapper.toMedicalRecordDto(medicalRecord);
    }

    /**
     * Delete all medical records from DB
     */
    public void deleteMedicalRecords() {
        medicalRecordRepository.deleteAll();
    }
}
