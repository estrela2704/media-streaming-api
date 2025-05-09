package br.com.media_streaming_api.infrastructure.storage;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import br.com.media_streaming_api.domain.ports.FileStorageGateway;

@Service
public class LocalFileStorage implements FileStorageGateway {

    @Value("${file.folder.path}")
    private String filepath;

    @Override
    public Resource getFileAsResource(String filename) {
        File file = new File(this.filepath, filename);

        return new FileSystemResource(file);
    }

    @Override
    public long getFileLength(String filename) {
        File file = new File(this.filepath, filename);

        return file.length();
    }

}
