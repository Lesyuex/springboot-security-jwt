package com.jobeth.utils;

import com.jobeth.enums.ResultEnum;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import com.jobeth.vo.ResultVO;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述
 *
 * @author JyrpoKoo
 * @version [版本号 2019/10/25]
 * @date Created by IntelliJ IDEA on 14:59 2019/10/25
 */
@Slf4j
public class ResultUtil {

    /**
     * 根据枚举类型输出结果
     *
     * @param response   response
     * @param resultEnum resultEnum
     */
    public static void writeResultByResultEnum(HttpServletResponse response, ResultEnum resultEnum) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("code", resultEnum.getCode());
        map.put("message", resultEnum.getMessage());
        responseObjectJson(response, map);
    }

    public ResultUtil() {
    }

    public static void writeSuccessResult(HttpServletResponse response, Object object) {
        ResultVO resultVO = getSuccessResult();
        resultVO.setData(object);
        responseResultJson(response, resultVO);
    }

    public static ResultVO getSuccessResult() {
        return new ResultVO(200, "操作成功");
    }

    public static ResultVO getFailResult() {
        return new ResultVO(500, "操作失败");
    }

    @SneakyThrows
    public static void responseResultJson(HttpServletResponse response, ResultVO resultVO) {
        responseObjectJson(response, resultVO);
    }

    public static void responseObjectJson(HttpServletResponse response, Object object) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            log.info("【responseJson-执行】-{}", JsonUtil.toJsonString(object));
            out = response.getWriter();
            out.write(JsonUtil.toJsonString(object));
        } catch (Exception e) {
            log.error("【responseJson-发生错误】-{}", e.getMessage());
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
}



