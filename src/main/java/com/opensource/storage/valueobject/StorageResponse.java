package com.opensource.storage.valueobject;

import com.opensource.storage.enumeration.StorageType;
import com.opensource.storage.exception.MessageError;
import java.net.URI;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StorageResponse {
  private List<MessageError> fails;
  @Builder.Default private Map<StorageType, URI> urls = new EnumMap<>(StorageType.class);
}
