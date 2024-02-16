package com.muhammet.restaurantapplication.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "file_storage")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FileStorage extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_byte", length = 5000)
    private byte[] fileByte;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "suffix")
    private String suffix;

}
