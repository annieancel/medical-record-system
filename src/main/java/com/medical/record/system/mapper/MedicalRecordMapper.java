package com.medical.record.system.mapper;

import com.medical.record.system.dto.MedicalRecordDto;
import com.medical.record.system.entity.MedicalRecord;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper for mapping {@link com.medical.record.system.dto.MedicalRecordDto} to {@link com.medical.record.system.entity.MedicalRecord}
 * and vice versa
 */
@Mapper(componentModel = "spring")
public interface MedicalRecordMapper {

    /**
     * Maps {@link MedicalRecordDto} to {@link MedicalRecord}
     * @param medicalRecordDto
     * @return
     */
    public MedicalRecord toMedicalRecord(MedicalRecordDto medicalRecordDto);

    /**
     * Maps {@link MedicalRecord} to {@link MedicalRecordDto}
     * @param medicalRecord
     * @return
     */
    public MedicalRecordDto toMedicalRecordDto(MedicalRecord medicalRecord);

    /**
     * Maps list of {@link MedicalRecordDto} to list of {@link MedicalRecord}
     * @param medicalRecordDtoList
     * @return
     */
    public List<MedicalRecord> toMedicalRecordList(List<MedicalRecordDto> medicalRecordDtoList);

    /**
     * Maps list of {@link MedicalRecord} to list of {@link MedicalRecordDto}
     * @param medicalRecordList
     * @return
     */
    public List<MedicalRecordDto> toMedicalRecordDtoList(List<MedicalRecord> medicalRecordList);
}
