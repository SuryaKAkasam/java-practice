package me.suryaakasam.practice.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ClassDoc {
    String author();
    String date();
    int version() default 1;
    String lastModifiedBy() default "N/A";
    String lastModifiedDate() default "N/A";
    String[] reviewers();
}
