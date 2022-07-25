
package org.secondopinion.utils.file;

import org.secondopinion.utils.threadlocal.Medicine;

public class SampleLineParser implements ILineParser<Medicine>{

	@Override
	public Medicine parseLine(String line) {
	
		String[] x = line.split(",");
		
		try {
			return new Medicine((x[0]), x[1], x[2], x[3], x[4], x[5], Double.parseDouble(x[6]), x[7], x[8], x[9],
					x[10], x[11]);

		}catch(Exception ex) {
			return null;
		}
	}

	
}
