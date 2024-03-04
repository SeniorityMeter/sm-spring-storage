package com.opensourcelibrary.storage.interaction;

import com.opensourcelibrary.storage.exception.MessageError;
import com.opensourcelibrary.storage.exception.StorageException;
import com.opensourcelibrary.storage.interaction.creationoptions.StorageCreationOption;
import com.opensourcelibrary.storage.utility.FileConverter;
import com.opensourcelibrary.storage.valueobject.StorageRequest;
import java.nio.file.Files;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageCreation {
  private final List<StorageCreationOption> options;

  public void execute(final StorageRequest storageRequest) {
    try {
      var fileConverted =
          FileConverter.of(storageRequest.getFileArray(), storageRequest.getFilename());
      storageRequest.setFile(fileConverted);

      var option = options.stream().filter(o -> o.canApply(storageRequest.getType())).findFirst();

      if (option.isEmpty()) {
        throw StorageException.typeNotSupported();
      }

      executeOptions(storageRequest, option.get());

      Files.delete(fileConverted.toPath());
    } catch (Exception e) {
      throw new StorageException(e.getMessage());
    }
  }

  private void executeOptions(StorageRequest request, StorageCreationOption option) {
    try {
      option.create(request);
    } catch (Exception e) {
      request
          .getFails()
          .add(
              MessageError.builder()
                  .message(option.getType().name())
                  .details(e.getMessage())
                  .build());
    }
  }
}
