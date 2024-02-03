package com.opensource.storage.interaction.creationoptions;

import com.opensource.storage.enumeration.StorageType;
import com.opensource.storage.valueobject.Storage;

public interface StorageCreationOption {
  void create(final Storage storage);

  boolean canApply(final StorageType storageType);

  StorageType getType();
}
