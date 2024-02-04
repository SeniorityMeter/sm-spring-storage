package com.opensource.storage.valueobject;

import com.opensource.storage.enumeration.StorageType;
import com.opensource.storage.exception.MessageError;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class StorageRequest {
  private MultipartFile multipartFile;
  private StorageType type;
  private String pathname;
  private String filename;
  private File file;
  @Builder.Default private List<MessageError> fails = new ArrayList<>();
}
