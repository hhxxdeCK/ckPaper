package com.ck.paper.notice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @param
 * @author ck
 * @description
 * @date 2020/3/16 0:45
 * @return
 */

@Service
public interface PicturesService {
    Map<String, Object> uploadQusImages(String file);
}
