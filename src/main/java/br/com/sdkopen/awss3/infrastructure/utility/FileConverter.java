package br.com.sdkopen.awss3.infrastructure.utility;

import br.com.sdkopen.awss3.infrastructure.exception.SDKOpenAwsS3Exception;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileConverter {

  public static File of(final InputStream file, final String filename) throws IOException {
    File fileConverted = new File(Objects.requireNonNull(filename));

    try (FileOutputStream fos = new FileOutputStream(fileConverted)) {
      fos.write(file.readAllBytes());
    } catch (IOException e) {
      Files.delete(fileConverted.toPath());
      throw new SDKOpenAwsS3Exception("Error on file conversion");
    }

    return fileConverted;
  }
}
