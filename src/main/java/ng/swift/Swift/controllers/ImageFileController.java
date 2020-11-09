package ng.swift.Swift.controllers;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import ng.swift.Swift.exception.ErrorResponse;
import ng.swift.Swift.models.ImageFile;
import org.apache.commons.io.IOUtils;
import ng.swift.Swift.pojo.UploadFileResponse;
import ng.swift.Swift.repositories.ImageFileRepository;
import ng.swift.Swift.service.ImageFileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class ImageFileController {
    private final ImageFileService imageFileService;
    private final ImageFileRepository imageFileRepository;

    @PostMapping("/upload")
    public ResponseEntity<UploadFileResponse> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        ImageFile imageFile = imageFileService.uploadImageFile(file);
        UploadFileResponse response = new UploadFileResponse(imageFile.getName(),
                null,
                file.getContentType(),
                imageFile.getId(),
                file.getSize());
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id:[0-9]+}")
    public RedirectView downloadFile(@PathVariable("id")
                                             long id, HttpServletResponse response) throws IOException {
        ImageFile file = imageFileRepository.findById(id).orElseThrow(() -> new ErrorResponse(HttpStatus.NOT_FOUND, "File not found"));
//        if (StringUtils.isNotBlank(file.getExternalReferencePath())) {
//            return new RedirectView(bwFile.getExternalReferencePath());
//        }
        response.setContentType(file.getContentType());
        IOUtils.write(file.getData(), response.getOutputStream());
        response.flushBuffer();
        return null;
    }
}
