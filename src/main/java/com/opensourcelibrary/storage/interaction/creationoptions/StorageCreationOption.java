package com.opensourcelibrary.storage.interaction.creationoptions;

import com.opensourcelibrary.storage.enumeration.StorageType;
import com.opensourcelibrary.storage.interaction.StorageCreation.Input;

public interface StorageCreationOption {
  void create(final Input input);

  boolean canApply(final StorageType storageType);
}
