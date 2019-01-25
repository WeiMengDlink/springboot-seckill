package com.ypj.configuration;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/** 配置校验数据需要的Validator，配置MethodValidationPostProcessor结合@Valided注解来使用
 * @ClassName ValidationConfig
 * @Author pain
 * @Date 2019/1/7 18:00
 * @Version 1.0
 **/
@Configuration
public class ValidationConfig {

    /**
     * 使用@Valid注解可以对对象进行校验，如：@Valid UserModel userModel
     * 但是如果是使用@RequestParam注解的参数，这时候需要设置MethodValidationPostProcessor，如：@RequestParam("phoneNumber") String phoneNumber
     *
     * @return
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        postProcessor.setValidator(validator());
        return postProcessor;
    }

    /**
     * 设置Validator为快速失败模式
     * 普通模式（默认）：校验所有要校验的属性，然后返回所有错误信息
     * 快速失败模式：只要有一个属性校验错误即返回
     * 如：设置为普通模式，如果用户名为空，密码也为空，则会返回用户名不能为空、密码不能为空的错误信息
     * 设置为快速失败模式，只会返回用户名不能为空的错误信息
     *
     * @return
     */
    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .addProperty("hibernate.validator.fail_fast", "true")
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }

}
