package com.ezetap.pipelinedesign.core;

import com.ezetap.pipelinedesign.entity.AmountStageOutput;
import com.ezetap.pipelinedesign.entity.Input;
import com.ezetap.pipelinedesign.entity.StageInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author naveen
 * @project pipeline-design
 */
@Service
@Slf4j
public class AmountStage implements Stage<AmountStageOutput, Input> {

    @Override
    public AmountStageOutput execute(Input input) {
        log.info("Amount Stage");
        input.stamp(name());
        AmountStageOutput amountStageOutput = new AmountStageOutput();
        amountStageOutput.setSuccess(Boolean.TRUE);
        return amountStageOutput;
    }

    @Override
    public String name() {
        return "Amount-Stage";
    }
}