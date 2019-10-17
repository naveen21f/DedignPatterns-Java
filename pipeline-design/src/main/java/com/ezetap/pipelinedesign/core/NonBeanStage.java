package com.ezetap.pipelinedesign.core;

import com.ezetap.pipelinedesign.entity.Input;
import com.ezetap.pipelinedesign.entity.NonBeanStageOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author naveen
 * @project pipeline-design
 */
@Service
@Slf4j
public class NonBeanStage implements Stage<NonBeanStageOutput, Input> {

    @Override
    public NonBeanStageOutput execute(Input input) {
        log.info("Non Bean Stage");
        input.stamp(name());
        NonBeanStageOutput nonBeanStageOutput = new NonBeanStageOutput();
        nonBeanStageOutput.setSuccess(Boolean.TRUE);
        return nonBeanStageOutput;
    }

    @Override
    public String name() {
        return "NonBean-Stage";
    }
}