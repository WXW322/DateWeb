package com.yueba.yueba.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yueba.yueba.mapper.RemarkMapper;
import com.yueba.yueba.model.Remark;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2020/5/11
 * @description
 **/
@Service
public class RemarkService {
    @Autowired
    private RemarkMapper remarkMapper;

    @Transactional(rollbackFor = Exception.class)
    public void insert(Remark remark) {
        remarkMapper.insert(remark);
    }

    public List<Remark> queryAllByUserId(Long userId) {
        val queryWrappers = Wrappers.<Remark>lambdaQuery().eq(Remark::getUserId, userId);
        return remarkMapper.selectList(queryWrappers);
    }
}
