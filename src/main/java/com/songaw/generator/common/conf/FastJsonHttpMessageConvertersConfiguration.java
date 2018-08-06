package com.songaw.generator.common.conf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

@Configuration
@ConditionalOnClass({JSON.class}) //1
public class FastJsonHttpMessageConvertersConfiguration {

    @Bean

    public HttpMessageConverters getConverters() {

        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();

        FastJsonConfig config = new FastJsonConfig();

        config.setSerializerFeatures(

                SerializerFeature.WriteMapNullValue,

                SerializerFeature.QuoteFieldNames,

                SerializerFeature.IgnoreNonFieldGetter

        );

        SerializeConfig serializeConfig = new SerializeConfig();

        serializeConfig.put(BigInteger.class, ToStringSerializer.instance);

        serializeConfig.put(Long.class, ToStringSerializer.instance);

        serializeConfig.put(Long.TYPE, ToStringSerializer.instance);

        config.setSerializeConfig(serializeConfig);

        converter.setFastJsonConfig(config);

        List<MediaType> mediaTypes = Arrays.asList(

                MediaType.APPLICATION_JSON_UTF8,

                MediaType.TEXT_PLAIN,

                MediaType.TEXT_HTML,

                MediaType.TEXT_XML,

                MediaType.APPLICATION_OCTET_STREAM);

        converter.setSupportedMediaTypes(mediaTypes);

        return new HttpMessageConverters(converter);

    }
}