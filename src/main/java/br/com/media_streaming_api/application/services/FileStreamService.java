package br.com.media_streaming_api.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.stereotype.Service;

import br.com.media_streaming_api.domain.ports.FileStorageGateway;

@Service
public class FileStreamService {

	@Autowired
    FileStorageGateway gateway;

    public ResourceRegion getResourceRegion(String filename, long start, long end) {
        Resource video = this.gateway.getFileAsResource(filename);
        long length = Math.min(1024 * 1024, end - start + 1);

        return new ResourceRegion(video, start, length);
    }

    public long getFileLength(String filename) {
        return this.gateway.getFileLength(filename);
    }

}
