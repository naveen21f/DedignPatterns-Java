package com.ezetap.pipelinedesign;

import com.ezetap.pipelinedesign.core.AmountStage;
import com.ezetap.pipelinedesign.core.BeanStage;
import com.ezetap.pipelinedesign.core.NonBeanStage;
import com.ezetap.pipelinedesign.core.PipeLine;
import com.ezetap.pipelinedesign.core.Stage;
import com.ezetap.pipelinedesign.core.StageFactory;
import com.ezetap.pipelinedesign.core.StageType;
import com.ezetap.pipelinedesign.entity.Input;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PipelineDesignApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PipelineDesignApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<StageType> stageTypeList = Arrays.asList(StageType.A, StageType.N, StageType.C,
            StageType.B);

		List<PipeLine> pipeLines = new ArrayList<>(5);

        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            Input input = new Input();
            PipeLine pipeLine = new PipeLine("XIOMI" + random.nextInt(), input);
            Collections.shuffle(stageTypeList);
            stageTypeList.forEach(stageType -> {
                pipeLine.addStage(StageFactory.getStage(stageType.getType()));
            });
		    pipeLines.add(pipeLine);
        }

        pipeLines.forEach(pipeLine -> {
            pipeLine.run();
            pipeLine.logExecutionOrder();
        });

        System.out.println("-----Summary-------");
        pipeLines.forEach(pipeLine -> {
            System.out.println("PIPELINE: " +pipeLine.getName() +" Success: "+pipeLine.getSuccess());
        });
	}

	private void runAsync() {

    }
}
