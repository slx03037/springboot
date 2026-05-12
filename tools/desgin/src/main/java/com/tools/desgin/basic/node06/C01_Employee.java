package com.tools.desgin.basic.node06;

/**
 * @author shenlx
 * @description
 * @date 2024/8/13 16:25
 */
public class C01_Employee {
    public static void main(String[] args) {
        HeadCompanyEmpManage empManage = new HeadCompanyEmpManage() ;
        BranchCompanyEmpManage branchEmp = new BranchCompanyEmpManage() ;
        empManage.printEmp(branchEmp);
    }
}
