package com.opensource.storage.interaction.retrievaloptions;

import com.opensource.storage.enumeration.StorageType;
import com.opensource.storage.valueobject.Storage;

public interface StorageRetrievalOption {
  void execute(final Storage storage);

  boolean canApply(final StorageType storageType);

  StorageType getType();
}
