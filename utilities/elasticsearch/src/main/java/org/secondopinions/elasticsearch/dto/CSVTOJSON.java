package org.secondopinions.elasticsearch.dto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.secondopinions.elasticsearch.model.Medicine;


public class CSVTOJSON {

	public static List<Medicine> convertCsvTOJson() {
		List<Medicine> medicneDTOs = null;
		Pattern pattern = Pattern.compile(",");
		try (BufferedReader in = new BufferedReader(new FileReader("E:\\drug_file_1.csv"));) {
			medicneDTOs = in.lines().skip(1).map(line -> {
				String[] x = pattern.split(line);
             
				return new Medicine((x[0]), x[1], x[2], x[3], x[4], x[5], Double.parseDouble(x[6]), x[7], x[8], x[9],
						x[10], x[11]);
			}).collect(Collectors.toList());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return medicneDTOs;
	}
	public static void main(String[] args) {
		convertCsvTOJson();
	}

}