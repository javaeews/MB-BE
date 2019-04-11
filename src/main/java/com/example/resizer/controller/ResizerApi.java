package com.example.resizer.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.resizer.dto.FileDto;
import com.example.resizer.dto.ResizeResponse;
import com.example.resizer.service.ResizerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * <h1>Resizer API.</h1>
 */
@Api(value = "resize")
@RestController
@RequestMapping("frontend/api/resize")
public class ResizerApi {

  private final ResizerService resizerService;

  /**
   * Konstr.
   */
  public ResizerApi(ResizerService resizerService) {
    this.resizerService = resizerService;
  }



  /**
   * Átméretez egy képet. (POST)
   * 
   */
  @ApiOperation(value = "Átméretez egy képet.")
  @RequestMapping(value = "/resize-img", method = RequestMethod.POST,
      consumes = "multipart/form-data")
  @ResponseBody
  ResizeResponse resizeImg(@RequestPart(required = true) Integer xx,
      @RequestPart(required = true) Integer yy,
      @RequestPart(required = true) MultipartFile[] files) {
    List<FileDto> fileDtos = new ArrayList<>();
    fileDtos = resizerService.resizeImages(xx, yy, files);
    ResizeResponse resp = new ResizeResponse();
    resp.setFileDtos(fileDtos);
    return resp;
  }


}
