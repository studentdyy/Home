package com.hmdp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.dto.LoginFormDTO;
import com.hmdp.dto.Result;
import com.hmdp.dto.UserDTO;
import com.hmdp.entity.User;
import com.hmdp.mapper.UserMapper;
import com.hmdp.service.IUserService;
import com.hmdp.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.*;
import static com.hmdp.utils.SystemConstants.USER_NICK_NAME_PREFIX;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result sendCode(String phone, HttpSession session) {
        //1，校验手机号，
        if(RegexUtils.isPhoneInvalid(phone)){
            //2，不符合就返回错误信息
            return Result.fail("手机号格式错误");
        }
        //3，符合就生产验证码
        String code = RandomUtil.randomNumbers(6);
        //4，保存验证码到session中
//        session.setAttribute("code",code);
        stringRedisTemplate.opsForValue().set(LOGIN_CODE_KEY + phone,code,LOGIN_CODE_TTL, TimeUnit.MINUTES);
        //5，发送验证码，返回ok
        log.debug("发送短信验证码成功，验证码为:{}",code);
        return Result.ok(code);
    }

    @Override
    public Result login(LoginFormDTO loginForm, HttpSession session) {
        //1,校验手机号
        String phone = loginForm.getPhone();
        if(RegexUtils.isPhoneInvalid(phone)){
            //2，不符合就返回错误信息
            return Result.fail("手机号格式错误");
        }
        //2，校验验证码
//        Object cacheCode = session.getAttribute("code");
        //2,从redis中获取验证码
        String cacheCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + phone);
        String code = loginForm.getCode();
        if(code == null || !cacheCode.equals(code)){
            //3,不一致就报错
            return Result.fail("验证码错误");
        }
        //4，一致，就根据手机号查询用户，select * from tb_user where phone = ?
        User user = query().eq("phone", phone).one();

        //5,判断用户是否存在
        if(user == null){
            user = createUserWithPhone(phone);
        }
        //7，将用户保存到redis中
        //7.1随机生成token，作为登录令牌
        String token = UUID.randomUUID().toString(true);
        //7.2将user对象转为hash存储
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        Map<String, Object> userMap = BeanUtil.beanToMap(userDTO,new HashMap<>(), CopyOptions.create()
                .setIgnoreNullValue(true)
                .setFieldValueEditor((fieldName,fieldValue) -> fieldValue.toString())
        );
        //7.3存储
        String tokenKey = LOGIN_USER_KEY + token;
        stringRedisTemplate.opsForHash().putAll(tokenKey,userMap);
        //7.4设置有效期
        stringRedisTemplate.expire(tokenKey,LOGIN_USER_TTL,TimeUnit.MINUTES);

        //session.setAttribute("user", BeanUtil.copyProperties(user, UserDTO.class));


        //8，返回token给前端

        return Result.ok(token);
    }

    @Override
    public Result queryUserById(Long userId) {
        User user = getById(userId);
        if (user == null) return Result.ok();

        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);

        return Result.ok(userDTO);
    }

    @Override
    public Result sign() {
        //1,获取当前用户
        Long userId = UserHolder.getUser().getId();
        //2，获取日期
        LocalDateTime now = LocalDateTime.now();
        //3，拼接key
        String keySuffix = now.format(DateTimeFormatter.ofPattern(":yyyyMM"));
        String key = USER_SIGN_KEY + userId + keySuffix;
        //4，获取今天是第几天
        int dayOfMonth = now.getDayOfMonth();
        //5，写入redis SETBIT key offset 1
        stringRedisTemplate.opsForValue().setBit(key,dayOfMonth - 1,true);
        return Result.ok();
    }

    /**
     * 统计当前用户的月总签到数
     * @return
     */
    @Override
    public Result signCount() {
        //1,获取当前用户
        Long userId = UserHolder.getUser().getId();
        //2，获取日期
        LocalDateTime now = LocalDateTime.now();
        //3，拼接key
        String keySuffix = now.format(DateTimeFormatter.ofPattern(":yyyyMM"));
        String key = USER_SIGN_KEY + userId + keySuffix;
        //4，获取今天是第几天
        int dayOfMonth = now.getDayOfMonth();
        //5,获取本月截止今天为止的所有的签到记录，返回的是一个十进制的数字
        List<Long> result = stringRedisTemplate.opsForValue().bitField(
                key,
                BitFieldSubCommands.create()
                        .get(BitFieldSubCommands.BitFieldType.unsigned(dayOfMonth))
                        .valueAt(0)
        );
        if (result == null || result.isEmpty()) {
            return Result.ok(0);
        }
        Long num = result.get(0);
        if (num == null || num == 0) {
            return Result.ok(0);
        }
        //6，遍历循环
        int count = 0 ;
        while (true){
            //让这个数字与1做与运算，得到数字的最后一个bit位置 //判断这个bit是否为0
            if ((num & 1) == 0){
                //如果为0，说明为签到，结束
                break;
            }else {
                //如果不为0，说明已签到计算器+1
                count++;
            }
            //把数字右移一位，抛弃最后一个bit位，继续下一个bit位
            num >>>= 1;
        }
        return Result.ok(count);
    }

    private User createUserWithPhone(String phone) {
        User user = new User();
        user.setPhone(phone);
        user.setNickName(USER_NICK_NAME_PREFIX + RandomUtil.randomString(10));
        //保存用户
        save(user);
        return user;
    }
}