package com.skjs.customerservice.Model.Mapper.Implementation;

import com.skjs.customerservice.Model.DTO.AllUniqueId;
import com.skjs.customerservice.Model.Entity.AllUniqueIdEntity;
import com.skjs.customerservice.Model.Mapper.BaseMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AllUniqueIdMapper extends BaseMapper<AllUniqueIdEntity, AllUniqueId> {
    @Override
    public AllUniqueIdEntity DtoToEntity(AllUniqueId allUniqueId) {
        AllUniqueIdEntity entity = new AllUniqueIdEntity();
        if (allUniqueId != null) {
            BeanUtils.copyProperties(allUniqueId, entity);
        }
        return entity;
    }

    @Override
    public AllUniqueId EntityToDto(AllUniqueIdEntity allUniqueIdEntity) {
        AllUniqueId dto = new AllUniqueId();
        if (allUniqueIdEntity != null) {
            BeanUtils.copyProperties(allUniqueIdEntity, dto);
        }
        return dto;
    }
}
