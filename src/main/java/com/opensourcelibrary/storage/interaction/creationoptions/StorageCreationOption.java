package com.opensourcelibrary.storage.interaction.creationoptions;

import com.opensourcelibrary.storage.enumeration.StorageType;
import com.opensourcelibrary.storage.valueobject.StorageRequest;

public interface StorageCreationOption {
  void create(final StorageRequest storageRequest);

  boolean canApply(final StorageType storageType);

  StorageType getType();
}
