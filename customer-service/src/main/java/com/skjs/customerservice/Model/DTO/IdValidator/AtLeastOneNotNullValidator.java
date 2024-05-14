package com.skjs.customerservice.Model.DTO.IdValidator;

import com.skjs.customerservice.Model.DTO.AllUniqueId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AtLeastOneNotNullValidator implements ConstraintValidator<AtLeastOneNotNull, AllUniqueId> {

    @Override
    public boolean isValid(AllUniqueId value, ConstraintValidatorContext context) {
        return value != null && (
                value.getAadharCardNumber() != null && !value.getAadharCardNumber().isEmpty() ||
                        value.getPanCardNumber() != null && !value.getPanCardNumber().isEmpty() ||
                        value.getVoterIdCardNumber() != null && !value.getVoterIdCardNumber().isEmpty()
        );
    }
}

