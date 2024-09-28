//package com.mingleHub.authsvc.test;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.opencsv.CSVParser;
//import com.opencsv.CSVReader;
//import com.opencsv.CSVReaderBuilder;
//import com.opencsv.bean.CsvToBean;
//import com.opencsv.bean.CsvToBeanBuilder;
//import com.opencsv.bean.HeaderColumnNameMappingStrategy;
//import com.opencsv.exceptions.CsvException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.opencsv.exceptions.CsvValidationException;
//import lombok.Data;
//import lombok.experimental.Accessors;
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVRecord;
//
//import java.io.*;
//import java.util.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//
//public class UniqueSkillsExtractor {
//    public static void main(String[] args) throws IOException {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        // Read JSON file
//        File jsonFile1 = new File("/Users/dipankarbhaduri/Desktop/mingle-hub/authsvc/src/main/java/com/mingleHub/authsvc/test/query_result_2024-06-26T08_25_02.662159Z.json");
//        File jsonFile2 = new File("/Users/dipankarbhaduri/Desktop/mingle-hub/authsvc/src/main/java/com/mingleHub/authsvc/test/query_result_2024-06-26T08_25_47.709923Z.json");
//        File jsonFile3 = new File("/Users/dipankarbhaduri/Desktop/mingle-hub/authsvc/src/main/java/com/mingleHub/authsvc/test/query_result_2024-06-26T08_33_28.052334Z.json");
//
//        List<File> files = new ArrayList<>();
//        files.add(jsonFile1);
//        files.add(jsonFile2);
//        files.add(jsonFile3);
//
//        String [] keys = new String[3];
//        keys[0] = "intern_skills";
//        keys[1] = "fte_skills";
//        keys[2] = "project_skills";
//
//        Set<String> extractedSkills = new HashSet<>();
//
//        for (int i = 0; i < keys.length; i++) {
//            JsonNode rootNode = objectMapper.readTree(files.get(i));
//
//            String regex = "\"([^\"]*)\"";
//            Pattern pattern = Pattern.compile(regex);
//
//            for (JsonNode node : rootNode) {
//                String projectSkills = node.get(keys[i]).asText();
//
//                Matcher matcher = pattern.matcher(projectSkills);
//                while (matcher.find()) {
//                    String skill = matcher.group(1);
//                    extractedSkills.add(skill);
//                }
//            }
//        }
//
//        int count = 0;
//        // Print extracted project skills
//        System.out.println("Extracted Project Skills:");
//        for (String skill : extractedSkills) {
//            count ++;
//            if (skill.length()< 30 && skill.length() > 3) System.out.println(skill);
//
//            if (count == 40000) {
//                System.out.println("---");
//            }
//        }
//    }
//}