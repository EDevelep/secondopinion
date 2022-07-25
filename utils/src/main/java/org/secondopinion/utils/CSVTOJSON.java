package org.secondopinion.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

public class CSVTOJSON {

	public static List<Object> convertCsvTOJson(MultipartFile file) {
		List<Object> medicneDTOs = null;
		Pattern pattern = Pattern.compile(",");
		try (BufferedReader in = new BufferedReader(new FileReader(file.getContentType()));) {
			medicneDTOs = in.lines().skip(1).map(line -> {
				String[] x = pattern.split(line);

				return new Object();
			}).collect(Collectors.toList());

		} catch (Exception e) {

		}

		return medicneDTOs;
	}

	
}