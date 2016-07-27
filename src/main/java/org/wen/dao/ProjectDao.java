package org.wen.dao;

import org.wen.entity.Project;

import java.util.List;

/**
 * 项目的数据访问层
 * 2016年7月9日20:13:45
 * 温海林
 */
public interface ProjectDao {
     List<Project> findAll();
}
