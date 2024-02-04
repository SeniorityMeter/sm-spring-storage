package com.opensourcelibrary.storage.interaction.retrievaloptions;

import com.opensourcelibrary.storage.enumeration.StorageType;
import com.opensourcelibrary.storage.valueobject.StorageRequest;
import com.opensourcelibrary.storage.valueobject.StorageResponse;

public interface StorageRetrievalOption {
  StorageResponse execute(final StorageRequest request, final StorageResponse response);

  boolean canApply(final StorageType storageType);

  StorageType getType();
}
