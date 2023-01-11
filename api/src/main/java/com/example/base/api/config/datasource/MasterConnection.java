package com.example.base.api.config.datasource;

import javax.persistence.Inheritance;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inheritance
@Documented
public @interface MasterConnection {
}
