package com.dimple.modules.system.service.impl;

import com.dimple.config.FileProperties;
import com.dimple.exception.EntityExistException;
import com.dimple.exception.EntityNotFoundException;
import com.dimple.modules.system.domain.User;
import com.dimple.modules.system.repository.UserRepository;
import com.dimple.modules.system.service.UserService;
import com.dimple.modules.system.service.dto.JobSmallDto;
import com.dimple.modules.system.service.dto.RoleSmallDTO;
import com.dimple.modules.system.service.dto.UserDTO;
import com.dimple.modules.system.service.dto.UserQueryCriteria;
import com.dimple.modules.system.service.mapstruct.UserMapper;
import com.dimple.utils.FileUtil;
import com.dimple.utils.PageUtil;
import com.dimple.utils.QueryHelp;
import com.dimple.utils.RedisUtils;
import com.dimple.utils.SecurityUtils;
import com.dimple.utils.StringUtils;
import com.dimple.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @className: UserServiceImpl
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final FileProperties properties;
    private final RedisUtils redisUtils;

    @Override
    public Object queryAll(UserQueryCriteria criteria, Pageable pageable) {
        Page<User> page = userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(userMapper::toDto));
    }

    @Override
    public List<UserDTO> queryAll(UserQueryCriteria criteria) {
        List<User> users = userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder));
        return userMapper.toDto(users);
    }

    @Override
    @Cacheable(key = "'id:' + #p0")
    @Transactional(rollbackFor = Exception.class)
    public UserDTO findById(long id) {
        User user = userRepository.findById(id).orElseGet(User::new);
        ValidationUtil.isNull(user.getId(), "User", "id", id);
        return userMapper.toDto(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(User resources) {
        if (userRepository.findByUsername(resources.getUsername()) != null) {
            throw new EntityExistException(User.class, "username", resources.getUsername());
        }
        if (userRepository.findByEmail(resources.getEmail()) != null) {
            throw new EntityExistException(User.class, "email", resources.getEmail());
        }
        userRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(User resources) {
        User user = userRepository.findById(resources.getId()).orElseGet(User::new);
        ValidationUtil.isNull(user.getId(), "User", "id", resources.getId());
        User user1 = userRepository.findByUsername(resources.getUsername());
        User user2 = userRepository.findByEmail(resources.getEmail());

        if (user1 != null && !user.getId().equals(user1.getId())) {
            throw new EntityExistException(User.class, "username", resources.getUsername());
        }

        if (user2 != null && !user.getId().equals(user2.getId())) {
            throw new EntityExistException(User.class, "email", resources.getEmail());
        }
        // 如果用户的角色改变
        if (!resources.getRoles().equals(user.getRoles())) {
            redisUtils.del("data::user:" + resources.getId());
            redisUtils.del("menu::user:" + resources.getId());
            redisUtils.del("role::auth:" + resources.getId());
        }
        // 如果用户名称修改
        if (!resources.getUsername().equals(user.getUsername())) {
            redisUtils.del("user::username:" + user.getUsername());
        }
        user.setUsername(resources.getUsername());
        user.setEmail(resources.getEmail());
        user.setEnabled(resources.getEnabled());
        user.setRoles(resources.getRoles());
        user.setPhone(resources.getPhone());
        user.setNickName(resources.getNickName());
        user.setGender(resources.getGender());
        userRepository.save(user);
        // 清除缓存
        delCaches(user.getId(), user.getUsername());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCenter(User resources) {
        User user = userRepository.findById(resources.getId()).orElseGet(User::new);
        user.setNickName(resources.getNickName());
        user.setPhone(resources.getPhone());
        user.setGender(resources.getGender());
        userRepository.save(user);
        // 清理缓存
        delCaches(user.getId(), user.getUsername());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Long> ids) {
        for (Long id : ids) {
            // 清理缓存
            UserDTO user = findById(id);
            delCaches(user.getId(), user.getUsername());
        }
        userRepository.deleteAllByIdIn(ids);
    }

    @Override
    @Cacheable(key = "'username:' + #p0")
    public UserDTO findByName(String userName) {
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new EntityNotFoundException(User.class, "name", userName);
        } else {
            return userMapper.toDto(user);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePass(String username, String pass) {
        userRepository.updatePass(username, pass, new Date());
        redisUtils.del("user::username:" + username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> updateAvatar(MultipartFile multipartFile) {
        User user = userRepository.findByUsername(SecurityUtils.getCurrentUsername());
        String oldPath = user.getAvatarPath();
        File file = FileUtil.upload(multipartFile, properties.getPath().getAvatar());
        user.setAvatarPath(Objects.requireNonNull(file).getPath());
        user.setAvatarName(file.getName());
        userRepository.save(user);
        if (StringUtils.isNotBlank(oldPath)) {
            FileUtil.del(oldPath);
        }
        redisUtils.del("user::username:" + user.getUsername());
        return new HashMap<String, String>(1) {{
            put("avatar", file.getName());
        }};
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEmail(String username, String email) {
        userRepository.updateEmail(username, email);
        redisUtils.del("user::username:" + username);
    }

    @Override
    public void download(List<UserDTO> queryAll, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (UserDTO userDTO : queryAll) {
            List<String> roles = userDTO.getRoles().stream().map(RoleSmallDTO::getName).collect(Collectors.toList());
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("用户名", userDTO.getUsername());
            map.put("角色", roles);
            map.put("岗位", userDTO.getJobs().stream().map(JobSmallDto::getName).collect(Collectors.toList()));
            map.put("邮箱", userDTO.getEmail());
            map.put("状态", userDTO.getEnabled() ? "启用" : "禁用");
            map.put("手机号码", userDTO.getPhone());
            map.put("修改密码的时间", userDTO.getPwdResetTime());
            map.put("创建日期", userDTO.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 清理缓存
     *
     * @param id /
     */
    public void delCaches(Long id, String username) {
        redisUtils.del("user::id:" + id);
        redisUtils.del("user::username:" + username);
    }
}
