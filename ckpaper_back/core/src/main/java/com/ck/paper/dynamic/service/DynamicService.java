package com.ck.paper.dynamic.service;

import com.ck.paper.dynamic.po.Dynamic;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @param
 * @author ck
 * @description
 * @date 2020/3/14 15:19
 * @return
 */
@Service
public interface DynamicService {
    List<Dynamic> getDynamic();

    int insertDynamic(Dynamic dynamic);
}
