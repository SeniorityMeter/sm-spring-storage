package com.opensource.storage.interaction.creationoptions;

import com.opensource.storage.enumeration.StorageType;
import com.opensource.storage.valueobject.StorageRequest;

public interface StorageCreationOption {
  void create(final StorageRequest storageRequest);

  boolean canApply(final StorageType storageType);

  StorageType getType();
}
