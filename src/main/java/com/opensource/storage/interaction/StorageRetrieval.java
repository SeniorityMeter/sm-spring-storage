package com.opensource.storage.interaction;

import com.opensource.storage.exception.MessageError;
import com.opensource.storage.exception.StorageException;
import com.opensource.storage.interaction.retrievaloptions.StorageRetrievalOption;
import com.opensource.storage.valueobject.Storage;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageRetrieval {
  private final List<StorageRetrievalOption> options;

  public Storage execute(final Storage storage) {
    try {
      var optionsApplied =
          options.stream().filter(option -> option.canApply(storage.getType())).toList();

      executeOptions(storage, optionsApplied);

      return storage;
    } catch (Exception e) {
      throw new StorageException("Error retrieving file");
    }
  }

  private void executeOptions(Storage storage, List<StorageRetrievalOption> optionsApplied) {
    for (StorageRetrievalOption option : optionsApplied) {
      try {
        option.execute(storage);
      } catch (Exception e) {
        storage
            .getRetrievalFails()
            .add(
                MessageError.builder()
                    .message(option.getType().name())
                    .details(e.getMessage())
                    .build());
      }
    }
  }
}
