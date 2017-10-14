package com.tutorial.gandalf.user.service;

import com.tutorial.gandalf.PageInfo;
import com.tutorial.gandalf.user.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 用户业务
 *
 * @author Cc
 */
@Service
public class UserService {

    /**
     * 用户用户信息分页信息
     *
     * @param page 页码
     * @param pageSize 元素个数
     * @return 用户分页信息
     */
    public PageInfo<User> getPageInfo(int page, int pageSize) {
        page = Math.max(1, page);
        pageSize = Math.max(5, pageSize);
        PageInfo<User> pageInfo = new PageInfo<>(page, pageSize);

        int count = 23;
        pageInfo.setItemCount(count);

        List<User> list = getList(count);
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(page * pageSize, list.size());

        List<User> items = list.subList(fromIndex, toIndex);
        pageInfo.setItems(items);
        return pageInfo;
    }

    private List<User> getList(int count) {
        List<User> result = new ArrayList<>();
        for (int i = 0; i < count; i ++) {
            int uid = i + 1;
            User user = new User();
            user.setUid(uid);
            user.setAvatar("http://img2.imgtn.bdimg.com/it/u=634098145,264198475&fm=27&gp=0.jpg");
            user.setCreatedTime(new Date(1507954234000L - i * 24 * 60 * 60 * 1000L));
            user.setGender(i % 2 == 0 ? "男" : "女");
            user.setNickname("昵称" + uid);
            user.setSignature("签名" + uid);
            result.add(user);
        }
        return result;
    }
}
