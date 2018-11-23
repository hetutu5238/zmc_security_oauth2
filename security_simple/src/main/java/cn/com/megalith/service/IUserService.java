package cn.com.megalith.service;

import cn.com.megalith.domain.entity.User;

/**
 * @Description:
 * @author: zhoum
 * @Date: 2018-11-22
 * @Time: 15:21
 */
public interface IUserService {
    /*
     * @Author zhoum
     * @Description 根据当前用户名获取用户的数据
     * @Date 15:59 2018-11-22
     * @Param [s]
     * @return cn.com.megalith.domain.entity.User
     **/
    User getByName(String s);
}
