package com.revisao.demo.repository;

import java.util.List;

import com.revisao.demo.enums.StateProcess;
import com.revisao.demo.models.ProcessEntity;

public interface ProcessRepository extends BaseRepository<ProcessEntity, String> {

    List<ProcessEntity> findByState(StateProcess state);

    List<ProcessEntity> findByStateOrderByDateCreationAsc(StateProcess state);

}
