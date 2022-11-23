package com.ur.gymbook.service;

import com.ur.gymbook.model.Admin;
import com.ur.gymbook.model.SuperAdmin;

import java.util.List;

public interface ISuperAdminService {
    List<SuperAdmin> listSu();

    SuperAdmin findByNameAndPassword(String suUserName, String suUserPwd);

    List<Admin> listAdmin();

    int activatedStatusSwitch(Admin admin);

    int updateAccount(SuperAdmin superAdmin);

    int insertAccount(SuperAdmin superAdmin);

    int deleteAccount(SuperAdmin superAdmin);

    int deleteAdminAccount(Admin admin);
}
