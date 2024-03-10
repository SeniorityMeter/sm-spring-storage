package com.opensourcelibrary.storage.interaction.removaloptions;

import com.opensourcelibrary.storage.enumeration.StorageType;
import com.opensourcelibrary.storage.interaction.StorageRemoval.Input;

public interface StorageRemovalOption {
  void execute(final Input input);

  boolean canApply(final StorageType storageType);
}
