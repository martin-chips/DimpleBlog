package com.dimple.modules.endModule.frontSetting.repository;

import com.dimple.modules.endModule.frontSetting.bean.Signature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : SignatureRepository
 * @description :
 * @date : 01/03/19 19:11
 */
public interface SignatureRepository extends JpaRepository<Signature, Integer> {

    @Modifying
    @Query(value = "insert  into signature(signature_key, signature_value) values (:signatureKey,:signatureValue)", nativeQuery = true)
    void createSignature(@Param("signatureKey") String signatureKey, @Param("signatureValue") String signatureValue);

    @Query("from Signature where signatureKey=:key")
    Signature getBySignatureKey(@Param("key") String key);

    @Modifying
    @Query("update Signature set signatureKey=:signatureKey,signatureValue=:signatureValue where id=:id")
    void updateSignature(@Param("id") Integer id, @Param("signatureKey") String signatureKey, @Param("signatureValue") String signatureValue);
}
