package com.MyMoviePlan.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.MyMoviePlan.model.FileDB;
import com.MyMoviePlan.response.ImageResponseMessage;
import com.MyMoviePlan.response.MessageResponse;
import com.MyMoviePlan.response.ResponseFile;
import com.MyMoviePlan.services.FileStorageService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class FileController {
  @Autowired
  private FileStorageService storageService;
  @PostMapping("/upload")
  public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";
    try {
    	FileDB img=storageService.store(file);
      message = "Uploaded the file successfully: " + file.getOriginalFilename(); 
      return ResponseEntity.ok(img);
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ImageResponseMessage(message));
    }
  }
  @GetMapping("/downloadfiles")
  public ResponseEntity<List<ResponseFile>> getListFiles() {
    List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
      String fileDownloadUri = ServletUriComponentsBuilder
          .fromCurrentContextPath()
          .path("/api/auth/downloadfiles/")
          .path(dbFile.getId())
          .toUriString();
      return new ResponseFile(
          dbFile.getName(),
          fileDownloadUri,
          dbFile.getType(),
          dbFile.getData().length);
    }).collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(files);
  }
  @GetMapping("/downloadfiles/{id}")
  public ResponseEntity<byte[]> getFile(@PathVariable String id) {
    FileDB fileDB = storageService.getFile(id);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
        .body(fileDB.getData());
  }
  
  
  @GetMapping(path = { "/getImage/{imageName}" })
	public ResponseEntity<?> getImage(@PathVariable("imageName") String imageName) throws IOException {

		final FileDB retrievedImage = storageService.getFileName(imageName);
		if(retrievedImage!=null && retrievedImage.getName() !=null) { 
			FileDB img = new FileDB(retrievedImage.getId(),  retrievedImage.getName(), retrievedImage.getType(),
					retrievedImage.getData());
			
			 return ResponseEntity.ok(img);
			
		}
		else
		{
		      return ResponseEntity.badRequest().body(new MessageResponse("Image not available. Ensure you have entered the correct image name"));
			 // return ResponseEntity.ok( new ImageResponseMessage("Image not available. Ensure you have entered the correct image name"));
		}
		
	}
  
  
  @GetMapping(path = { "/getImageById/{id}" })
	public ResponseEntity<?> getImageById(@PathVariable("id") String id) throws IOException {

		final FileDB retrievedImage = storageService.getFile(id);
		if(retrievedImage!=null && retrievedImage.getName() !=null) { 
			FileDB img = new FileDB(retrievedImage.getId(),  retrievedImage.getName(), retrievedImage.getType(),
					retrievedImage.getData());
			
			 return ResponseEntity.ok(img);
			
		}
		else
		{
		      return ResponseEntity.badRequest().body(new MessageResponse("Image not available. Ensure you have entered the correct image name"));
			 // return ResponseEntity.ok( new ImageResponseMessage("Image not available. Ensure you have entered the correct image name"));
		}
		
	}
  
  
  @GetMapping(path = { "/getImages"})
 	public ResponseEntity<?> getImages() throws IOException {
	  
	List<FileDB> images=storageService.getImages();

 		return ResponseEntity.ok(images);
 	}
}