package com.opensource.storage.valueobject;

import com.opensource.storage.enumeration.StorageType;
import com.opensource.storage.exception.MessageError;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.Map;


@Data
@Builder
public class Storage {
  private StorageType type;
  private MultipartFile multipartFile;
  private String pathname;
  private File file;
  private Map<StorageType, URI> urls;
  private List<StorageType> success;
  private List<MessageError> creationFails;
  private List<MessageError> retrievalFails;
}
