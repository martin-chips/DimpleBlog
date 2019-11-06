package com.dimple.project.tool.qiNiu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dimple.common.constant.ConfigKey;
import com.dimple.common.exception.CustomException;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.common.utils.StringUtils;
import com.dimple.common.utils.file.FileUtils;
import com.dimple.project.system.domain.Config;
import com.dimple.project.system.service.ConfigService;
import com.dimple.project.tool.qiNiu.domain.QiNiuConfig;
import com.dimple.project.tool.qiNiu.domain.QiNiuContent;
import com.dimple.project.tool.qiNiu.mapper.QiNiuContentMapper;
import com.dimple.project.tool.qiNiu.service.QiNiuService;
import com.dimple.project.tool.qiNiu.utils.QiNiuUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @className: QiNiuServiceImpl
 * @description:
 * @author: Dimple
 * @date: 11/06/19
 */
@Service
@Slf4j
public class QiNiuServiceImpl implements QiNiuService {

    @Autowired
    ConfigService configService;
    @Autowired
    QiNiuContentMapper qiNiuContentMapper;

    @Override
    public QiNiuConfig getQiNiuConfig() {
        Config config = configService.selectConfigByKey(ConfigKey.QI_NIU_CONFIG_KEY);
        //七牛云Config的配置Json
        String configValue = config.getConfigValue();
        if (StringUtils.isEmpty(configValue)) {
            return new QiNiuConfig();
        }
        QiNiuConfig qiNiuConfig = JSONObject.parseObject(configValue, QiNiuConfig.class);
        //secretKey 打码
        qiNiuConfig.setSecretKey("**************************");
        return qiNiuConfig;
    }

    @Override
    public int updateQiNiuConfig(QiNiuConfig qiNiuConfig) {
        String qiNiuConfigString = JSONObject.toJSONString(qiNiuConfig);
        Config config = new Config();
        config.setConfigKey(ConfigKey.QI_NIU_CONFIG_KEY);
        config.setConfigValue(qiNiuConfigString);
        config.setUpdateBy(SecurityUtils.getUsername());
        return configService.updateConfigByConfigKey(config);
    }

    @Override
    public QiNiuContent upload(MultipartFile file) {
        //获取七牛云信息
        QiNiuConfig qiNiuConfig = getQiNiuConfig();
        if (!qiNiuConfig.check()) {
            throw new CustomException("七牛云配置信息不完整,请先填写七牛云配置信息");
        }
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(QiNiuUtils.getRegion(qiNiuConfig.getZone()));
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(qiNiuConfig.getAccessKey(), qiNiuConfig.getSecretKey());
        //生成上传文件Token
        String upToken = auth.uploadToken(qiNiuConfig.getBucket());
        try {
            String key = file.getOriginalFilename();
            Response response = uploadManager.put(file.getBytes(), key, upToken);
            //解析
            DefaultPutRet defaultPutRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
            //将结果存入数据库
            QiNiuContent qiNiuContent = new QiNiuContent();
            qiNiuContent.setSuffix(FileUtils.getExtensionName(defaultPutRet.key));
            qiNiuContent.setBucket(qiNiuConfig.getBucket());
            qiNiuContent.setType(qiNiuConfig.getType());
            qiNiuContent.setName(FileUtils.getFileNameNoExtension(defaultPutRet.key));
            qiNiuContent.setUrl(qiNiuConfig.getHost() + "/" + defaultPutRet.key);
            qiNiuContent.setSize(FileUtils.getSizeString(Integer.parseInt(file.getSize() + "")));
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return null;
    }

    @Override
    public int synchronize() {
        QiNiuConfig qiNiuConfig = getQiNiuConfig();
        if (!qiNiuConfig.check()) {
            throw new CustomException("七牛云配置信息不完整,请先填写七牛云配置信息");
        }
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(QiNiuUtils.getRegion(qiNiuConfig.getZone()));
        Auth auth = Auth.create(qiNiuConfig.getAccessKey(), qiNiuConfig.getSecretKey());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        //文件名前缀
        String prefix = "";
        //每次迭代的长度限制，最大1000，推荐值 1000
        int limit = 1000;
        //指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
        String delimiter = "";
        //列举空间文件列表
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(qiNiuConfig.getBucket(), prefix, limit, delimiter);
        List<QiNiuContent> qiNiuContentList = new ArrayList<>();
        while (fileListIterator.hasNext()) {
            //处理获取的file list结果
            QiNiuContent qiNiuContent;
            FileInfo[] items = fileListIterator.next();
            for (FileInfo item : items) {
                qiNiuContent = new QiNiuContent();
                qiNiuContent.setSize(FileUtils.getSizeString(Integer.parseInt(item.fsize + "")));
                qiNiuContent.setSuffix(FileUtils.getExtensionName(item.key));
                qiNiuContent.setName(FileUtils.getFileNameNoExtension(item.key));
                qiNiuContent.setType(qiNiuConfig.getType());
                qiNiuContent.setBucket(qiNiuConfig.getBucket());
                qiNiuContent.setUrl(qiNiuConfig.getHost() + "/" + item.key);
                qiNiuContentList.add(qiNiuContent);
            }
        }
        return qiNiuContentList.size();
    }

    @Override
    public int delete(Long id) {
        QiNiuConfig qiNiuConfig = getQiNiuConfig();
        if (!qiNiuConfig.check()) {
            throw new CustomException("七牛云配置信息不完整,请先填写七牛云配置信息");
        }
        //查询
        QiNiuContent qiNiuContent = qiNiuContentMapper.selectContentById(id);
        if (Objects.isNull(qiNiuContent) || StringUtils.isEmpty(qiNiuContent.getName())) {
            throw new CustomException("数据异常");
        }
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(QiNiuUtils.getRegion(qiNiuConfig.getZone()));
        Auth auth = Auth.create(qiNiuConfig.getAccessKey(), qiNiuConfig.getSecretKey());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(qiNiuContent.getBucket(), qiNiuContent.getName());
        } catch (QiniuException e) {
            log.error("删除七牛云图片出错,{},", e.getMessage(), e);
            //出错后删除本地数据库文件
        }
        return qiNiuContentMapper.deleteContentById(id);
    }

    @Override
    public String getDownloadUrl(Long id) {
        QiNiuConfig qiNiuConfig = getQiNiuConfig();
        if (!qiNiuConfig.check()) {
            throw new CustomException("七牛云配置信息不完整,请先填写七牛云配置信息");
        }
        QiNiuContent qiNiuContent = qiNiuContentMapper.selectContentById(id);
        if (Objects.isNull(qiNiuConfig)) {
            throw new CustomException("对应文件不存在,建议同步数据后再试");
        }
        if ("公开".equals(qiNiuConfig.getType())) {
            return qiNiuContent.getUrl();
        } else {
            Auth auth = Auth.create(qiNiuConfig.getAccessKey(), qiNiuConfig.getSecretKey());
            // 1小时，可以自定义链接过期时间
            long expireInSeconds = 3600;
            return auth.privateDownloadUrl(qiNiuContent.getUrl(), expireInSeconds);
        }
    }

    @Override
    public List<QiNiuContent> selectContentList(QiNiuContent qiNiuContent) {
        return qiNiuContentMapper.selectContentList(qiNiuContent);
    }

}
