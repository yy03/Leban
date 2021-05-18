package com.yy.yeb.controller;


import com.yy.yeb.entity.Position;
import com.yy.yeb.entity.RespBean;
import com.yy.yeb.service.PositionService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2021-04-07
 */
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {
    @Autowired
    private PositionService positionService;

    @ApiOperation("获取所有职位信息")
    @GetMapping("/")
    public List<Position> getAllPositions() {
        return positionService.list();
    }

    @ApiOperation("添加职位信息")
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position) {
        if (positionService.save(position)) {
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiModelProperty("修改职位信息")
    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position) {
        if (positionService.updateById(position)) {
            return RespBean.success("修改成功！");
        }
        return RespBean.error("修改失败！");
    }

    @ApiModelProperty("删除职位信息")
    @DeleteMapping("/{id}")
    public RespBean delPositionById(@PathVariable Integer id) {
        if (positionService.removeById(id)) {
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @ApiModelProperty("批量删除职位信息")
    @DeleteMapping("/")
    public RespBean delPositionByIds(Integer[] ids) {
        if (positionService.removeByIds(Arrays.asList(ids))) {
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }
}

