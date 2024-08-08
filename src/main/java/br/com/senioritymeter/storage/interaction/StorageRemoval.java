package br.com.senioritymeter.storage.interaction;

import br.com.senioritymeter.storage.enumeration.StorageType;
import br.com.senioritymeter.storage.exception.StorageException;
import br.com.senioritymeter.storage.interaction.removaloptions.StorageRemovalOption;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageRemoval {
  private final List<StorageRemovalOption> options;

  public void execute(final Input input) {
    try {
      var option =
          options.stream()
              .filter(o -> o.canApply(input.getType()))
              .findFirst()
              .orElseThrow(StorageException::typeNotSupported);

      option.execute(input);
    } catch (Exception e) {
      throw new StorageException(e.getMessage());
    }
  }

  @Getter
  @Builder
  public static class Input {
    private String key;
    private String bucket;
    private StorageType type;
  }
}
