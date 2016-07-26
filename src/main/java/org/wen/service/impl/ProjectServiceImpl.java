package org.wen.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wen.dao.ProjectDao;
import org.wen.entity.Project;
import org.wen.section.SystemServiceLog;
import org.wen.service.ProjectService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目实现
 * 2016年7月9日20:12:52
 * 温海林
 */
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    @Resource
    private ProjectDao projectDao;
    @SystemServiceLog(description = "查询所有项目")
    public List<Project> getProject() {
        return projectDao.findAll();
    }
}
