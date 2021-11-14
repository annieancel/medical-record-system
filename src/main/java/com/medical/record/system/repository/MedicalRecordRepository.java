package com.medical.record.system.repository;

import com.medical.record.system.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for {@link MedicalRecord}
 */
@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

    /**
     * Find {@link MedicalRecord} for the given code
     * @param code
     * @return
     */
    public Optional<MedicalRecord> findMedicalRecordByCode(String code);
}
