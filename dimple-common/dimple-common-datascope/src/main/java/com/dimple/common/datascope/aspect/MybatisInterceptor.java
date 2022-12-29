package com.dimple.common.datascope.aspect;

import com.dimple.common.core.utils.reflect.ReflectUtils;
import com.dimple.common.security.utils.SecurityUtils;
import com.dimple.system.api.model.LoginUser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/**
 * MybatisInterceptor
 *
 * @author Dimple
 */
@Slf4j
@Component
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class MybatisInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameter = invocation.getArgs()[1];
        if (parameter == null) {
            return invocation.proceed();
        }
        LoginUser loginUser = SecurityUtils.getLoginUser();
        String username = Optional.ofNullable(loginUser).map(LoginUser::getUsername).orElse("");
        switch (sqlCommandType) {
            case INSERT:
                Field createBy = ReflectUtils.getAccessibleField(parameter, "createBy");
                setValue(parameter, createBy, username);
                Field createTime = ReflectUtils.getAccessibleField(parameter, "createTime");
                setValue(parameter, createTime, new Date());
                break;
            case UPDATE:
                Field updateBy = ReflectUtils.getAccessibleField(parameter, "updateBy");
                setValue(parameter, updateBy, username);
                Field updateTime = ReflectUtils.getAccessibleField(parameter, "updateTime");
                setValue(parameter, updateTime, new Date());
                break;
            default:
                break;
        }
        return invocation.proceed();
    }

    @SneakyThrows
    private void setValue(Object parameter, Field field, Object value) {
        if (Objects.isNull(field)) {
            log.warn("field is null, just ignore set value.");
            return;
        }
        field.setAccessible(true);
        field.set(parameter, value);
    }
}
