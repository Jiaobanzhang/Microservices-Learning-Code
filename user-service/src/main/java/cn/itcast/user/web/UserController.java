package cn.itcast.user.web;

import cn.itcast.user.config.PatternProperties;
import cn.itcast.user.pojo.User;
import cn.itcast.user.service.UserService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Slf4j
@RestController
@RequestMapping("/user")
//@RefreshScope
public class UserController {

    @Autowired
    private UserService userService;

//    @Value("${pattern.dateformat}")
//    private String dateformat;

    @Autowired
    private PatternProperties properties;

    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id) {
        return userService.queryById(id);
    }

    @GetMapping("prop")
    public PatternProperties properties(){
        return properties;
    }

    @GetMapping("now")
    public String now(){
        System.out.println("你好");
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern(properties.getDateformat(), Locale.CHINA)));
        return LocalDate.now().format(DateTimeFormatter.ofPattern(properties.getDateformat(), Locale.CHINA));
    }
}
