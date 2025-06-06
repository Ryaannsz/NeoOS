package com.revisao.demo.components;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.revisao.demo.exception.UnsupportedActionException;
import com.revisao.demo.util.ProducerManipulateDTO;

@Component
public class CloseAppAction implements ProcessUserAction {

    public static final String TYPE = "CLOSE_APP";

    private ProducerManipulateDTO manipulateDTO;

    public CloseAppAction(ProducerManipulateDTO manipulateDTO) {
	this.manipulateDTO = manipulateDTO;
    }

    @Override
    public String getActionType() {
	return TYPE;
    }

    @Override
    public void execute(String appId, Map<String, Object> payload) throws UnsupportedActionException {

	manipulateDTO.createProcessDTO(appId, payload, TYPE);
    }

}
