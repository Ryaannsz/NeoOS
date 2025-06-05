package com.revisao.demo.components;

import java.util.Map;

public interface ProcessUserAction {
    /**
     * Retorna o tipo de ação que esta implementação manipula. Usado para registrar
     * a ação no serviço principal.
     */
    String getActionType();

    /**
     * Executa a lógica da ação específica.
     * 
     * @param process O processo simulado a ser manipulado.
     * @param payload Dados adicionais específicos para esta ação.
     */
    void execute(String appId, Map<String, Object> payload);
}
