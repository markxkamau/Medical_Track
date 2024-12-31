package com.example.MedicalWebInput.Configuration;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Converter(autoApply = true)
public class LocalTimeSetConvertor implements AttributeConverter<Set<LocalTime>, String> {

    //Converts a Set collection of time into one combined string e.g. SetData : [08:30, 12:45, 17:00] to String ""08:30, 12:45, 17:00""
    @Override
    public String convertToDatabaseColumn(Set<LocalTime> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return "";
        }
        return attribute.stream().map(LocalTime::toString).collect(Collectors.joining(","));
    }

    // Data we are receiving from the database is being split DB data received : ""08:30, 09:00"" to SetData: [08:30, 09:00]
    @Override
    public Set<LocalTime> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return new HashSet<>();
        }
        return Arrays.stream(dbData.split(",")).map(LocalTime::parse).collect(Collectors.toSet());
    }

}
//import javax.persistence.AttributeConverter;
//import javax.persistence.Converter;
//import java.time.LocalTime;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
//import java.util.stream.Collectors;

//@Converter(autoApply = true)
//public class LocalTimeSetConvertor implements AttributeConverter<Set<LocalTime>, String> {
//
//    @Override
//    public String convertToDatabaseColumn(Set<LocalTime> attribute) {
//        if (attribute == null || attribute.isEmpty()) {
//            return "";
//        }
//        return attribute.stream()
//                .map(LocalTime::toString)  // Convert LocalTime to ISO-8601 string
//                .collect(Collectors.joining(","));
//    }
//
//    @Override
//    public Set<LocalTime> convertToEntityAttribute(String dbData) {
//        if (dbData == null || dbData.isEmpty()) {
//            return new HashSet<>();
//        }
//        return Arrays.stream(dbData.split(","))
//                .map(LocalTime::parse)  // Convert ISO-8601 string back to LocalTime
//                .collect(Collectors.toSet());
//    }
//}