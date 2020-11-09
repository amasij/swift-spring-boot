package ng.swift.Swift.service;

import ng.swift.Swift.models.ImageFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageFileService {
    ImageFile uploadImageFile(MultipartFile file) throws IOException;
}
