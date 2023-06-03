package com.MyMoviePlan.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.MyMoviePlan.model.FileDB;
import com.MyMoviePlan.repository.FileDBRepository;

import org.springframework.util.StringUtils;

@Service
public class FileStorageService {
  @Autowired
  private FileDBRepository fileDBRepository;
  public FileDB store(MultipartFile file) throws IOException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
    return fileDBRepository.save(FileDB);
  }
  public FileDB getFile(String id) {
    return fileDBRepository.findById(id).get();
  }
  
  
  
 public FileDB getFileName(String imagename) {
	  return fileDBRepository.findByNameIgnoreCase(imagename);
	  
	    //return fileDBRepository.findById(id).get();
	  }
	  
  
  public Stream<FileDB> getAllFiles() {
    return fileDBRepository.findAll().stream();
  }
  
  public List<FileDB>getImages(){
	  
	  return fileDBRepository.findAll();
  }
}