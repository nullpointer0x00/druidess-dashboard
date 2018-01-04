package com.beatnikstree.druidess.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface EnvironmentSettingsRepository extends CrudRepository<EnvironmentSettings, Long> {

    public List<EnvironmentSettings> findAll();

    public EnvironmentSettings findById(Integer id);

    public EnvironmentSettings findFirstByIsDefaultTrue();
}
