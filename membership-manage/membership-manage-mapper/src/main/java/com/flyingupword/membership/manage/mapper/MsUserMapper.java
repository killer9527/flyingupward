package com.flyingupword.membership.manage.mapper;

import com.flyingupword.membership.manage.entity.MsUserDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MsUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MsUserDO record);

    int insertSelective(MsUserDO record);

    MsUserDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsUserDO record);

    int updateByPrimaryKey(MsUserDO record);
}