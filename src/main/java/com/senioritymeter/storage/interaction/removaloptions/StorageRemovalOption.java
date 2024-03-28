package com.senioritymeter.storage.interaction.removaloptions;

import com.senioritymeter.storage.enumeration.StorageType;
import com.senioritymeter.storage.interaction.StorageRemoval.Input;

public interface StorageRemovalOption {
  void execute(final Input input);

  boolean canApply(final StorageType storageType);
}
