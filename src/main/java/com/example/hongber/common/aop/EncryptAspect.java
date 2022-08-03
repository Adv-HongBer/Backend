package com.example.hongber.common.aop;

import com.example.hongber.common.annotation.Encrypt;
import com.example.hongber.common.enumeration.EncryptType;
import com.example.hongber.common.enumeration.EncryptType2;
import com.example.hongber.common.util.encrypt.AESEncryptor;
import com.example.hongber.common.util.encrypt.MD5Encryptor;
import com.example.hongber.common.util.encrypt.PBKDF2Encryptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@Order(1)
@Component
public class EncryptAspect {
    @Around(value = "(execution(* com.example.hongber..*Service.*find*(*)) || execution(* com.example.hongber..*Service.*save*(*)))")
    public Object encrypt(ProceedingJoinPoint pjp) throws Throwable {
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        String methodNm = method.getName();
        Encrypt encrypt = method.getAnnotation(Encrypt.class);
        boolean decFlag = false;

        String INSERT = "insert";
        String UPDATE = "update";
        String SAVE = "save";
        if (methodNm.startsWith(INSERT) || methodNm.startsWith(UPDATE) || methodNm.startsWith(SAVE) || encrypt.selOpt()) {
            encParam(pjp);
            decFlag = true;
        }

        Object result = pjp.proceed();

        String GET = "get";
        String SELECT = "select";
        String FIND = "find";
        if (methodNm.startsWith(GET) || methodNm.startsWith(SELECT) || methodNm.startsWith(FIND)) {
            decObj(result);
        }

        if (decFlag) {
            decParam(pjp);
        }

        return result;
    }

    private void encParam(JoinPoint jp) {
        try {
            Object[] objs = jp.getArgs();

            if (objs == null) {
                return;
            }

            for (Object obj : objs) {
                execute(obj, EncryptType2.ENCRYPT);
            }
        } catch (Exception e) {
            log.error("=======> EncryptAspect : encParam!! : errorMsg : [{}]", e.toString());
        }
    }

    private void decParam(JoinPoint jp) {
        Object[] objs = jp.getArgs();

        if (objs == null) {
            return;
        }

        for (Object obj : objs) {
            decObj(obj);
        }
    }

    private void decObj(Object obj) {
        try {
            execute(obj, EncryptType2.DECRYPT);
        } catch (Exception e) {
            log.error("=======> EncryptAspect : decrypt!! : errorMsg : [{}]", e.toString());
        }
    }

    private void execute(Object obj, EncryptType2 type) throws Exception {
        if (obj == null) {
            return;
        }

        if (obj instanceof List) {
            list((List<?>) obj, type);
        } else if (obj instanceof Map) {
            map((Map<?, ?>) obj, type);
        } else {
            setField(obj, type);
        }
    }

    private void map(Map<?, ?> map, EncryptType2 type) throws Exception {
        for (Object obj : map.values()) {
            setField(obj, type);
        }
    }

    private void list(List<?> list, EncryptType2 type) throws Exception {
        for (Object obj : list) {
            setField(obj, type);
        }
    }

    private void setField(Object obj, EncryptType2 type) throws Exception {
        if (obj == null) {
            return;
        }

        setField2(obj, obj.getClass(), type);
    }

    private void setField2(Object obj, Class<?> clazz, EncryptType2 type2) throws Exception {
        if (clazz == null) {
            return;
        }

        Class<?> sClazz = clazz.getSuperclass();

        if (isCheck2(sClazz.getSimpleName())) {
            setField2(obj, sClazz, type2);
        }

        List<Field> fields = Arrays.stream(clazz.getDeclaredFields()).filter(this::isCheck).collect(Collectors.toList());
        Object oData;
        EncryptType type1;
        for (Field field : fields) {
            field.setAccessible(true);
            oData = field.get(obj);

            if (oData == null) {
                continue;
            }

            if (field.isAnnotationPresent(Encrypt.class)) {
                type1 = field.getDeclaredAnnotation(Encrypt.class).value();
                if (oData instanceof List) {
                    for (Object data : (List<?>) oData) {
                        setField3(field, obj, (String) data, type1, type2);
                    }
                } else if (oData instanceof String) {
                    setField3(field, obj, (String) oData, type1, type2);
                } else {
                    log.error("=======> EncryptAspect : Field type is unknown!!");
                }
            } else {
                execute(oData, type2);
            }
        }
    }

    private void setField3(Field field, Object obj, String data, EncryptType type1, EncryptType2 type2) throws Exception {
        if (StringUtils.isBlank(data)) {
            return;
        }

        if (EncryptType.AES128 == type1) {
            if (EncryptType2.ENCRYPT == type2) {
                field.set(obj, AESEncryptor.encAes128E(data, field.getName()));
            } else if (EncryptType2.DECRYPT == type2) {
                field.set(obj, AESEncryptor.decAes128E(data, field.getName()));
            } else {
                log.error("=======> EncryptAspect : EncryptType2 is empty!!");
            }
        } else if (EncryptType.AES256 == type1) {
            if (EncryptType2.ENCRYPT == type2) {
                field.set(obj, AESEncryptor.encAes256S(data, field.getName()));
            } else if (EncryptType2.DECRYPT == type2) {
                field.set(obj, AESEncryptor.decAes256S(data, field.getName()));
            } else {
                log.error("=======> EncryptAspect : EncryptType2 is empty!!");
            }
        } else if (EncryptType.SHA256 == type1) {
            if (EncryptType2.ENCRYPT == type2) {
                field.set(obj, PBKDF2Encryptor.encrypt(data, field.getName()));
            } else {
                log.error("=======> EncryptAspect : SHA256 : only encrypt!!");
            }
        } else if (EncryptType.MD5 == type1) {
            if (EncryptType2.ENCRYPT == type2) {
                field.set(obj, MD5Encryptor.encrypt(data, field.getName()));
            } else {
                log.error("=======> EncryptAspect : MD5 : only encrypt!!");
            }
        } else {
            log.error("=======> EncryptAspect : EncryptType is empty!!");
        }
    }

    private boolean isCheck(Field field) {
        String typeNm = field.getType().getName();
        return field.isAnnotationPresent(Encrypt.class) || typeNm.endsWith("List") || typeNm.endsWith("Map") || typeNm.endsWith("DTO") || typeNm.endsWith("VO") || typeNm.endsWith("ET");
    }

    private boolean isCheck2(String str) {
        return str.endsWith("DTO") || str.endsWith("VO") || str.endsWith("ET");
    }
}