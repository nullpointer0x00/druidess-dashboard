package com.beatnikstree.druidess.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface EnvironmentSettingsRepository extends CrudRepository<EnvironmentSettings, Long> {


}
