package com.bakeryblueprint.modernjava.week05_jaehyun;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ChannelFileIO {

  public static void main(final String[] args) {
    if (args.length > 2) {
      System.out.println("Usage : java ChannelFileIO <source> <target>");
      return;
    }

    final Path sourcePath = Paths.get(args[0]);
    final Path targetPath = Paths.get(args[1]);

    try (final ReadableByteChannel inputChannel = Files
        .newByteChannel(sourcePath, StandardOpenOption.READ);
        final WritableByteChannel outChannel = Files
            .newByteChannel(targetPath, StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW)) {
      ByteBuffer buf = ByteBuffer.allocate(1024);

      while (inputChannel.read(buf) != 0) {

        outChannel.write(buf);
        buf.rewind();
      }

    } catch (final IOException e) {
      e.printStackTrace();
    }
  }
}
