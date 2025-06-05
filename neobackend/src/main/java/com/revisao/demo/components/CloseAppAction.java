package com.revisao.demo.components;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.revisao.demo.enums.StateProcess;
import com.revisao.demo.models.ProcessEntity;
import com.revisao.demo.repository.ProcessRepository;

@Component
public class CloseAppAction implements ProcessUserAction {

    public static final String TYPE = "CLOSE_APP";

    private ProcessRepository processRepository;

    @Override
    public String getActionType() {
	return TYPE;
    }

    @Override
    public void execute(ProcessEntity process, Map<String, Object> payload) {

	String fileName = (String) payload.get("fileName");

	System.out.println("Ação: " + TYPE + " para o processo " + process.getId() + " no arquivo " + fileName);

	process.setState(StateProcess.TERMINATED);
	process.setWaitingReason(null);
	processRepository.save(process);

    }

}
