package com.medical.record.system.utils;

import com.opencsv.*;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.exceptions.CsvException;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Utility class for CSV file processing
 */
public class CsvUtils {
    /**
     * Convert CSV file to List of objects of the given type
     * @param multipartFile
     * @param type
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> List<T> convertCsvFile(MultipartFile multipartFile, Class<T> type) throws IOException, CsvException {
        Reader reader = new InputStreamReader(multipartFile.getInputStream());
        return new CsvToBeanBuilder(reader)
                .withType(type)
                .build()
                .parse();
    }
}
