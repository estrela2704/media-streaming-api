package br.com.media_streaming_api.domain.ports;

import org.springframework.core.io.Resource;

public interface FileStorageGateway {
    Resource getFileAsResource(String filename);
    long getFileLength(String filename);
}
