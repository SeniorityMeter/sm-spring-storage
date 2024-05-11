package br.com.senioritymeter.storage.interaction.retrievaloptions;

import br.com.senioritymeter.storage.enumeration.StorageType;
import br.com.senioritymeter.storage.interaction.StorageRetrieval.Input;
import br.com.senioritymeter.storage.interaction.StorageRetrieval.Output;

public interface StorageRetrievalOption {
  Output execute(final Input input);

  boolean canApply(final StorageType storageType);
}
