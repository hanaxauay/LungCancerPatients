package com.example.cancerpatients.service;

import com.example.cancerpatients.dto.GalleryDto;
import com.example.cancerpatients.entity.Gallery;
import com.example.cancerpatients.repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GalleryService {
    @Autowired
    private GalleryRepository galleryRepository;

    public GalleryService(GalleryRepository galleryRepository) {
        this.galleryRepository =galleryRepository;
    }

    @Transactional
    public Long savePost(GalleryDto galleryDto) {
        Gallery gallery = Gallery.builder()
                .title(galleryDto.getTitle())
                .content(galleryDto.getContent())
                .author(galleryDto.getAuthor())
                .write_time(LocalDate.from(LocalDateTime.now())) // 현재 시간으로 설정
                .filePath(galleryDto.getFilePath()) // 파일 경로 추가
                .fileName(galleryDto.getFileName()) // 파일 이름 추가
                .build();

        return galleryRepository.save(gallery).getSeq();
    }

    @Transactional
    public void updateGallery(Long id, GalleryDto galleryDto) {
        Optional<Gallery> optionalGallery = galleryRepository.findById(id);

        if (optionalGallery.isPresent()) {
            Gallery existingGallery = optionalGallery.get();

            existingGallery.setTitle(galleryDto.getTitle());
            existingGallery.setContent(galleryDto.getContent());
            existingGallery.setAuthor(galleryDto.getAuthor());

            // 기타 필요한 업데이트 작업 수행

            // 업데이트된 기부 내용을 저장
            galleryRepository.save(existingGallery);
        } else {
            throw new RuntimeException("공지사항을 찾을 수 없습니다: " + id);
        }
    }

    public void deleteGallery(Long id) {
        galleryRepository.deleteById(id);
    }


    @Transactional
    public List<GalleryDto> getGalleryList(Sort sort){
        List<Gallery> galleryList = galleryRepository.findAll(sort);
        List<GalleryDto> galleryDtoList = new ArrayList<>();

        for(Gallery gallery : galleryList) {
            GalleryDto galleryDto = GalleryDto.builder()
                    .seq(gallery.getSeq())
                    .title(gallery.getTitle())
                    .content(gallery.getContent())
                    .author(gallery.getAuthor())
                    .write_time(gallery.getWrite_time())
                    .build();
            galleryDtoList.add(galleryDto);

        }
        return galleryDtoList;
    }

    public List<Gallery> getAllGallery() {
        return galleryRepository.findAll();
    }

    public Optional<Gallery> getGalleryById(Long seq) {
        return galleryRepository.findById(seq);
    }
}
