package com.medical.record.system.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

/**
 * Entity class for medical record
 */
@Entity
@Table(name = "medical_record")
@Data
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "source")
    private String source;

    @Column(name = "code_list_code")
    private String codeListCode;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "display_value")
    private String displayValue;

    @Column(name = "long_description")
    private String longDescription;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    @Column(name = "sorting_priority")
    private int sortingPriority;
}
