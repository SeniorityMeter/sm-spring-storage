package br.com.senioritymeter.storage.interaction.removaloptions;

import br.com.senioritymeter.storage.enumeration.StorageType;
import br.com.senioritymeter.storage.interaction.StorageRemoval.Input;

public interface StorageRemovalOption {
  void execute(final Input input);

  boolean canApply(final StorageType storageType);
}
