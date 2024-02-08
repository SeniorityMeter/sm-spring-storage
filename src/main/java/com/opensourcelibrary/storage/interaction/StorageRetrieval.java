package com.opensourcelibrary.storage.interaction;

import com.opensourcelibrary.storage.exception.MessageError;
import com.opensourcelibrary.storage.exception.StorageException;
import com.opensourcelibrary.storage.interaction.retrievaloptions.StorageRetrievalOption;
import com.opensourcelibrary.storage.valueobject.StorageRequest;
import com.opensourcelibrary.storage.valueobject.StorageResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StorageRetrieval {
  private final List<StorageRetrievalOption> options;

  public StorageResponse execute(final StorageRequest storageRequest) {
    try {
      var optionsApplied =
          options.stream().filter(option -> option.canApply(storageRequest.getType())).toList();

      return executeOptions(storageRequest, optionsApplied);
    } catch (Exception e) {
      throw new StorageException("Error retrieving file");
    }
  }

  private StorageResponse executeOptions(
      StorageRequest request, List<StorageRetrievalOption> optionsApplied) {
    var response = StorageResponse.builder().fails(new ArrayList<>()).build();
    for (StorageRetrievalOption option : optionsApplied) {
      try {
        option.execute(request, response);
      } catch (Exception e) {
        response
            .getFails()
            .add(
                MessageError.builder()
                    .message(option.getType().name())
                    .details(e.getMessage())
                    .build());
      }
    }
    return response;
  }
}
