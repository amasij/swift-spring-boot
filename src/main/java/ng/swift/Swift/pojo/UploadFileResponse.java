package ng.swift.Swift.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadFileResponse {
    private String fileName;
    private String fileDownloadUri;
    private String contentType;
    private Long fileId;
    private long size;

    public UploadFileResponse(String fileName, String fileDownloadUri, String contentType, Long fileId, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.contentType = contentType;
        this.fileId = fileId;
        this.size = size;
    }
}
