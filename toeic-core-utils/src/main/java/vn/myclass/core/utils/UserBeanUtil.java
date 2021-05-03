package vn.myclass.core.utils;

import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.persistence.entity.UserEntity;

public class UserBeanUtil {
    public static UserDTO entity2Dto(UserEntity userEntity){
        UserDTO dto=new UserDTO();
        dto.setUserId(userEntity.getUserId());
        dto.setName(userEntity.getName());
        dto.setFullName(userEntity.getFullName());
        dto.setPassword(userEntity.getPassword());
        dto.setCreatedDate(userEntity.getCreatedDate());
        dto.setRoleDTO(RoleBeanUtil.entity2Dto(userEntity.getRole()));
        return dto;
    }

    public static UserEntity dto2Entity(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setUserId(dto.getUserId());
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        entity.setFullName(dto.getFullName());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setRoleEntity(RoleBeanUtil.dto2Entity(dto.getRoleDTO()));
        return entity;
    }
}
