package com.revisao.demo.mapper;

import org.mapstruct.Mapper;

import com.revisao.demo.dto.AppDTO;
import com.revisao.demo.models.App;

@Mapper(componentModel = "spring")
public interface AppMapper extends BaseMapper<AppDTO, App> {

}
