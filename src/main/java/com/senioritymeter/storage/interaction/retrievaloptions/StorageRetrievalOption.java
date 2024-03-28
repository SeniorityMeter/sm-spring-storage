package com.senioritymeter.storage.interaction.retrievaloptions;

import com.senioritymeter.storage.enumeration.StorageType;
import com.senioritymeter.storage.interaction.StorageRetrieval.Input;
import com.senioritymeter.storage.interaction.StorageRetrieval.Output;

public interface StorageRetrievalOption {
  Output execute(final Input input);

  boolean canApply(final StorageType storageType);
}
