package com.revisao.demo.mapper;

import org.mapstruct.Mapper;

import com.revisao.demo.dto.ProcessDTO;
import com.revisao.demo.models.ProcessEntity;

@Mapper(componentModel = "spring")
public interface ProcessMapper extends BaseMapper<ProcessDTO, ProcessEntity> {

}
