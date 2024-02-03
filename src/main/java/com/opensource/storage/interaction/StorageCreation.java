package com.opensource.storage.interaction;

import com.opensource.storage.exception.MessageError;
import com.opensource.storage.exception.StorageException;
import com.opensource.storage.interaction.creationoptions.StorageCreationOption;
import com.opensource.storage.utility.FileConverter;
import com.opensource.storage.valueobject.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageCreation {
  private final List<StorageCreationOption> options;

  public Storage execute(final Storage storage) {
    try {
      File fileConverted = FileConverter.of(storage.getMultipartFile(), storage.getPathname());
      storage.setFile(fileConverted);

      var optionsApplied = options.stream()
          .filter(option -> option.canApply(storage.getType()))
          .toList();

      executeOptions(storage, optionsApplied);

      storage.setFile(null);
      Files.delete(fileConverted.toPath());
      return storage;
    } catch (Exception e) {
      throw new StorageException("Error saving file");
    }
  }

  private static void executeOptions(Storage storage, List<StorageCreationOption> optionsApplied) {
    for (StorageCreationOption option : optionsApplied) {
      try {
        option.create(storage);
        storage.getSuccess().add(option.getType());
      } catch (Exception e) {
        storage.getCreationFails()
            .add(MessageError.builder().message(option.getType().name()).details(e.getMessage()).build());
      }
    }
  }
}
