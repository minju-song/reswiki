package com.teddybear.reswiki.core;

import com.teddybear.reswiki.core.errors.exception.Exception404;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {
    private final String imageDir = "/Users/songminju/uploads/restaurants/"; // 실제 경로로 변경

    @GetMapping("api/images")
    public ResponseEntity<Resource> getImage(@RequestParam("imageName") String imageName) {
        Resource resource = new FileSystemResource(imageDir+imageName);

        // 파일 존재 여부 확인
        if (!resource.exists()) {
            throw new Exception404("파일없음"); // 파일이 존재하지 않을 경우 404 반환
        }

        try {
            System.out.println(resource.getInputStream());
            System.out.println(resource.contentLength());
        } catch (Exception e) {
            System.out.println(e);
        }

        ResponseEntity<Resource> response = ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // 적절한 Content-Type 설정
                .body(resource);

        return response;
    }
}
