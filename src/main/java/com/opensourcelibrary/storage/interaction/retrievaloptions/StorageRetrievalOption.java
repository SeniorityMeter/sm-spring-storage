package com.opensourcelibrary.storage.interaction.retrievaloptions;

import com.opensourcelibrary.storage.enumeration.StorageType;
import com.opensourcelibrary.storage.interaction.StorageRetrieval.Input;
import com.opensourcelibrary.storage.interaction.StorageRetrieval.Output;

public interface StorageRetrievalOption {
  Output execute(final Input input);

  boolean canApply(final StorageType storageType);
}
