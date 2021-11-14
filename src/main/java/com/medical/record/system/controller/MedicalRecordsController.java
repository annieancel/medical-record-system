package com.medical.record.system.controller;

import com.medical.record.system.dto.MedicalRecordDto;
import com.medical.record.system.service.MedicalRecordsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Controller class for uploading and getting medical records
 */
@RestController("/medicalrecords")
@RequiredArgsConstructor
@Tag(name = "Medical Records System", description = "APIs to handle uploading, getting and deleting medical records")
public class MedicalRecordsController {

    private final MedicalRecordsService medicalRecordsService;

    /**
     * Uploads medical records file
     * @param medicalRecordFile
     * @return
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload medical records")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medical Records uploaded successfully",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Error while processing records",
                    content = @Content)})
    public ResponseEntity<String> uploadMedicalRecord(@RequestParam("file") MultipartFile medicalRecordFile){
        medicalRecordsService.saveMedicalRecordsFile(medicalRecordFile);
        return ResponseEntity.ok("Medical record uploaded successfully");
    }

    /**
     * Get all medical records
     * @return
     */
    @GetMapping("/")
    @Operation(summary = "Get all medical records")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved medical records",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Internal error",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MedicalRecordDto.class)) })})
    public ResponseEntity<List<MedicalRecordDto>> getMedicalRecords(){
        return ResponseEntity.ok(medicalRecordsService.getMedicalRecords());
    }

    /**
     * Get medical record by code
     * @param code
     * @return
     */
    @GetMapping("/{code}")
    @Operation(summary = "Get medical record for the given code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved medical record for the given code",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid code",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MedicalRecordDto.class)) })})
    public ResponseEntity<MedicalRecordDto> getMedicalRecordByCode(@PathVariable("code") String code){
        return ResponseEntity.ok(medicalRecordsService.getMedicalRecordByCode(code));
    }

    /**
     * Delete all medical records
     * @return
     */
    @DeleteMapping("/")
    @Operation(summary = "Delete all medical records")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted all medical records",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Internal error",
                    content = @Content)})
    public ResponseEntity<String> deleteMedicalRecords(){
        medicalRecordsService.deleteMedicalRecords();
        return ResponseEntity.ok("Medical records deleted successfully");
    }
}
