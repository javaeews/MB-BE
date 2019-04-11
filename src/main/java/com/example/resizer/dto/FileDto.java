package com.example.resizer.dto;

import java.io.Serializable;

public class FileDto implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;


  private byte[] data;
  private String fileName;
  private String contentType;

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

}
