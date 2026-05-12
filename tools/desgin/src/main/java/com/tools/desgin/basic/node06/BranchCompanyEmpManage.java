package com.tools.desgin.basic.node06;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2024/8/13 16:26
 */
public class BranchCompanyEmpManage {
    // 添加分公司员工
    public List<BranchCompanyEmp> addEmp (){
        List<BranchCompanyEmp> list = new ArrayList<>() ;
        for (int i = 1 ; i <= 3 ; i++){
            list.add(new BranchCompanyEmp("分公司员工"+i)) ;
        }
        return list ;
    }
    // 获取分公司员工
    public void printBranchCompanyEmp (){
        List<BranchCompanyEmp> list = addEmp () ;
        for (BranchCompanyEmp emp:list){
            System.out.println(emp);
        }
    }
}
