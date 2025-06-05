package com.revisao.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.revisao.demo.components.ProcessUserAction;
import com.revisao.demo.dto.UserActionRequest;
import com.revisao.demo.exception.UnsupportedActionException;
import com.revisao.demo.repository.AppRepository;

import jakarta.annotation.PostConstruct;

@Service
public class UserActionHandlerService {

    private final Map<String, ProcessUserAction> actionStrategies = new HashMap<>();
    private final List<ProcessUserAction> allActions;

    public UserActionHandlerService(List<ProcessUserAction> allActions, AppRepository appRepository) {
	this.allActions = allActions;
    }

    @PostConstruct
    public void initActionStrategies() {
	for (ProcessUserAction action : allActions) {
	    actionStrategies.put(action.getActionType(), action);
	}
    }

    public void handleAction(String appId, UserActionRequest request) throws UnsupportedActionException {

	String actionType = request.getActionType();
	ProcessUserAction actionHandler = actionStrategies.get(actionType);

	if (actionHandler == null) {
	    throw new UnsupportedActionException("Tipo de ação não suportado: " + actionType);
	}

	actionHandler.execute(appId, request.getPayload());
    }

}
