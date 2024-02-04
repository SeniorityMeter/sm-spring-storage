package com.opensource.storage.interaction;

import com.opensource.storage.exception.MessageError;
import com.opensource.storage.exception.StorageException;
import com.opensource.storage.interaction.creationoptions.StorageCreationOption;
import com.opensource.storage.utility.FileConverter;
import com.opensource.storage.valueobject.StorageRequest;
import java.io.File;
import java.nio.file.Files;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageCreation {
  private final List<StorageCreationOption> options;

  public StorageRequest execute(final StorageRequest storageRequest) {
    try {
      File fileConverted =
          FileConverter.of(storageRequest.getMultipartFile(), storageRequest.getFilename());
      storageRequest.setFile(fileConverted);

      var optionsApplied =
          options.stream().filter(option -> option.canApply(storageRequest.getType())).toList();

      executeOptions(storageRequest, optionsApplied);

      storageRequest.setFile(null);
      Files.delete(fileConverted.toPath());
      return storageRequest;
    } catch (Exception e) {
      throw new StorageException(e.getMessage());
    }
  }

  private static void executeOptions(
      StorageRequest storageRequest, List<StorageCreationOption> optionsApplied) {
    for (StorageCreationOption option : optionsApplied) {
      try {
        option.create(storageRequest);
      } catch (Exception e) {
        storageRequest
            .getFails()
            .add(
                MessageError.builder()
                    .message(option.getType().name())
                    .details(e.getMessage())
                    .build());
      }
    }
  }
}
