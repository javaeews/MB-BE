package com.example.resizer.service;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.example.resizer.dto.FileDto;


/**
 * Service class.
 */
@Component
public class ResizerService {

  /**
   * Konstr.
   */
  public ResizerService() {}



  /**
   * Átméretező service.
   * 
   * @throws IOException, MagickException
   * 
   */
  public List<FileDto> resizeImages(Integer xx, Integer yy, MultipartFile[] files) {
    List<FileDto> result = new ArrayList<>();
    for (MultipartFile file : files) {
      result.add(resizeItem(file, xx, yy));
    }
    return result;
  }

  private FileDto resizeItem(MultipartFile file, Integer xx, Integer yy) {

    File f = null;
    try {
      f = convert(file);
    } catch (IOException e) {
      throw new RuntimeException("Hiba történt a Multipart - File konverzió során.", e);
    }

    File resizedFile = null;
    
    try {
      resizedFile = getThumbnailFile(f, xx, yy);
    } catch (IOException | InterruptedException | IM4JavaException e) {
        throw new RuntimeException("Hiba történt a Thumbnail létrehozásakor.", e);
    }

    FileDto fileDto = new FileDto();
   
    try {
      fileDto.setData(Files.readAllBytes(resizedFile.toPath()));
    } catch (IOException e) {
      throw new RuntimeException("Hiba történt a File byte átalakitásakor.", e);
    }
    fileDto.setFileName(file.getOriginalFilename());
    fileDto.setContentType(file.getContentType());

    return fileDto;
  }

  
  private File convert(MultipartFile file) throws IOException {
    File convFile = new File(file.getOriginalFilename());
    convFile.createNewFile();
    FileOutputStream fos = new FileOutputStream(convFile);
    fos.write(file.getBytes());
    fos.close();
    return convFile;
  }
  
    private File getThumbnailFile(File f, Integer xx, Integer yy) throws IOException, InterruptedException, IM4JavaException {
    String ext = FilenameUtils.getExtension(f.getName());
    File f2 = new File(f.getParentFile(), f.getName() + "." + ext);
    f2.deleteOnExit();
    ConvertCmd cmd = new ConvertCmd();
    IMOperation op = new IMOperation();
    op.addImage(f.getAbsolutePath());
    op.resize(xx, yy);
    op.addImage(f2.getAbsolutePath());
    cmd.run(op);
    return f2;
}


}
