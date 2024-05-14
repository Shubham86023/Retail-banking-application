package com.skjs.customerservice.Model.Mapper.Implementation;

import com.skjs.customerservice.Model.DTO.Address;
import com.skjs.customerservice.Model.Entity.AddressEntity;
import com.skjs.customerservice.Model.Mapper.BaseMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper extends BaseMapper<AddressEntity, Address> {

    @Override
    public AddressEntity DtoToEntity(Address address) {
        AddressEntity entity = new AddressEntity();
        if (address != null) {
            BeanUtils.copyProperties(address, entity);
        }
        return entity;
    }

    @Override
    public Address EntityToDto(AddressEntity addressEntity) {
        Address dto = new Address();
        if (addressEntity != null) {
            BeanUtils.copyProperties(addressEntity, dto);
        }
        return dto;
    }
}
