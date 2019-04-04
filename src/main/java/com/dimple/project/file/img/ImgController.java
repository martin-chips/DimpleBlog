package com.dimple.project.file.img;

import com.dimple.common.utils.file.QiNiuUtils;
import com.dimple.framework.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @className: ImgController
 * @description:
 * @auther: Dimple
 * @date: 04/03/19
 * @version: 1.0
 */
@Controller
public class ImgController {
    @Autowired
    QiNiuUtils qiNiuUtils;

    @GetMapping("/list")
    @ResponseBody
    public AjaxResult list() {
        List<String> list = qiNiuUtils.selectImgList();
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("list", list);
        return ajaxResult;
    }

    @GetMapping("/file/img")
    public String img(Model model) {
        model.addAttribute("list", qiNiuUtils.selectImgList());
        return "file/img/img";
    }
}
