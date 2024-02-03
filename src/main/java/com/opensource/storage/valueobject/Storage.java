package com.opensource.storage.valueobject;

import com.opensource.storage.enumeration.StorageType;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;


@Data
@Builder
public class Storage {
  private StorageType type;
  private MultipartFile multipartFile;
  private String pathname;
  private File file;
  private String awsS3url;
  private List<StorageType> successSavedTypes;
  private List<StorageType> failedSavedTypes;
}
