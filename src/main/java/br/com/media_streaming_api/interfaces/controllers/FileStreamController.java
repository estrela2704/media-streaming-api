package br.com.media_streaming_api.interfaces.controllers;

import javax.print.attribute.standard.Media;

import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.media_streaming_api.application.services.FileStreamService;

@RestController
@RequestMapping("/file")
public class FileStreamController {

    private final FileStreamService service;

    public FileStreamController(FileStreamService service) {
        this.service = service;
    }

    @GetMapping("/{filename}")
    public ResponseEntity<ResourceRegion> stream(
            @PathVariable String filename,
            @RequestHeader HttpHeaders headers) {
        long contentLength = this.service.getFileLength(filename);
        long start = 0;
        long end = contentLength - 1;

        if (headers.getRange() != null && !headers.getRange().isEmpty()) {
            HttpRange range = headers.getRange().get(0);
            start = range.getRangeStart(contentLength);
            end = range.getRangeEnd(contentLength);
        }

        ResourceRegion region = this.service.getResourceRegion(filename, start, end);

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(region.getResource())
                        .orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(region);
    }

}
