package com.revisao.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.revisao.demo.components.ProcessUserAction;
import com.revisao.demo.dto.UserActionRequest;
import com.revisao.demo.exception.UnsupportedActionException;
import com.revisao.demo.models.ProcessEntity;
import com.revisao.demo.repository.ProcessRepository;

import jakarta.annotation.PostConstruct;

@Service
public class UserActionHandlerService {

    private final ProcessRepository processRepository;
    private final Map<String, ProcessUserAction> actionStrategies = new HashMap<>();
    private final List<ProcessUserAction> allActions;

    public UserActionHandlerService(ProcessRepository processRepository, List<ProcessUserAction> allActions) {
	this.processRepository = processRepository;
	this.allActions = allActions;
    }

    @PostConstruct
    public void initActionStrategies() {
	for (ProcessUserAction action : allActions) {
	    actionStrategies.put(action.getActionType(), action);
	}
    }

    public void handleAction(String processId, UserActionRequest request) throws UnsupportedActionException {
	ProcessEntity process = processRepository.findById(processId)
		.orElseThrow(() -> new RuntimeException("Processo não encontrado com ID: " + processId)); // Crie
													  // esta
													  // exceção

	String actionType = request.getActionType();
	ProcessUserAction actionHandler = actionStrategies.get(actionType);

	if (actionHandler == null) {
	    throw new UnsupportedActionException("Tipo de ação não suportado: " + actionType);
	}

	actionHandler.execute(process, request.getPayload());
    }

}
