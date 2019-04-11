package com.example.resizer.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResizeResponse implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private List<FileDto> fileDtos = new ArrayList<>();

  public List<FileDto> getFileDtos() {
    return fileDtos;
  }

  public void setFileDtos(List<FileDto> fileDtos) {
    this.fileDtos = fileDtos;
  }

}
