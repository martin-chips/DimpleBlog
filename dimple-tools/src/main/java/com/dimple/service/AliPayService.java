package com.dimple.service;

import com.dimple.domain.AlipayConfig;
import com.dimple.domain.vo.TradeVO;

/**
 * @className: AliPayService
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public interface AliPayService {

    /**
     * 查询配置
     *
     * @return AlipayConfig
     */
    AlipayConfig find();

    /**
     * 更新配置
     *
     * @param alipayConfig 支付宝配置
     * @return AlipayConfig
     */
    AlipayConfig config(AlipayConfig alipayConfig);

    /**
     * 处理来自PC的交易请求
     *
     * @param alipay 支付宝配置
     * @param trade  交易详情
     * @return String
     * @throws Exception 异常
     */
    String toPayAsPc(AlipayConfig alipay, TradeVO trade) throws Exception;

    /**
     * 处理来自手机网页的交易请求
     *
     * @param alipay 支付宝配置
     * @param trade  交易详情
     * @return String
     * @throws Exception 异常
     */
    String toPayAsWeb(AlipayConfig alipay, TradeVO trade) throws Exception;
}
