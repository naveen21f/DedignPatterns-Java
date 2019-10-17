package com.ezetap.pipelinedesign.core;

import com.ezetap.pipelinedesign.entity.BeanStageOutput;
import com.ezetap.pipelinedesign.entity.Input;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author naveen
 * @project pipeline-design
 */
@Service
@Slf4j
public class BeanStage implements Stage<BeanStageOutput, Input> {

    @Override
    public BeanStageOutput execute(Input input) {
        log.info("Bean Stage");
        input.stamp(name());
        BeanStageOutput beanStageOutput = new BeanStageOutput();
        beanStageOutput.setSuccess(Boolean.TRUE);
        return beanStageOutput;
    }

    @Override
    public String name() {
        return "Bean-Stage";
    }
}