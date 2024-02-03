package com.opensource.storage.valueobject;

import com.opensource.storage.enumeration.StorageType;
import com.opensource.storage.exception.MessageError;
import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class Storage {
  private StorageType type;
  private MultipartFile multipartFile;
  private String pathname;
  private File file;
  private Map<StorageType, URI> urls;
  private List<MessageError> creationFails;
  private List<MessageError> retrievalFails;
}
