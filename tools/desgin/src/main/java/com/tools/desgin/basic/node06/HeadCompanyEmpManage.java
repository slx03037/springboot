package com.tools.desgin.basic.node06;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2024/8/13 16:26
 */
public class HeadCompanyEmpManage {
    // 添加总公司员工
    public List<HeadCompanyEmp> addHeadEmp (){
        List<HeadCompanyEmp> list = new ArrayList<>() ;
        for (int i = 1 ; i <= 3 ; i++){
            list.add(new HeadCompanyEmp("总公司员工"+i)) ;
        }
        return list ;
    }
    public void printEmp (BranchCompanyEmpManage empManage){
        // 打印分公司员工
        empManage.printBranchCompanyEmp();
        List<HeadCompanyEmp> headEmpList = addHeadEmp () ;
        for (HeadCompanyEmp headCompanyEmp:headEmpList){
            System.out.println(headCompanyEmp);
        }
    }
}
