package com.talkweb.ei.shiro;
import java.io.Serializable;

import org.apache.shiro.authc.UsernamePasswordToken;

public class ShiroToken extends UsernamePasswordToken implements Serializable
{

    /**
     * 简单的用户密码令牌
     */
    private static final long serialVersionUID = -2013574731436985473L;

    public ShiroToken(String username, String pswd)
    {
        super(username, pswd);
        this.pswd = pswd;
    }

    private String pswd;

    public String getPswd()
    {
        return pswd;
    }

    public void setPswd(String pswd)
    {
        this.pswd = pswd;
    }

}