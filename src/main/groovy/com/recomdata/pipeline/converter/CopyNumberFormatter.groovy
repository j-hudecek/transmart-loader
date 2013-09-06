/*************************************************************************
 * tranSMART - translational medicine data mart
 * 
 * Copyright 2008-2012 Janssen Research & Development, LLC.
 * 
 * This product includes software developed at Janssen Research & Development, LLC.
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
 * as published by the Free Software  * Foundation, either version 3 of the License, or (at your option) any later version, along with the following terms:
 * 1.	You may convey a work based on this program in accordance with section 5, provided that you retain the above notices.
 * 2.	You may convey verbatim copies of this program code as you receive it, in any medium, provided that you retain the above notices.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS    * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *
 ******************************************************************/
  

package com.recomdata.pipeline.converter

import org.apache.log4j.Logger;

class CopyNumberFormatter {

	private static final Logger log = Logger.getLogger(CopyNumberFormatter)

	File copyNumberFile
	Map samplePatientMap

	void format(){

		log.info "Start formatting Copy Number file: " + copyNumberFile.toString()

		File f = new File(copyNumberFile.toString() + ".final")
		if(f.size() > 0){
			f.delete()
		}
		f.createNewFile()

		StringBuffer sb = new StringBuffer()

		int lineNum = 0
		copyNumberFile.eachLine{
			lineNum++
			String [] str = it.split("\t")
			sb.append(samplePatientMap[str[0]] + "\t")
			for(int i=1; i<str.size(); i++){
				sb.append(str[i] + "\t")
			}
			sb.append("\n")

			if((lineNum % 10000) == 0){
				f.append(sb.toString())
				sb.setLength(0)
			}
		}

		f.append(sb.toString())
		sb.setLength(0)
		
		// add the suffix ".raw" to the original copy number file
		File raw = new File(copyNumberFile.toString() + ".raw")
		copyNumberFile.renameTo(raw)
		
		// remove the suffix ".final" from the new copy number file
		f.renameTo(copyNumberFile)
		
		log.info "End formatting Copy Number file: " + copyNumberFile.toString()
	}


	void setCopyNumberFile(File copyNumberFile){
		this.copyNumberFile = copyNumberFile
	}


	void setSamplePatientMap(Map samplePatientMap){
		this.samplePatientMap = samplePatientMap
	}
}
