package ng.swift.Swift.serviceImpl;

import lombok.RequiredArgsConstructor;
import ng.swift.Swift.models.EntityStatusConstant;
import ng.swift.Swift.models.ImageFile;
import ng.swift.Swift.repositories.ImageFileRepository;
import ng.swift.Swift.service.ImageFileService;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Named;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Named
@RequiredArgsConstructor
public class ImageFileServiceImpl implements ImageFileService {
    private final ImageFileRepository imageFileRepository;

    @Override
    public ImageFile uploadImageFile(MultipartFile file) throws IOException {
        ImageFile imageFile = new ImageFile();
        imageFile.setContentType(file.getContentType());
        imageFile.setData(file.getBytes());
        imageFile.setDateCreated(new Date());
        imageFile.setName(file.getOriginalFilename());
        imageFile.setStatus(EntityStatusConstant.ACTIVE);
        return imageFileRepository.save(imageFile);
    }

    @Override
    public Optional<ImageFile> getImageFile(Long id) {
        return imageFileRepository.findById(id);
    }
}
