package com.dimple.rest;

import com.dimple.annotation.OLog;
import com.dimple.domain.Picture;
import com.dimple.service.PictureService;
import com.dimple.service.dto.PictureQueryCriteria;
import com.dimple.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: PictureController
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pictures")
@Api(tags = "工具：免费图床管理")
public class PictureController {

    private final PictureService pictureService;

    @OLog("查询图片")
    @PreAuthorize("@ps.check('pictures:list')")
    @GetMapping
    @ApiOperation("查询图片")
    public ResponseEntity<Object> query(PictureQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(pictureService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @OLog("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@ps.check('pictures:list')")
    public void download(HttpServletResponse response, PictureQueryCriteria criteria) throws IOException {
        pictureService.download(pictureService.queryAll(criteria), response);
    }

    @OLog("上传图片")
    @PreAuthorize("@ps.check('pictures:add')")
    @PostMapping
    @ApiOperation("上传图片")
    public ResponseEntity<Object> upload(@RequestParam MultipartFile file) {
        String userName = SecurityUtils.getCurrentUsername();
        Picture picture = pictureService.upload(file, userName);
        return new ResponseEntity<>(picture, HttpStatus.OK);
    }

    @OLog("同步图床数据")
    @ApiOperation("同步图床数据")
    @PostMapping(value = "/synchronize")
    public ResponseEntity<Object> synchronize() {
        pictureService.synchronize();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @OLog("多选删除图片")
    @ApiOperation("多选删除图片")
    @PreAuthorize("@ps.check('pictures:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        pictureService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
