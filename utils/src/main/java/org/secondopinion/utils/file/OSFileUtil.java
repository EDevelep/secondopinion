package org.secondopinion.utils.file;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class OSFileUtil {

	public static enum OS {
		WINDOWS('\\'),
		UNIX('/');		
		public final Character pathSeparator;		
		private OS(Character pathSeparator) {
			this.pathSeparator = pathSeparator;
		}
		private static Set<Character> ALL_PATH_SEPARATORS;
		public static Set<Character> getALL_PATH_SEPARATORS() {
			if (Objects.isNull(ALL_PATH_SEPARATORS )) {
				ALL_PATH_SEPARATORS = new HashSet<Character>(OS.values().length);
				for (OS os : OS.values()) {
					ALL_PATH_SEPARATORS.add(os.pathSeparator);
				}
				ALL_PATH_SEPARATORS = Collections.unmodifiableSet(ALL_PATH_SEPARATORS);
			}
			return ALL_PATH_SEPARATORS;			
		}
	}
	
	
	/**
	 * Corrects the path separators for the specified OS.
	 * @param path
	 * @param os
	 * @return
	 */
	public static String fixPathSeparators(String path, OS os) {		
		switch(os) {
			case UNIX: 
				return path.replace(OS.WINDOWS.pathSeparator, OS.UNIX.pathSeparator);
			case WINDOWS:
				return path.replace(OS.UNIX.pathSeparator, OS.WINDOWS.pathSeparator);
			default:
				throw new UnsupportedOperationException("Unsupported OS.");  
		}
	}
	
	public static OS getOS() {
		String fileSeparator = System.getProperty("file.separator");
		if (fileSeparator.equals(OS.UNIX.pathSeparator.toString()))
			return OS.UNIX;
		else if (fileSeparator.equals(OS.WINDOWS.pathSeparator.toString()))
			return OS.WINDOWS;
		else
			throw new UnsupportedOperationException("Unsupported OS.");  
	}
	
	/**
	 * Trims trailing and leading path separators.
	 * @param path
	 * @param os
	 * @return
	 */
	public static String trimSeparators(String path) {
		return trimLeadingSeparator(trimTrailingSeparator(path));
	}
	
	public static String trimLeadingSeparator(String path) {
		if (Objects.isNull(path )) return "";
		for (Character pathSeparator : OS.getALL_PATH_SEPARATORS()) {
			if (path.startsWith(pathSeparator.toString())) {
				path = path.substring(pathSeparator.toString().length());
			}
		}
		return path;
	}
	
	public static String trimTrailingSeparator(String path) {
		if (Objects.isNull(path )) return "";
		for (Character pathSeparator : OS.getALL_PATH_SEPARATORS()) {
			if (path.endsWith(pathSeparator.toString())) {
				path = path.substring(0, path.length() - pathSeparator.toString().length());
			}
		}
		return path;
	}
	
	public static String getFilePath(
			String root, String path, String fileName, 
			boolean includingFileName, OS os) 
	{	
		
		root = trimTrailingSeparator(fixPathSeparators(root, os));
		String relPath = trimSeparators(fixPathSeparators(path, os));
		String fName = trimSeparators(fixPathSeparators(fileName, os));
		
		StringBuilder filePath = new StringBuilder();
		
		filePath.append(root);
		filePath.append(os.pathSeparator);
		filePath.append(relPath);
		
		if (includingFileName) {
			filePath.append(os.pathSeparator);
			filePath.append(fName);
		}
		
		return filePath.toString();
	}
}
