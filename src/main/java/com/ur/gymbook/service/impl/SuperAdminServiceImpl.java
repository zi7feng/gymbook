package com.ur.gymbook.service.impl;

import com.ur.gymbook.mapper.AdminMapper;
import com.ur.gymbook.mapper.SuperAdminMapper;
import com.ur.gymbook.model.Admin;
import com.ur.gymbook.model.SuperAdmin;
import com.ur.gymbook.service.ISuperAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SuperAdminServiceImpl implements ISuperAdminService{
    @Resource
    private SuperAdminMapper superAdminMapper;

    @Override
    public List<SuperAdmin> listSu() {
        return superAdminMapper.listSu();
    }

    @Override
    public SuperAdmin findByNameAndPassword(String suUserName, String suUserPwd) {
        return superAdminMapper.findByNameAndPassword(suUserName, suUserPwd);
    }

    @Override
    public List<Admin> listAdmin() {
        return superAdminMapper.listAdmin();
    }

    @Override
    public void activatedStatusSwitch(Admin admin) {
        superAdminMapper.activatedStatusSwitch(admin);
    }
}
