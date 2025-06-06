package com.revisao.demo.repository;

import java.util.List;
import java.util.Optional;

import com.revisao.demo.enums.StateProcess;
import com.revisao.demo.models.App;
import com.revisao.demo.models.ProcessEntity;

public interface ProcessRepository extends BaseRepository<ProcessEntity, String> {

    List<ProcessEntity> findByState(StateProcess state);

    List<ProcessEntity> findByStateOrderByDateCreationAsc(StateProcess state);

    Optional<ProcessEntity> findByApp_id(String id);

    Optional<ProcessEntity> findByApp_IdAndInstanceName(String appId, String instanceName);

    boolean existsByApp(App app);

}
