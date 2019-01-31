package com.y3wegy.web.controller.api.meida;

import java.io.IOException;

import com.y3wegy.web.mapper.business.FileMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.y3wegy.base.ServiceExeption;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.base.web.bean.web.ResponseJson;
import com.y3wegy.base.web.bean.user.SecurityUser;
import com.y3wegy.web.bean.business.FileDto;

/**
 * @author y3wegy
 */
@RestController
@RequestMapping("/api/file")
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    private static final String UPLOAD_FILE = "uploadFile";

    @Autowired
    private FileMapper fileMapper;

    @RequiresRoles("USER")
    @PostMapping(value = "/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws ServiceExeption {
        ResponseJson responseJson = new ResponseJson().code(UPLOAD_FILE);
        try {
            Subject currentSubject = SecurityUtils.getSubject();
            SecurityUser securityUser = (SecurityUser) currentSubject.getPrincipal();

            String fileName = file.getOriginalFilename();
            FileDto fileDto = new FileDto();
            fileDto.setFileName(fileName);
            fileDto.setContent(file.getBytes());
            fileDto.setLastUpdateBy(securityUser.getUserName());

            fileMapper.save(fileDto);
        } catch (IOException e) {
            logger.error("save file failed", e);
            responseJson.fail(e.getMessage());
        }
        return JackSonHelper.obj2JsonStr(responseJson);
    }

    /*@PostMapping(value = "/upload/batch")
    public String batchUpload(HttpServletRequest request) throws ServiceExeption {
        Subject currentSubject = SecurityUtils.getSubject();
        SecurityUser securityUser = (SecurityUser) currentSubject.getPrincipal();

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        ResponseJson responseJson = new ResponseJson(UPLOAD_FILE);
        for (int i = 0; i < files.size(); ++i) {
            MultipartFile file = files.get(i);
            if (!saveFile(file)) {
                responseJson.fail("save failed");
            }
        }
        return JackSonHelper.obj2JsonStr(responseJson);
    }*/
}
