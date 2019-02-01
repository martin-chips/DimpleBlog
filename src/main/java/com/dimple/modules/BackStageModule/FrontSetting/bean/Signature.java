package com.dimple.modules.BackStageModule.FrontSetting.bean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : Signature
 * @description :
 * @date : 01/03/19 19:11
 */
@Entity
public class Signature {
    private Integer id;
    private String signatureKey;
    private String signatureValue;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "signature_key", nullable = true, length = 64)
    public String getSignatureKey() {
        return signatureKey;
    }

    public void setSignatureKey(String signatureKey) {
        this.signatureKey = signatureKey;
    }

    @Basic
    @Column(name = "signature_value", nullable = true, length = -1)
    public String getSignatureValue() {
        return signatureValue;
    }

    public void setSignatureValue(String signatureValue) {
        this.signatureValue = signatureValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Signature signature = (Signature) o;
        return Objects.equals(id, signature.id) &&
                Objects.equals(signatureKey, signature.signatureKey) &&
                Objects.equals(signatureValue, signature.signatureValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, signatureKey, signatureValue);
    }
}
