package com.senioritymeter.storage.interaction.creationoptions;

import com.senioritymeter.storage.enumeration.StorageType;
import com.senioritymeter.storage.interaction.StorageCreation.Input;

public interface StorageCreationOption {
  void create(final Input input);

  boolean canApply(final StorageType storageType);
}
