package com.dimple.repository;

import com.dimple.bean.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: SettingRepository
 * @Description:
 * @Auther: Owenb
 * @Date: 01/02/19 20:50
 * @Version: 1.0
 */
@Repository
public interface SettingRepository extends JpaRepository<Setting, Integer> {

    @Modifying
    @Query(value = "insert  into setting(setting_key,setting_value) values (:settingKey,:settingValue)", nativeQuery = true)
    void createSetting(@Param("settingKey") String settingKey, @Param("settingValue") String settingValue);


    @Query(value = "from Setting where id=:id")
    Setting getSettingById(@Param("id") Integer id);


    @Query("from Setting where settingKey=:settingKey")
    Setting getSettingBySettingKey(@Param("settingKey") String settingKey);


    void deleteById(Integer id);

    @Modifying
    @Query("update Setting set settingKey=:settingKey,settingValue=:settingValue where id=:id")
    void updateSetting(@Param("id") Integer id, @Param("settingKey") String settingKey, @Param("settingValue") String settingValue);

    @Query(value = "from Setting where settingKey=:settingKey")
    Setting getSettingValueBySettingKey(@Param("settingKey") String settingKey);
}
