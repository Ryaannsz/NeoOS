package com.revisao.demo.components;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.revisao.demo.enums.StateProcess;
import com.revisao.demo.models.ProcessEntity;
import com.revisao.demo.repository.ProcessRepository;
import com.revisao.demo.service.IOService;

@Component
public class SaveFileAction implements ProcessUserAction {

    public static final String TYPE = "SAVE_FILE";

    private ProcessRepository processRepository;

    private IOService ioService;

    @Override
    public String getActionType() {
	return TYPE;
    }

    @Override
    public void execute(ProcessEntity process, Map<String, Object> payload) {

	String fileName = (String) payload.get("fileName");

	System.out.println("Ação: " + TYPE + " para o processo " + process.getId() + " no arquivo " + fileName);

	process.setState(StateProcess.WAITING);
	process.setWaitingReason("DISK_IO_SAVE: " + fileName);
	processRepository.save(process);

	ioService.diskSave(process.getId());
    }

}
