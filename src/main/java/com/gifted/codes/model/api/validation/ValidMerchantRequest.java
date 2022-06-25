package com.gifted.codes.model.api.validation;

import com.gifted.codes.model.api.merchant.MerchantRequest;
import com.gifted.common.validation.ValidationMessage;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidMerchantRequest.ValidMerchantRequestValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMerchantRequest {

    String message() default ValidationMessage.INVALID_VALUE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class ValidMerchantRequestValidator implements ConstraintValidator<ValidMerchantRequest, MerchantRequest> {

        @Override
        public boolean isValid(MerchantRequest request, ConstraintValidatorContext context) {


            //TODO magic. Check that denomination AND min/max is not set.
            //TODO Check that if min or max is set, BOTH are set

            return true;
        }
    }
}
