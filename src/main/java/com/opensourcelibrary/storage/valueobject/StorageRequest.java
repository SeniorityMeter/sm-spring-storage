package com.opensourcelibrary.storage.valueobject;

import com.opensourcelibrary.storage.enumeration.StorageType;
import com.opensourcelibrary.storage.exception.MessageError;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StorageRequest {
  private byte[] fileArray;
  private StorageType type;
  private String domain;
  private String filename;
  private File file;
  @Builder.Default private List<MessageError> fails = new ArrayList<>();
}
