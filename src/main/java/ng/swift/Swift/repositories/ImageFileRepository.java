package ng.swift.Swift.repositories;

import ng.swift.Swift.models.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageFileRepository extends JpaRepository<ImageFile, Long> {
}
