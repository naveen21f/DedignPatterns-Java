package com.ezetap.pipelinedesign.core;

import com.ezetap.pipelinedesign.entity.ConflictStageOutput;
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
public class ConflictResolveStage implements Stage<ConflictStageOutput, Input> {

    @Override
    public ConflictStageOutput execute(Input input) {
        log.info("Conflict Stage");
        input.stamp(name());
        ConflictStageOutput conflictStageOutput = new ConflictStageOutput();
        conflictStageOutput.setSuccess(Boolean.TRUE);
        return conflictStageOutput;
    }

    @Override
    public String name() {
        return "Conflict-Stage";
    }
}