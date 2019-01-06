package com.dimple.service.impl;

import com.dimple.bean.Setting;
import com.dimple.bean.Signature;
import com.dimple.repository.SettingRepository;
import com.dimple.repository.SignatureRepository;
import com.dimple.service.SettingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : SettingServiceImpl
 * @description :
 * @date : 01/03/19 12:13
 */
@Service
@Transactional
public class SettingServiceImpl implements SettingService {

    @Autowired
    SettingRepository settingRepository;

    @Autowired
    SignatureRepository signatureRepository;

    @Override
    public void createSetting(Setting setting) {
        if (setting != null) {
            settingRepository.createSetting(setting.getSettingKey(), setting.getSettingValue());
        }
    }

    @Override
    public Setting getSettingById(Integer id) {

        return settingRepository.getSettingById(id);
    }

    @Override
    public Setting getSettingBySettingKey(String settingKey) {
        return settingRepository.getSettingBySettingKey(settingKey);
    }

    @Override
    public void deleteSettingById(Integer id) {
        settingRepository.deleteById(id);
    }

    @Override
    public void updateSetting(List<Setting> settings) {
        if (settings == null || settings.size() == 0) {
            return;
        }
        for (Setting setting : settings) {
            if (setting == null || StringUtils.isBlank(setting.getSettingKey()) || StringUtils.isBlank(setting.getSettingValue())) {
                continue;
            }
            Setting settingBySettingKey = settingRepository.getSettingBySettingKey(setting.getSettingKey());
            if (settingBySettingKey != null) {
                settingRepository.updateSetting(settingBySettingKey.getId(), setting.getSettingKey(), setting.getSettingValue());
            } else {
                settingRepository.createSetting(setting.getSettingKey(), setting.getSettingValue());
            }
        }
    }

    @Override
    public Signature getSignatureByKey(String key) {
        return signatureRepository.getBySignatureKey(key);
    }

    @Override
    public void insertOrUpdateSignature(Signature signature) {
        if (signature != null && StringUtils.isNotBlank(signature.getSignatureKey()) && StringUtils.isNotBlank(signature.getSignatureValue())) {
            if (signature.getId() == null) {
                signatureRepository.createSignature(signature.getSignatureKey(), signature.getSignatureValue());
            } else {
                signatureRepository.updateSignature(signature.getId(), signature.getSignatureKey(), signature.getSignatureValue());
            }
        }
    }

    @Override
    public List<Setting> getAllSetting() {
        List<Setting> all = settingRepository.findAll();
        return all;
    }

    @Override
    public String getSettingValueBySettingKey(String settingKey) {
        Setting settingValueBySettingKey = settingRepository.getSettingValueBySettingKey(settingKey);
        return settingValueBySettingKey == null ? "" : settingValueBySettingKey.getSettingValue();
    }
}
