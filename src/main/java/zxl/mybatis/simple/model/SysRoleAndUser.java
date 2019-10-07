package zxl.mybatis.simple.model;

public class SysRoleAndUser extends SysRole{
    private SysUser sysUser;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }
}
