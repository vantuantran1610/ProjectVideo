package edu.vn.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;

public class UploadUtils {
	public static String processUploadField(String fieldName, String storedFolder, String storedFilename,
			HttpServletRequest request) throws IOException, ServletException {
		
		Part filPart = request.getPart(fieldName);

		if (filPart == null || filPart.getSize() == 0) {
			return "";
		}

		if (storedFolder == null) {
			storedFolder = "/uploads";
		}
		
		if (storedFilename == null) {
			storedFilename = Path.of(filPart.getSubmittedFileName()).getFileName().toString();

		} else {
			storedFilename += "." + FilenameUtils.getExtension(Path.of(filPart.getSubmittedFileName()).toString());
		}
		
		
		//lay duong dan thuc te cua storedFolder
		String uploadFolder = request.getServletContext().getRealPath(storedFolder);

		Path uploadPath = Paths.get(uploadFolder);

		if (!Files.exists(uploadPath)) {
			Files.createDirectory(uploadPath);
		}
		
		//ghi noi dung ra thu muc
		filPart.write(Paths.get(uploadPath.toString(), storedFilename).toString());

		return storedFilename;
	}
}
