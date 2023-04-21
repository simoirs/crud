package com.employeeRegister.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileUploadController {
	
	 private static String UPLOADED_FOLDER = "F://temp//";

	@GetMapping("/uploadFile")
	public String uploadFile() {
		return "file-upload";
	}
	
	@PostMapping("uploadFile")
	public String uploadFile(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Add a file to upload");
			return "redirect:uploadStatus";
		}
		
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(file.getOriginalFilename());
			Files.write(path, bytes);
			
			redirectAttributes.addFlashAttribute("message", "Successfully uploaded: " + file.getOriginalFilename());
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/uploadStatus";
	}
	
	@GetMapping("uploadStatus")
	public String uploadStatus() {
		return "upload-status";
	}
	
}
