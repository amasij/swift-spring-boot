package ng.swift.Swift.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "ImageFile")
public class ImageFile {
    private @Id
    @GeneratedValue(strategy= GenerationType.AUTO) Long id;

    @Column(nullable=false)
    private byte[] data;

    @Column(nullable=false)
    private Date dateCreated;

    @Column(nullable=false)
    private String contentType;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private EntityStatusConstant status;

}
