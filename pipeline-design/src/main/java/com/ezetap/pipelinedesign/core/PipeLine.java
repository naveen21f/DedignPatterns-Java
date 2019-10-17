package com.ezetap.pipelinedesign.core;

import com.ezetap.pipelinedesign.entity.Input;
import com.ezetap.pipelinedesign.entity.Output;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author naveen
 * @project pipeline-design
 */
@Data
@Slf4j
public class PipeLine {

    private List<Stage> stages = new ArrayList<>(6);
    private Boolean running = Boolean.FALSE;
    private Boolean done = Boolean.FALSE;
    private Boolean success = Boolean.FALSE;
    private String name = "No Name";
    private Input input;

    public PipeLine(String name, Input input) {
        this.name = name;
        this.input = input;
    }

    public Boolean addStage(Stage stage) {
        if (running) {
            log.info("{}: Can not Add Stage While Running", name);
            return Boolean.FALSE;
        }
        stages.add(stage);
        return Boolean.TRUE;
    }

    private Boolean init() {
        if (running && done) {
            log.info("{}: Pipeline either RUNNING or DONE.", name);
            return Boolean.FALSE;
        }
        if (stages.isEmpty()) {
            log.info("{} Pipeline Stages are Empty.", name);
            return Boolean.FALSE;
        }

        log.info("{}: ----> Stages Configured <-----", name);
        stages.stream().map(Stage::name)
            .forEach(log::info);
        log.info("{} ----> END <-----", name);

        return Boolean.TRUE;
    }

    public Boolean run() {

        if (this.init()){
            log.info("{}: Starting Pipeline", name);
        } else {
            log.info("{}: Can not start Pipeline", name);
            return Boolean.FALSE;
        }

        try {
            Predicate<Output> failed = output -> !output.getSuccess();

            Optional<Output> output = stages.stream()
                .map(stage -> stage.execute(input))
                .filter(failed)
                .findFirst();

           if (output.isPresent()) {
               log.error("{}: Pipeline failed", name);

               return Boolean.FALSE;

           } else {
               log.info("{}: Pipeline Success", name);
               success = Boolean.TRUE;
               return Boolean.TRUE;
           }

        } catch (Exception ex) {
            success = Boolean.FALSE;
            log.error("{}: Exception in Run Method {}", name,ex);
            return Boolean.FALSE;
        } finally {
            this.done = Boolean.TRUE;
            this.running = Boolean.FALSE;
        }
    }

    public void logExecutionOrder() {
        log.info("{}: ---- Execution Order ----", name);
        input.getStageStamp()
            .forEach(log::info);
    }

}