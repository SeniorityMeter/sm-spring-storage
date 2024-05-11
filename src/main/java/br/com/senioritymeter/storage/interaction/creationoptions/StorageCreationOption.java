package br.com.senioritymeter.storage.interaction.creationoptions;

import br.com.senioritymeter.storage.enumeration.StorageType;
import br.com.senioritymeter.storage.interaction.StorageCreation.Input;

public interface StorageCreationOption {
  void create(final Input input);

  boolean canApply(final StorageType storageType);
}
