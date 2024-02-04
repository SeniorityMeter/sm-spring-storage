package com.opensource.storage.interaction.retrievaloptions;

import com.opensource.storage.enumeration.StorageType;
import com.opensource.storage.valueobject.StorageRequest;
import com.opensource.storage.valueobject.StorageResponse;

public interface StorageRetrievalOption {
  StorageResponse execute(final StorageRequest request, final StorageResponse response);

  boolean canApply(final StorageType storageType);

  StorageType getType();
}
