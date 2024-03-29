package com.ur.gymbook.service.impl;

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
    public int activatedStatusSwitch(Admin admin) {
        return superAdminMapper.activatedStatusSwitch(admin);
    }

    @Override
    public int updateAccount(SuperAdmin superAdmin) {
        return superAdminMapper.updateAccount(superAdmin);

    }

    @Override
    public int insertAccount(SuperAdmin superAdmin) {
        return superAdminMapper.insertAccount(superAdmin);
    }

    @Override
    public int deleteAccount(SuperAdmin superAdmin) {
        return superAdminMapper.deleteAccount(superAdmin);
    }

    @Override
    public int deleteAdminAccount(Admin admin) {
        return superAdminMapper.deleteAdminAccount(admin);
    }
}
