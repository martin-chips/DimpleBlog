package com.dimple.service.impl;

import com.dimple.bean.LoginLog;
import com.dimple.dao.LoginLogMapper;
import com.dimple.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: LoginLogServiceImpl
 * @Description:
 * @Auther: Owenb
 * @Date: 11/29/18 18:16
 * @Version: 1.0
 */
@Service
@Transactional
public class LoginLogServiceImpl implements LoginLogService {
    @Autowired
    LoginLogMapper loginLogMapper;


    @Override
    public Integer insertLoginLog(LoginLog loginLog) {
        int i = loginLogMapper.insertSelective(loginLog);
        return i;
    }

    @Override
    public List<LoginLog> getAllLoginLog() {
        List<LoginLog> loginLogs = loginLogMapper.selectByExample(null);
        return loginLogs;
    }
}
