package cn.bluedog233.noswustoj.controller;

import cn.bluedog233.noswustoj.domain.QuestionAnswer;
import cn.bluedog233.noswustoj.domain.Result;
import cn.bluedog233.noswustoj.mapper.QuestionAnswerMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class AcController {

    @Resource
    QuestionAnswerMapper mapper;
    @PutMapping
    public Result putQuestion(@RequestBody QuestionAnswer questionAnswer){
        QuestionAnswer q = mapper.selectByPrimaryKey(questionAnswer.getId());
        if (q != null) {
            mapper.updateByPrimaryKey(q);
        }else{
            mapper.insertSelective(questionAnswer);
        }
        return Result.success(null);
    }
    @PostMapping
    public Result<List<QuestionAnswer>> getQuestionAnswer(@RequestBody List<Long> ids){
        var list=ids.stream().map(mapper::selectByPrimaryKey).collect(Collectors.toList());
        return Result.success(list);
    }
    @GetMapping("/{id}")
    public Result<Boolean> getQuestionAnswer(@PathVariable Long id){
        QuestionAnswer questionAnswer = mapper.selectByPrimaryKey(id);
        if (questionAnswer != null) {
            return Result.success(true);
        } else {
            return Result.success(false);
        }
    }
}
